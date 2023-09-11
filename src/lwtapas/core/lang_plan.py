from __future__ import annotations
from core.rule_system import Rule, Vocab, Terminal, Nonterm 
from core.line_format_system import NewLine, InLine, IndentLine

from lwtapas.core.schema_system import Schema

content = Schema([],
{
    "Typ" : [
        Rule("Var", [
            Vocab("name", "identifier"),
        ]),
        Rule("Exis", [
            Nonterm("body", "Typ", InLine()),
            Nonterm("qualifiers", "ListQual", InLine()),
            Nonterm("indicies", "ListIdent", InLine()),
        ]),
        Rule("Univ", [
            Vocab("index", "identifier"),
            Nonterm("upper_bound", "Typ", InLine()),
            Nonterm("body", "Typ", InLine()),
        ]),
        Rule("Induc", [
            Vocab("fixedpoint", "identifier"),
            Nonterm("body", "Typ", InLine()),
        ]),
        Rule("Union", [
            Nonterm("left", "Typ", InLine()),
            Nonterm("right", "Typ", InLine()),
        ]),
        Rule("Inter", [
            Nonterm("left", "Typ", InLine()),
            Nonterm("right", "Typ", InLine()),
        ]),
        Rule("Top", []),
        Rule("Bot", []),
        Rule("Unit", []),
        Rule("Tag", [
            Vocab("label", "discriminator"),
            Nonterm("body", "Typ", InLine()),
        ]),
        Rule("Field", [
            Vocab("label", "selector"),
            Nonterm("body", "Typ", InLine()),
        ]),
        Rule("Impli", [
            Nonterm("ante", "Typ", InLine()),
            Nonterm("consq", "Typ", InLine()),
        ])
    ],
    "ListQual" : [
        Rule("NilQual", []),
        Rule("ConsQual", [
            Nonterm("lower", "Typ", InLine()),
            Nonterm("upper", "TYp", InLine()),
            Nonterm("qualifiers", "ListQual", InLine()),
        ]),
    ],
    "ListIdent" : [
        Rule("NilIdent", []),
        Rule("ConsIdent", [
            Vocab("name", "identifier"),
            Nonterm("identifiers", "ListIdent", InLine()),
        ]),
    ],

    "Expr" : [
        Rule("Var", [
            Vocab("name", "identifier"),
        ]),
        Rule("Unit", []),
        Rule("Tag", [
            Vocab("label", "discriminator"),
            Nonterm("body", "Expr", InLine()),
        ]),
        Rule("Record", [
            Nonterm("fields", "ListField", InLine()),
        ]),
        Rule("Function", [
            Vocab("param", "identifier"),
            Nonterm("fields", "ListField", InLine()),
        ]),
        Rule("Match", [
            Nonterm("switch", "Expr", InLine()),
            Nonterm("branches", "ListBranch", InLine()),
        ]),
        Rule("Project", [
            Nonterm("target", "Expr", InLine()),
            Vocab("label", "selection"),
        ]),
        Rule("App", [
            Nonterm("function", "Expr", InLine()),
            Nonterm("arg", "Expr", InLine()),
        ]),
        Rule("Letb", [
            Vocab("param", "identifier"),
            Nonterm("annotation", "Typ", InLine()),
            Nonterm("arg", "Expr", InLine()),
            Nonterm("body", "Expr", InLine()),
        ]),
        Rule("Fix", [
            Nonterm("body", "Expr", InLine()),
        ]),
    ],
    "ListField" : [
        Rule("NilField", []),
        Rule("ConsField", [
            Vocab("label", "discriminator"),
            Nonterm("body", "Expr", InLine()),
            Nonterm("fields", "ListField", InLine()),
        ]),
    ],
    "ListBranch" : [
        Rule("NilBranch", []),
        Rule("ConsBranch", [
            Nonterm("pattern", "Expr", InLine()),
            Nonterm("body", "Expr", InLine()),
            Nonterm("branches", "ListBranch", InLine()),
        ]),
    ]
}
)