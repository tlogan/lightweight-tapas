grammar Slim;

@header {
from dataclasses import dataclass
from typing import *
from tapas.util_system import box, unbox
from contextlib import contextmanager

from tapas.slim.analyzer import * 

from pyrsistent import m, pmap, v
from pyrsistent.typing import PMap 

}


@parser::members {

_solver : Solver 
_cache : dict[int, str] = {}

_guidance : Guidance 
_overflow = False  

def init(self): 
    self._solver = Solver() 
    self._cache = {}
    self._guidance = nt_default 
    self._overflow = False  

def reset(self): 
    self._guidance = nt_default
    self._overflow = False
    # self.getCurrentToken()
    # self.getTokenStream()



def getGuidance(self):
    return self._guidance

def tokenIndex(self):
    return self.getCurrentToken().tokenIndex

def guide_nonterm(self, f : Callable, *args) -> Optional[Nonterm]:
    for arg in args:
        if arg == None:
            self._overflow = True

    nt_result = None
    if not self._overflow:
        nt_result = f(*args)
        self._guidance = nt_result

        tok = self.getCurrentToken()
        if tok.type == self.EOF :
            self._overflow = True 

    return nt_result 



def guide_lex(self, guidance : Union[Symbol, Terminal]):   
    if not self._overflow:
        self._guidance = guidance 

        tok = self.getCurrentToken()
        if tok.type == self.EOF :
            self._overflow = True 


def guide_symbol(self, text : str):
    self.guide_lex(Symbol(text))

def guide_terminal(self, text : str):
    self.guide_lex(Terminal(text))



def collect(self, f : Callable, *args):

    if self._overflow:
        return None
    else:

        clean = next((
            False
            for arg in args
            if arg == None
        ), True)

        if clean:
            return f(*args)
        else:
            return None
        # TODO: caching is broken; tokenIndex does not change 
        # index = self.tokenIndex() 
        # cache_result = self._cache.get(index)
        # print(f"CACHE: {self._cache}")
        # if False: # cache_result:
        #     return cache_result
        # else:
        #     result = f(*args)
        #     self._cache[index] = result
        #     return result

}

ids returns [list[str] combo] :

| ID {
$combo = [$ID.text]
}

| ID ids {
$combo = [$ID.text] ++ $ids.combo
}

;


typ_base returns [Typ combo] : 

| ID {
$combo = TVar($ID.text) 
}

| '@' {
$combo = TUnit() 
}

// Tag 
| ':' ID typ_base {
$combo = TTag($ID.text, $typ_base.combo) 
}

// Field 
| ID ':' typ_base {
$combo = TField($ID.text, $typ_base.combo) 
}


| '(' typ ')' {
$combo = $typ.combo   
}

;

typ returns [Typ combo] : 

| typ_base {
$combo = $typ_base.combo
}

// NOTE: infix type combinators are right-associative  
| typ_base '|' typ {
$combo = Unio($typ_base.combo, $typ.combo) 
}

| typ_base '&' typ {
$combo = Inter($typ_base.combo, $typ.combo) 
}

| typ_base '->' typ {
$combo = Imp($typ_base.combo, $typ.combo) 
}


// Tuple 
| typ_base ',' typ {
$combo = Inter(TField('left', $typ_base.combo), TField('right', $typ.combo)) 
}

// indexed union
//  {P <: T, ...}     
| '{' ids '.' qualification '}' typ {
$combo = IdxUnio($ids.combo, $qualification.combo, $typ.combo) 
}


// indexed intersection
// [P <: T, ...] T 
// [T <: X -> Y, ...] :a X -> :b Y 
| '[' ids '.' qualification ']' typ {
$combo = IdxInter($ids.combo, $qualification.combo, $typ.combo) 
}


//induction // least fixed point; smallest set such that typ <: ID is invariant
//   
// least self with 
// :zero, :nil |  
// {n, l <: self] succ n, cons l 
| 'least' ID 'with' typ {
$combo = Least($ID.text, $typ.combo) 
}


//co-induction // greatest fixed point; greatest set such that ID <: typ is invariant
//
// greatest self of 
// nil -> zero &  
// [self <: n -> l] cons n -> succ l 
| 'greatest' ID 'of' typ {
$combo = Greatest($ID.text, $typ.combo) 
}

;

qualification returns [list[tuple[Typ, Typ]] combo] :

| subtyping

| subtyping ',' qualification

;

subtyping returns [tuple[Typ, Typ] combo] :

| typ '<:' typ

;

expr [Nonterm nt] returns [Typ combo] : 

// Base rules

| base[nt] {
$combo = $base.combo
}

// Introduction rules

| {
nt_cator = self.guide_nonterm(ExprRule(self._solver, nt).distill_tuple_head)
} head = base[nt] {
self.guide_symbol(',')
} ',' {
nt_cator = self.guide_nonterm(ExprRule(self._solver, nt).distill_tuple_tail, $head.combo)
} tail = base[nt] {
$combo = self.collect(ExprRule(self._solver, nt).combine_tuple, $head.combo, $tail.combo) 
}

// Elimination rules

| 'if' {
nt_condition = self.guide_nonterm(ExprRule(self._solver, nt).distill_ite_condition)
} condition = expr[nt_condition] {
self.guide_symbol('then')
} 'then' {
nt_branch_true = self.guide_nonterm(ExprRule(self._solver, nt).distill_ite_branch_true, $condition.combo)
} branch_true = expr[nt_branch_true] {
self.guide_symbol('else')
} 'else' {
nt_branch_false = self.guide_nonterm(ExprRule(self._solver, nt).distill_ite_branch_false, $condition.combo, $branch_true.combo)
} branch_false = expr[nt_branch_false] {
$combo = self.collect(ExprRule(self._solver, nt).combine_ite, $condition.combo, $branch_true.combo, $branch_false.combo) 
} 

| {
nt_cator = self.guide_nonterm(ExprRule(self._solver, nt).distill_projection_cator)
} cator = base[nt_cator] {
nt_keychain = self.guide_nonterm(ExprRule(self._solver, nt).distill_projection_keychain, $cator.combo)
} keychain[nt_keychain] {
$combo = self.collect(ExprRule(self._solver, nt).combine_projection, $cator.combo, $keychain.combo) 
}

| {
nt_cator = self.guide_nonterm(ExprRule(self._solver, nt).distill_application_cator)
} cator = base[nt_cator] {
nt_argchain = self.guide_nonterm(ExprRule(self._solver, nt).distill_application_argchain, $cator.combo)
} argchain[nt_argchain] {
$combo = self.collect(ExprRule(self._solver, nt).combine_application, $cator.combo, $argchain.combo)
}

| {
nt_arg = self.guide_nonterm(ExprRule(self._solver, nt).distill_funnel_arg)
} cator = base[nt_arg] {
nt_pipeline = self.guide_nonterm(ExprRule(self._solver, nt).distill_funnel_pipeline, $cator.combo)
} pipeline[nt_pipeline] {
$combo = self.collect(ExprRule(self._solver, nt).combine_funnel, $cator.combo, $pipeline.combo)
}

| 'let' {
self.guide_terminal('ID')
} ID {
nt_target = self.guide_nonterm(ExprRule(self._solver, nt).distill_let_target, $ID.text)
} target[nt_target] {
self.guide_symbol(';')
} ';' {
nt_contin = self.guide_nonterm(ExprRule(self._solver, nt).distill_let_contin, $ID.text, $target.combo)
} contin = expr[nt_contin] {
$combo = $contin.combo
}


| 'fix' {
self.guide_symbol('(')
} '(' {
nt_body = self.guide_nonterm(ExprRule(self._solver, nt).distill_fix_body)
} body = expr[nt_body] {
self.guide_symbol(')')
} ')' {
$combo = self.collect(ExprRule(self._solver, nt).combine_fix, $body.combo)
}

;

base [Nonterm nt] returns [Typ combo] : 
// Introduction rules

| '@' {
$combo = self.collect(BaseRule(self._solver, nt).combine_unit)
} 

| ':' {
self.guide_terminal('ID')
} ID {
nt_body = self.guide_nonterm(BaseRule(self._solver, nt).distill_tag_body, $ID.text)
} body = expr[nt_body] {
$combo = self.collect(BaseRule(self._solver, nt).combine_tag, $ID.text, $body.combo)
}

| record[nt] {
$combo = $record.combo
}

| {
} function[nt] {
$combo = $function.combo
}

// Elimination rules

| ID {
$combo = self.collect(BaseRule(self._solver, nt).combine_var, $ID.text)
} 

| '(' {
nt_expr = self.guide_nonterm(lambda: nt)
} expr[nt_expr] {
self.guide_symbol(')')
} ')' {
$combo = $expr.combo
} 

;


function [Nonterm nt] returns [Typ combo] :

| 'case' {
nt_pattern = self.guide_nonterm(FunctionRule(self._solver, nt).distill_single_pattern)
} pattern[nt_pattern] {
self.guide_symbol('=>')
} '=>' {
nt_body = self.guide_nonterm(FunctionRule(self._solver, nt).distill_single_body, $pattern.combo)
} body = expr[nt_body] {
$combo = self.collect(FunctionRule(self._solver, nt).combine_single, $pattern.combo, $body.combo)
}

| 'case' {
nt_pattern = self.guide_nonterm(FunctionRule(self._solver, nt).distill_cons_pattern)
} pattern[nt_pattern] {
self.guide_symbol('=>')
} '=>' {
nt_body = self.guide_nonterm(FunctionRule(self._solver, nt).distill_cons_body, $pattern.combo)
} body = expr[nt_body] {
nt_tail = self.guide_nonterm(FunctionRule(self._solver, nt).distill_cons_tail, $pattern.combo, $body.combo)
} tail = function[nt] {
$combo = self.collect(FunctionRule(self._solver, nt).combine_cons, $pattern.combo, $body.combo, $tail.combo)
}

;



record [Nonterm nt] returns [Typ combo] :

| ':' {
self.guide_terminal('ID')
} ID {
self.guide_symbol('=')
} '=' {
nt_body = self.guide_nonterm(RecordRule(self._solver, nt).distill_single_body, $ID.text)
} body = expr[nt_body] {
$combo = self.collect(RecordRule(self._solver, nt).combine_single, $ID.text, $body.combo)
}

| ':' {
self.guide_terminal('ID')
} ID {
self.guide_symbol('=')
} '=' {
nt_body = self.guide_nonterm(RecordRule(self._solver, nt).distill_cons_body, $ID.text)
} body = expr[nt] {
nt_tail = self.guide_nonterm(RecordRule(self._solver, nt).distill_cons_tail, $ID.text, $body.combo)
} tail = record[nt] {
$combo = self.collect(RecordRule(self._solver, nt).combine_cons, $ID.text, $body.combo, $tail.combo)
}

;

// NOTE: nt.expect represents the type of the rator applied to the next immediate argument  
argchain [Nonterm nt] returns [list[Typ] combo] :

| '(' {
nt_content = self.guide_nonterm(ArgchainRule(self._solver, nt).distill_single_content) 
} content = expr[nt_content] {
self.guide_symbol(')')
} ')' {
$combo = self.collect(ArgchainRule(self._solver, nt).combine_single, $content.combo)
}

| '(' {
nt_head = self.guide_nonterm(ArgchainRule(self._solver, nt).distill_cons_head) 
} head = expr[nt_head] {
self.guide_symbol(')')
} ')' {
nt_tail = self.guide_nonterm(ArgchainRule(self._solver, nt).distill_cons_tail, $head.combo) 
} tail = argchain[nt_tail] {
$combo = self.collect(ArgchainRule(self._solver, nt).combine_cons, $head.combo, $tail.combo)
}

;

pipeline [Nonterm nt] returns [list[Typ] combo] :

| '|>' {
nt_content = self.guide_nonterm(PipelineRule(self._solver, nt).distill_single_content) 
} content = expr[nt_content] {
$combo = self.collect(PipelineRule(self._solver, nt).combine_single, $content.combo)
}

| '|>' {
nt_head = self.guide_nonterm(PipelineRule(self._solver, nt).distill_cons_head) 
} head = expr[nt_head] {
nt_tail = self.guide_nonterm(PipelineRule(self._solver, nt).distill_cons_tail, $head.combo) 
} tail = pipeline[nt_tail] {
$combo = self.collect(ArgchainRule(self._solver, nt).combine_cons, $head.combo, $tail.combo)
}

;


// NOTE: nt.expect represents the type of the rator applied to the next immediate argument  
keychain [Nonterm nt] returns [list[str] combo] :

| '.' {
self.guide_terminal('ID')
} ID {
$combo = self.collect(KeychainRule(self._solver, nt).combine_single, $ID.text)
}

| '.' {
self.guide_terminal('ID')
} ID {
nt_tail = self.guide_nonterm(KeychainRule(self._solver, nt).distill_cons_tail, $ID.text) 
} tail = keychain[nt_tail] {
$combo = self.collect(KeychainRule(self._solver, nt).combine_cons, $ID.text, $tail.combo)
}

;

target [Nonterm nt] returns [Typ combo]:

| '=' {
nt_expr = self.guide_nonterm(lambda: nt)
} expr[nt_expr] {
$combo = $expr.combo
}

;



pattern [Nonterm nt] returns [PatternAttr combo]:  

| pattern_base[nt] {
$combo = $pattern_base.combo
}

| {
nt_cator = self.guide_nonterm(PatterRule(self._solver, nt).distill_tuple_head)
} head = base[nt] {
self.guide_symbol(',')
} ',' {
nt_cator = self.guide_nonterm(PatterRule(self._solver, nt).distill_tuple_tail, $head.combo)
} tail = base[nt] {
$combo = self.collect(ExprRule(self._solver, nt).combine_tuple, $head.combo, $tail.combo) 
}

;

pattern_base [Nonterm nt] returns [PatternAttr combo]:  

| ID {
$combo = self.collect(PatternBaseRule(self._solver, nt).combine_var, $ID.text)
} 

| ID {
$combo = self.collect(PatternBaseRule(self._solver, nt).combine_var, $ID.text)
} 

| '@' {
$combo = self.collect(PatternBaseRule(self._solver, nt).combine_unit)
} 

| ':' {
self.guide_terminal('ID')
} ID {
nt_body = self.guide_nonterm(PatternBaseRule(self._solver, nt).distill_tag_body, $ID.text)
} body = pattern[nt_body] {
$combo = self.collect(PatternBaseRule(self._solver, nt).combine_tag, $ID.text, $body.combo)
}

| pattern_record[nt] {
$combo = $pattern_record.combo
}

;

pattern_record [Nonterm nt] returns [PatternAttr combo] :

| ':' {
self.guide_terminal('ID')
} ID {
self.guide_symbol('=')
} '=' {
nt_body = self.guide_nonterm(PatternRecordRule(self._solver, nt).distill_single_body, $ID.text)
} body = pattern[nt_body] {
$combo = self.collect(PatternRecordRule(self._solver, nt).combine_single, $ID.text, $body.combo)
}

| ':' {
self.guide_terminal('ID')
} ID {
self.guide_symbol('=')
} '=' {
nt_body = self.guide_nonterm(PatternRecordRule(self._solver, nt).distill_cons_body, $ID.text)
} body = pattern[nt_body] {
nt_tail = self.guide_nonterm(PatternRecordRule(self._solver, nt).distill_cons_tail, $ID.text, $body.combo)
} tail = pattern_record[nt_tail] {
$combo = self.collect(PatternRecordRule(self._solver, nt).combine_cons, $ID.text, $body.combo, $tail.combo)
}

;



// thing returns [str result]: 
//     | 'fun' param = expr '=>' body = expr {
//         $result = f'(fun {$param.result} {$body.result})'
//     }
//     ;

// things : 
//     | thing
//     | things thing
//     ;

// typ :
//     | 'unit'
//     | 'top'
//     | 'bot'
//     | ID '//' typ 
//     | ID 'in' typ 
//     | typ '&' typ 
//     | typ '*' typ 
//     | typ '|' typ 
//     | typ '->' typ 
//     | '{' ID ('<:' typ)? ('with' typ '<:' typ)* ('#' (ID)+)? '}'
//     | '[' ID ('<:' typ)? ']' typ 
//     | ID '@' typ 
//     ;


ID : [a-zA-Z]+ ;
INT : [0-9]+ ;
WS : [ \t\n\r]+ -> skip ;

////////////////////////////////////////////////////
/** Grammar from tour chapter augmented with actions */
// grammar Slim;

// @header {
// }

// @parser::members {
// @property
// def memory(self):
//     if not hasattr(self, '_map'):
//         setattr(self, '_map', {})
//     return self._map
    
// @memory.setter
// def memory_setter(self, value):
//     if not hasattr(self, '_map'):
//         setattr(self, '_map', {})
//     self._map = value
    
// def eval(self, left, op, right):
//     if   ExprParser.MUL == op.type:
//         return left * right
//     elif ExprParser.DIV == op.type:
//         return left / right
//     elif ExprParser.ADD == op.type:
//         return left + right
//     elif ExprParser.SUB == op.type:
//         return left - right
//     else:
//         return 0
// }

// stat:   e NEWLINE           {print($e.v);}
//     |   ID '=' e NEWLINE    {self.memory[$ID.text] = $e.v}
//     |   NEWLINE                   
//     ;

// e returns [int v]
//     : a=e op=('*'|'/') b=e  {$v = self.eval($a.v, $op, $b.v)}
//     | a=e op=('+'|'-') b=e  {$v = self.eval($a.v, $op, $b.v)}
//     | INT                   {$v = $INT.int}    
//     | ID
//       {
// id = $ID.text
// $v = self.memory.get(id, 0)
//       }
//     | '(' e ')'             {$v = $e.v}       
//     ; 

// MUL : '*' ;
// DIV : '/' ;
// ADD : '+' ;
// SUB : '-' ;

// ID  :   [a-zA-Z]+ ;      // match identifiers
// INT :   [0-9]+ ;         // match integers
// NEWLINE:'\r'? '\n' ;     // return newlines to parser (is end-statement signal)
// WS  :   [ \t]+ -> skip ; // toss out whitespace
