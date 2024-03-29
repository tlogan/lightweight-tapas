from __future__ import annotations

from dataclasses import dataclass
from typing import Dict, TypeVar, Any, Generic, Union, Optional, Iterable
from collections.abc import Callable, Sequence

from abc import ABC, abstractmethod
import json

from pyrsistent import PMap, PSet, pset

from pyrsistent import pmap, m, pset, s, PMap, PSet

import pickle

import inspect

T = TypeVar('T')
X = TypeVar('X')
K = TypeVar('K')
V = TypeVar('V')
R = TypeVar('R')

@dataclass(frozen=True, eq=True)
class StackResult(Generic[T]):
    content : T 


def make_stack_machine(
    make_plate_entry : Callable[[T], tuple[list[T], Callable[..., R]]], 
) -> Callable[[T], R] :
    def run(start : T):
        result : Optional[StackResult[R]] = None 
        stack : list[tuple[list[T], Callable, list[R]]] = [([start], (lambda x : x), [])]

        while len(stack) > 0 :
            (controls, combine, args) = stack.pop()

            if isinstance(result, StackResult):
                args.append(result.content)

            assert isinstance(combine, Callable)
            if len(controls) == 0:
                result = StackResult(combine(*args))
            else:
                result = None 
                control = controls.pop(0)
                stack.append((controls, combine, args))
                plate_entry = make_plate_entry(control)
                plate = (plate_entry[0], plate_entry[1], []) 
                stack.append(plate)

            pass

        assert isinstance(result, StackResult)
        return result.content
    return run




def box(x : Optional[T]) -> Iterable[T]:
    if x:
        return [x]
    else:
        return []

def unbox(xs : Iterable[T]) -> Optional[T]:
    return next((x for x in xs), None)


# x = unbox(for thing in box(x)
# )


def linearize_dict(d : dict) -> list: 
    return ['{'] + [
        item
        for k, v in d.items()
        for item in [k] + (
            linearize_dict(v) if isinstance(v, dict) else
            linearize_list(v) if isinstance(v, list) else
            linearize_tuple(v) if isinstance(v, tuple) else
            [v]
        )
    ] +  ['}']

def linearize_list(xs : list) -> list: 
    return ['['] + [
        item
        for x in xs
        for item in (
            linearize_dict(x) if isinstance(x, dict) else
            linearize_list(x) if isinstance(x, list) else
            linearize_tuple(x) if isinstance(x, tuple) else
            [x]
        )
    ] + [']']

def linearize_tuple(xs : tuple) -> list: 
    return ['('] + [
        item
        for x in xs
        for item in (
            linearize_dict(x) if isinstance(x, dict) else
            linearize_list(x) if isinstance(x, list) else
            linearize_tuple(x) if isinstance(x, tuple) else
            [x]
        )
    ] + [')']


class InsertOrderMap(Generic[K,V]):
    _d : PMap[K,V]
    _keys : tuple[K, ...]

    def __init__(self, d : PMap[K, V] = pmap({}), keys : tuple[K, ...] = ()):
        self._d = d 
        self._keys = keys 

    def __iter__(self):
        for k in self._keys: yield k

    def __eq__(self : InsertOrderMap[K,V], other : InsertOrderMap[K,V]) -> bool:
        return self._d == other._d and self._keys == other._keys

    def get(self, k : K):
        return self._d.get(k)
        
    def __getitem__(self, k : K):
        return self._d[k]

    def __add__(self : InsertOrderMap[K,V], other : InsertOrderMap[K,V]) -> InsertOrderMap:
        d = self._d + other._d
        keys = tuple(k
            for k in self.keys()
            if k not in other
        ) + other.keys()
        return InsertOrderMap(d, keys)

    # def __iadd__(self, other):
    #     return self + other

    def __contains__(self : InsertOrderMap[K,V], k : K):
        return k in self._d

    def remove(self : InsertOrderMap[K,V], k : K):
        d = self._d.remove(k) 
        keys = tuple(k1 for k1 in self._keys if k1 != k)
        return InsertOrderMap(d, keys)

    def items(self : InsertOrderMap[K,V]):
        for k in self._keys:
            yield (k, self._d[k]) 

    def keys(self):
        return self._keys

    def values(self):
        for k in self._keys:
            yield self._d[k]
    
    def __len__(self):
        return len(self._keys)


# def iom(d : PMap[K, V] = pmap({}), l : tuple[K, ...] = ()) -> InsertOrderMap:
def iom(*pair_list : tuple[K, V]) -> InsertOrderMap[K, V]:
    result = InsertOrderMap[K, V]() 
    for (k, v) in pair_list:
        result += InsertOrderMap(pmap({k : v}), (k,))
    return result

class Ref(Generic[T]): 
    def __init__(self, item : T):
        self.item  = item 

def fail(msg : str):
    raise Exception(msg)

def match_d(k : T, d : dict[T, Callable[[], Any]], error_msg):
    return d.get(k, lambda: fail(error_msg))()

def exists(it : Iterable[T], f : Callable[[T], bool]) -> bool:
    for item in it:
        if f(item):
            return True
    return False

def every(it : Iterable[T], f : Callable[[T], bool]) -> bool:
    return not exists(it, lambda t : not f(t))


def merge_psets(sets : Iterable[PSet[T]]) -> PSet[T]:
    ts : PSet[T] = pset() 
    for x in sets:
        ts = ts.update(x)
    return ts


import os
import pathlib
# import logging

# logging.basicConfig(level=logging.INFO)


def project_path(rel_path : str):
    base_path = os.path.join(pathlib.Path(__file__).parent.absolute(), '../..')
    return os.path.abspath(os.path.join(base_path, rel_path))

def all_paths(base_root : str) -> list[str]:
    paths = []
    for root,_,files in os.walk(base_root, topdown=True):
        prefix = (
            ""
            if root == base_root else
	    root[len(base_root) + 1:] + '/'
        )
        for f in files:
            paths.append(prefix + f)
    return paths


def write(dirpath : str, fname : str, code : str, append : bool = False):
    if not os.path.exists(dirpath):
        os.makedirs(dirpath)

    fpath = os.path.join(dirpath, f"{fname}")

    with open(fpath, 'a' if append else 'w') as f:
        # logging.info(f"Writing file: {fpath}")
        f.write(code)

def write_code(package_name, name, content):
    dirpath = project_path(f'{package_name}')
    return write(dirpath, f"{name}_autogen.py", f"# THIS FILE IS AUTOGENERATED\n# CHANGES MAY BE LOST\n{content}")

def run_jsonl_file(fpath : str, func : Callable[[str], Any]):
    error_count = 0

    with open(fpath, 'r') as f:
        #note: example 101 originally had a typo of using equality '==' instead of assignment '='
        count = 1

        line = f.readline()
        while line: 
            line_obj = json.loads(line)

            concrete = line_obj['code']
            func(concrete)

            # update
            line = f.readline()
            count += 1

        print(f"ERROR COUNT {error_count}")

def run_file(fpath : str, func : Callable[[str], Any]):
    with open(fpath, 'r') as f:
        concrete = f.read()
        func(concrete)




def save_object(o : Any, rel_path : str) -> Any:
    path = project_path(rel_path)
    with open(path, 'wb') as f:
        pickle.dump(o, f)
    return o

def load_object(rel_file : str) -> Any:
    path = project_path(rel_file)
    with open(path, 'rb') as f:
        return pickle.load(f)
