# THIS FILE IS AUTOGENERATED
# CHANGES MAY BE LOST



from __future__ import annotations

from dataclasses import dataclass
from typing import Dict, TypeVar, Any, Generic, Union, Optional
from collections.abc import Callable

from abc import ABC, abstractmethod

T = TypeVar('T')


@dataclass(frozen=True, eq=True)
class SourceFlag: 
    pass



    


# type AbstractToken
@dataclass(frozen=True, eq=True)
class AbstractToken(ABC):
    @abstractmethod
    def match(self, handler : AbstractTokenHandler[T]) -> T:
        pass

# constructors for type AbstractToken

@dataclass(frozen=True, eq=True)
class Grammar(AbstractToken):
    key : str
    selection : str

    def match(self, handler : AbstractTokenHandler[T]) -> T:
        return handler.case_Grammar(self)

def make_Grammar(
    key : str, 
    selection : str
) -> AbstractToken:
    return Grammar(
        key,
        selection
    )

def update_Grammar(source_Grammar : Grammar,
    key : Union[str, SourceFlag] = SourceFlag(),
    selection : Union[str, SourceFlag] = SourceFlag()
) -> Grammar:
    return Grammar(
        source_Grammar.key if isinstance(key, SourceFlag) else key,
        source_Grammar.selection if isinstance(selection, SourceFlag) else selection
    )

        

@dataclass(frozen=True, eq=True)
class Vocab(AbstractToken):
    key : str
    selection : str

    def match(self, handler : AbstractTokenHandler[T]) -> T:
        return handler.case_Vocab(self)

def make_Vocab(
    key : str, 
    selection : str
) -> AbstractToken:
    return Vocab(
        key,
        selection
    )

def update_Vocab(source_Vocab : Vocab,
    key : Union[str, SourceFlag] = SourceFlag(),
    selection : Union[str, SourceFlag] = SourceFlag()
) -> Vocab:
    return Vocab(
        source_Vocab.key if isinstance(key, SourceFlag) else key,
        source_Vocab.selection if isinstance(selection, SourceFlag) else selection
    )

        

# case handler for type AbstractToken
class AbstractTokenHandler(ABC, Generic[T]):
    @abstractmethod
    def case_Grammar(self, o : Grammar) -> T :
        pass
    @abstractmethod
    def case_Vocab(self, o : Vocab) -> T :
        pass

     

 
    