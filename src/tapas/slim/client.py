from typing import *
import sys
from antlr4 import *
import sys

import asyncio
from asyncio import Queue

from tapas.slim.SlimLexer import SlimLexer
from tapas.slim.SlimParser import SlimParser

import tapas.util_system

from tapas.slim import server
'''
NOTE: lexer does NOT preserve skipped lexicon (e.g. skipped white space)
NOTE: there is a built in mechanism to stringify partial parse tree as s-expression
NOTE: NO built in mechanism to build indented string representation of parse tree
NOTE: NO built in mechanism to concretize partial parse tree. must implement custom attribute rules.

TODO: determine how to build parse tree of incomplete token stream?
TODO: determine how to use BufferedInputStream to put tokens on the stream one by one 
'''


def newline_column_count(x : str) -> tuple[int, int]:
    lines = x.split("\n")
    return (len(lines) - 1, len(lines[-1]))



async def _mk_task():
    # pieces = [

    #     "fix (self =>", " (", "\n",
    #     "    fun :nil . => :zero () ", "\n",
    #     "    fun :cons x ", "=>", ":succ", "(self(", "x))", "\n",
    #     ")", ")"
    # ]

    # pieces = [
    #     "hello"
    # ]

    # pieces = [
    #     ":hello ()"
    # ]

    pieces = [
        '''
        let y = :foo ()
        y 
        ''' 
        # "fix (", "()", ")"
        # 'x'
        ,
        server.Kill()
    ]


    results = []

    connection = server.launch()

    # pieces = [
    # f'''
    # fun :nil () => :zero () 
    # fun :cons () => :succ (self(x))
    # '''
    # ]


    for piece in pieces:
        answr = await connection.mk_caller(piece)
        print(f'answr: {answr}')
        if isinstance(answr, server.Done):
            break


    print('post while')
    result = await connection.mk_getter()
    print(f'result: {result}')


def interact():
    asyncio.run(_mk_task())
