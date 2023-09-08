from __future__ import annotations
from typing import Iterator, Optional

# from base.line_format_system import LineFormatHandler
# from base.rule_system import ItemHandler, Terminal
from base.abstract_token_autogen import *

from base.line_format_system import LineFormat, LineFormatHandler, is_inline, next_indent_width
from base.rule_system import Rule, Item, ItemHandler, Terminal, Nonterm

from dataclasses import dataclass

def from_primitive(ptok : list[str]) -> AbstractToken:
    assert len(ptok) == 4
    assert ptok[0] == "P"
    if ptok[1] == "grammar":
        return make_Grammar(ptok[2], ptok[3])
    else:
        assert ptok[1] == "vocab"
        return make_Vocab(ptok[2], ptok[3])


def raise_exception(e):
    raise e

def to_primitive(inst : AbstractToken) -> list[str]:
    class Handler(AbstractTokenHandler):
        def case_Grammar(self, o):
            return ["P", "grammar", o.options, o.selection]

        def case_Vocab(self, o): 
            return ["P", "vocab", o.options, o.selection]

        def case_Hole(self, o): 
            return ["H"]
    return inst.match(Handler())

def to_string(token : AbstractToken) -> str:
    class Handler(AbstractTokenHandler):
        def case_Grammar(self, g): return f"grammar: {g.selection} <{g.options}>"
        def case_Vocab(self, v): return f"vocab: {v.selection} <{v.options}>"
        def case_Hole(self, v): return f"hole"
    return token.match(Handler())


def dump(rule_map : dict[str, Rule], AbstractTokens : tuple[AbstractToken, ...], indent : int = 4):

    @dataclass
    class Format:
        relation : str 
        depth : int 

    def dump_AbstractToken(inst : AbstractToken, format : Format) -> str:

        class Handler(AbstractTokenHandler):
            def case_Grammar(self, o):
                indent_str = (' ' * format.depth * indent)
                relation_str = (' = .' + format.relation if (isinstance(format.relation, str)) else '')
                return (
                    indent_str + o.selection + (' (' + o.options  + ')' if o.options != o.selection else '') +
                    relation_str
                )

            def case_Vocab(self, o):
                indent_str = (' ' * format.depth * indent)
                relation_str = (' = .' + format.relation if (isinstance(format.relation, str)) else '')
                return (
                    indent_str + o.selection + ' (' + o.options  + ')' +
                    relation_str
                )

            def case_Hole(self, o):
                indent_str = (' ' * format.depth * indent)
                relation_str = (' = .' + format.relation if (isinstance(format.relation, str)) else '')
                return (
                    indent_str + 'HOLE' + 
                    relation_str
                )

        return inst.match(Handler())





    result_strs = [] 

    inst_iter = iter(AbstractTokens)

    stack : list[Format] = [Format("", 0)]

    while stack:
        format : Format = stack.pop()
        inst = next(inst_iter, None)

        if not inst:
            return '\n'.join(result_strs)

        class Formatter(AbstractTokenHandler):
            def case_Grammar(self, inst):
                nonlocal stack
                nonlocal format
                rule = rule_map[inst.selection]

                for item in reversed(rule.content):
                    if not isinstance(item, Terminal):
                        class Handler(ItemHandler):
                            def case_Terminal(self, o):
                                raise Exception()
                            def case_Nonterm(self, o): 
                                return Format(o.relation, format.depth + 1)
                            def case_Vocab(self, o):
                                return Format(o.relation, format.depth + 1)

                        child_format = item.match(Handler())
                        stack += [child_format]

            def case_Vocab(self, inst):
                pass

            def case_Hole(self, inst):
                pass

        inst.match(Formatter())

        result_strs += [dump_AbstractToken(inst, format)]

    return '\n'.join(result_strs)


def concretize(rule_map : dict[str, Rule], AbstractTokens : tuple[AbstractToken, ...]) -> str:

    @dataclass
    class Format:
        inline : bool 
        indent_width : int 

    token_iter = iter(AbstractTokens)
    first_token = next(token_iter)
    assert isinstance(first_token, Grammar)
    stack : list[tuple[Format, Grammar, tuple[str, ...]]] = [(Format(True, 0), first_token, ())]

    stack_result : str | None = None 
    while stack:

        (format, token, children) = stack.pop()

        if stack_result != None:
            # get the result from the child in the stack
            children = children + (stack_result,) 
            stack_result = None

        rule = rule_map[token.selection]
        index = len(children)
        if index == len(rule.content):
            prefix = "" if format.inline else "\n" + "    " * format.indent_width
            stack_result = prefix + "".join(children)

        else:
            item = rule.content[index]

            if isinstance(item, Nonterm):
                child_token = next(token_iter, None)

                stack.append((format, token, children))
                child_format = Format(is_inline(item.format), next_indent_width(format.indent_width, item.format))
                if isinstance(child_token, Grammar):
                    stack.append((child_format, child_token, ()))
                else:
                    break

            elif isinstance(item, Vocab):
                vocab_token = next(token_iter, None)
                if isinstance(vocab_token, Vocab):
                    if vocab_token.options == "comment" and vocab_token.selection:
                        comments = vocab_token.selection.split("\n")

                        comment = ('' if index == 0 else ' ') + ("\n" + ("    " * format.indent_width)).join([c for c in comments if c]) + "\n"

                        stack.append((format, token, children + (comment,)))
                    else:
                        stack.append((format, token, children + (vocab_token.selection,)))
                else:
                    break

            elif isinstance(item, Terminal):
                class Handler(LineFormatHandler):
                    def case_InLine(self, _): return ""
                    def case_NewLine(self, _): return "\n" + ("    " * format.indent_width)
                    def case_IndentLine(self, _): return "\n" + ("    " * format.indent_width)

                prefix = ""
                if index != 0 and index == len(rule.content) - 1:
                    pred = rule.content[index - 1]
                    if isinstance(pred, Nonterm):
                        prefix = pred.format.match(Handler())

                s = (prefix + item.terminal)
                stack.append((format, token, children + (s,)))


    # if stack is not empty, then input program must be incomplete
    # so clean up the stack
    while stack:
        (format, token, children) = stack.pop()

        if stack_result != None:
            # get the result from the child in the stack
            children = children + (stack_result,) 

        rule = rule_map[token.selection]
        prefix = "" if format.inline else "\n" + "    " * format.indent_width
        stack_result = prefix + "".join(children)

    assert stack_result != None
    return stack_result

def concretize_old(rule_map : dict[str, Rule], AbstractTokens : tuple[AbstractToken, ...]) -> str:

    @dataclass
    class Format:
        inline : bool 
        indent_width : int 

    result = ""

    token_iter = iter(AbstractTokens)

    stack : list[Union[str, Format]] = [Format(True, 0)] # str is concrete syntax, and int is indentation of the AbstractToken from the iterator 
    AbstractToken_count = 0

    while stack:

        stack_item : Union[str, Format] = stack.pop()
        if isinstance(stack_item, str):
            result += stack_item 
        else: 
            assert isinstance(stack_item, Format)
            format = stack_item

            # take an element from the iterator
            inst = next(token_iter, None)
            if not inst:
                break

            AbstractToken_count += 1

            class Handler(AbstractTokenHandler):
                def case_Grammar(self, inst):
                    nonlocal stack
                    rule = rule_map[inst.selection]
                    for i, item in enumerate(reversed(rule.content)):
                        class Handler(ItemHandler):
                            def case_Terminal(self, o):
                                j = len(rule.content) - 1 - i
                                class Formatter(LineFormatHandler):
                                    def case_InLine(self, _): return ""
                                    def case_NewLine(self, _): return "\n" + ("    " * format.indent_width)
                                    def case_IndentLine(self, _): return "\n" + ("    " * format.indent_width)

                                prefix = ""
                                if i == 0: 
                                    pred = rule.content[j - 1]
                                    if isinstance(pred, Nonterm):
                                        prefix = pred.format.match(Formatter)

                                stack.append(prefix + o.terminal)

                            def case_Nonterm(self, o):
                                child_format = Format(is_inline(o.format), next_indent_width(format.indent_width, o.format))
                                stack.append(child_format)

                            def case_Vocab(self, o):
                                stack.append(format)

                        item.match(Handler())

                    prefix = "" if format.inline else "\n" + "    " * format.indent_width
                    stack += [prefix]
                
                def case_Vocab(self, inst):
                    nonlocal stack
                    stack += [inst.selection]

                def case_Hole(self, _):
                    nonlocal stack
                    stack += ['(HOLE)']

            inst.match(Handler())

    return result


from typing import Sequence
def truncate_at_hole(toks : Sequence[AbstractToken]) -> Sequence[AbstractToken]:
    hole_index = next((i for i, t in enumerate(toks) if isinstance(t, Hole)), None) 
    if hole_index:
        return toks[0:hole_index]
    else:
        return toks

