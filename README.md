# Lightweight Tapas


### TODO
- read about and understand duality interpolation for horn clauses
- write first draft of paper for ICFP  
- determine rationale for calls to unify in distill, combine, or leaf combine steps.
- fill in attribute rule implementation 
- update collect and guide_choice rules to memo(r)ize
- modify unify to return multiple interpretations 
- write unification with outsourcing to Z3 
- write tree listener for printing out indented parse tree 
- instead of using custom solver, translate leaf subtyping into horn clauses for external solver
- use renaming lazily (just for equality modulo alpha renaming)

### Implementation 
- update guidance before each part of a sequence
- update overflow after each guidance update 
- update result after entire sequence
- after each call to server, server responds with some guidance, completion status, or acknowledgment of killing.   
- inherit attributes via guide and distill methods
- synthesize attributes via collect and combine methods

### Title
- Guiding safe generation of untyped programs   


### Context
- Generation of programs one token at a time from left to right
- Generation can be parameterized by analysis of previous results 

- inductive invariant for expressions is a property that holds for every possible expression that can be constructed according to a recursion 

- Constraints are symbolic representations of interpretations

- Predicates are symbolic representations of sets of expressions

- CEGAR abstracts the model and iteratively refines it  
    - makes the problem hard and iteratively makes it easier
    - refutation of a counter-example is a necessary condition of the concrete model 
        - the necessary condition refines the abstract model, making verification easier

- SYNGAR abstracts the specification and iteratively refines it
    - makes the problem easy and iteratively makes it harder 
    - spuriousness/incorrectness on an I/O example is a sufficient condition for negating the concrete specification
        - the sufficient condition refines the abstract specification, making verification harder

- interpolation is the construction of an interpretation and a valid formula from an instance/derivation constrained by a specification      
    - interpolation is simply used to find a generalizable refinement from some instance
    - e.g. `P_instance <: P_interp <: P_spec`
    - construction of formulas with disjunction and conjunction represents construction of an interpolant 
    - updating the interpretation propagates the interpolant to higher levels 

- predicate abstraction requires a universe of predicates for constructing the interpolation

- duality does not require a universe of predicates and relies on union/disjunction to construct interpolation


### Gap
- Pure statical-learning methods (LLMs) generate unsafe programs
- Symbolic generation methods (Synquid) are limited to intrinsically typed languages 
- Left-to-right guidance (Neural Program Generation Modulo Static Analysis) is limited to heavily annotated programming languages
- CHC solving methods cannot provide inference to guide program completion 

### Innovation 
- Guidance of program synthesis from context a la duality interpolation 
    - generalizes the Synquid's notion of synthesis via predicate abstraction to synthesis via duality
    - generalizes left-to-right guidance to extrinsically typed and unannotated expression languages 
- Connection of bidirectional typing with unions and intersection to interpolation via the duality algorithm
    - the program represents an instance/derivation tree and its type under the interpretation constructed by bidirectional typing represents the interpolation   
    - construction of types with unions or intersections represents construction of an interpolant 
    - updating the interpretation propagates the interpolant to higher levels 
    - e.g. for `foo(e)`, `foo : A -> B`, `e : T`, the interpolant is `I` where `T <: I <: A`
- Duality analysis on streaming/partial programs 
    - duality analysis on recursive-decent parse-tree 
    - duality analysis of top-down parse-tree (without left-recursion) for left-associative semantics. 
    - adds layer of complexity that's not apparent in a normalized AST
- Relational types
    - extrinsic types 
    - unifies/decides/solves subtyping without base types refined by qualifiers
    - second order qualifiers allow natural expression of relational co-induction
    - lack of sorts prevents general propositions over proofs
    - the inhabitation of types/predicates may be viewed as existential propositions 
    - the inhabitation of a function type may be viewed as an implication between the inhabitation of each of the two subparts
    - the relational types of various parts of an untyped program can be expressed as 
        - a single algebraic datatype representing all possible tag/record constructions 
        - and various predicates/relations defined by horn clauses over the massive algebraic data type
    - a record type could be encoded as an uninterpreted function over their payload
        - e.g. maybe `x : {m1 : T1, m2 : T2}` could become `m1(x) : T1, m2(x) : T2 ==> x : P`
    - TODO: determine if there is an advantage to doing some unification before outsourcing to horn clause solver

    - typing/subtyping behave like constraints, which are symbolic representations of sets of interpretations

    - types behave like predicates, which are symbolic representations of sets of expressions

    - horn clauses map to relational subtyping
        - e.g. `P(y, y') /\ z = y' + 1 => P(y,z)`
        - into `(y, y') : P /\ (z, y' + 1) : Eq => (y,z) : P`
        - into `{Y * Z with (Y * Y') <: P, (Z * Y' + 1) <: Eq} <: P`
        - into `{Y * Z with (Y * Y') <: P, (Z * (:succ? Y')) <: Eq} <: P`
    - TODO: what's the advantage of relational subtyping over horn clauses?
        - provides a more compact representation for annotating programs

- Propagation of types
    - checking and guiding via propagation 
    - checking/solving via horn-clause solver when subtyping at leaves
    - type inference of application of cases without a specified upper bound:
        ```
        P <: A | C 
        Q <: {B with A <: P} | {D with C <: P}
        ---------------------------------------
        (A -> B) & (C -> D) <: (P -> Q)
        ```
        - syntactic check at unification; make sure that lhs antecedents are associated with rhs consequent's subparts 

### Future work
- use statistical learning to find necessary types 
    - verify that the found type is actually necessary (of the concrete model): i.e. `T_model <: T_nc`, `T_nc <: T_spec`
    - (analogous to finding and verifying refutation of a counter-example in CEGAR)
- use statistical learning to find interpolants, e.g. `T_nc <: T_i <: T_spec` 
- extend language to handle mutable references in synthesis from context


### Hypothesis
- Type information must be leniently extracted from programs based on compositions
- Type reasoning must be performed directly with the program 
    - in order to construct upper bound of program completion 

### Motivating examples

#### Heterogeneous generics
```
let x = [] 

let first : list[str] * top -> list[str] 
let first = (a , b) => a 

let _ = first (x, x)  

let y = x ++ [4]
```

After `let x`, the system infers
```
x : list[T]
```

After `let first`, the system infers
```
first : (list[str] * top) -> list[str]
```

After `let _ = first (x, x)`, the system infers
```
x : list[str] 
ok 
treat first as a constraint on the type of x
```

After `let y = x ++ [4]`, the system infers
```
++ : [T] list[T] * list[T] -> list[T]
strict option. error: list[int] ≤ list[str] 
lenient option. x : list[str | T], y : list[str | int | T]
```


#### Parameter refinement 
```
let g : (uno : G) -> unit = _ in 
let h : (dos : H) -> unit = _ in 
let f  = (x => g(x), h(x))
```

After `let f`, the system infers
```
f : (uno : G) & (dos : H) -> unit * unit
```

#### Overlapping pattern matching
```
let g : A -> G
let h : B -> H
let k : C -> K

def foo(x) =  
    match x 
    case uno;a => g(a)  
    case dos;b => h(b)

def boo(x) =  
    match x 
    case dos;b => h(b)
    case tres;c => k(c)  


let hplus : (H * H) -> nat 


def client(x) =
    let y = foo(x) in
    match y 
    case <pattern : H> => hplus(y, boo(x))
```

After `def foo`, the system infers
```
foo : [X] X -> {G with X <: uno//A} | {H with X <: dos//B}  
```

After `def boo`, the system infers
```
boo : [X] X -> {H with X <: dos//B} | {K with X <: tres//C}
```

In `def client`, the system infers
```
x : X 
X <: uno//A | dos//B  
y : Y
Y <: {G with X <: uno//A} | {H with X <: dos//B} 
-- OR ----
union [ X <: uno//A |- Y <: G,  X <: dos//B |- H ]
```

Towards the end, the system infers 
```
client : (uno//A | dos//B) & (dos//B) -> unit
client : dos//B -> nat 
```



<!-- 
--------
OLD
--------
--------

consider:

```
let x = [1]
-- x : list[int]

let first : (list[str] ; ?) -> list[str]
let first = (a , b) => a 
-- first : (list[str] ; ?) -> list[str]

let _ = first (x, ... 
-- error: list[int] ≤ list[str]
```


basic typing: <br> 
`Γ ⊢ t : τ` <br>

variable
```
(x : τ) ∈ Γ 
---------------------------                        
Γ ⊢ x : τ
```
 

application
```
Γ ⊢ t₁ : τ₂ -> τ₁
Γ ⊢ t₂ : τ₂'
Γ ⊩ τ₂' ≤ τ₂
------------------------------- 
Γ ⊢ t₁ t₂ : τ₁
```


example: is `first (x, ...` well-typed?
```
Γ ⊢ first : (list[str] ; ?) -> list[str] 
Γ ⊢ (x, ... : ... 
Γ ⊩ ... ≤ (list[str] ; ?)
--------------------------------------------------
Γ ⊢ first (x, ... : ...
```


basic supertyping: <br>
`Γ ⊢ t : τ ≥ τ` <br>

variable
```
(x : τ') ∈ Γ 
Γ ⊩ τ' ≤ τ
---------------------------                        
Γ ⊢ x : τ ≥ τ' 
```

application
```
Γ ⊢ t₁ : ? -> τ₁ ≥ τ₂ -> τ₁'
Γ ⊢ t₂ : τ₂ ≥ _ 
------------------------------- 
Γ ⊢ t₁ t₂ : τ₁ ≥ τ₁'
```


example: is `first (x, ...` well-typed?
```
(x : list[int]) ∈ Γ 
Γ ⊩ list[int] ≤ list[str]
--------------------------------------------------------------------
Γ ⊢ x : list[str] ≥ list[int]
```

consider:
```
let x = [] 
-- x : (∀ α ≤ ? . list[α])

let first : list[str] ; ? -> list[str] 
let first = (a , b) => a 
-- first : (list[str] ; ?) -> list[str]

let _ = first (x, x)  
-- ok 
```

polymorphic supertyping: <br>
`Γ ⊢ t : τ ≥ τ` <br>

variable
```
(x : ∀ Δ . τ') ∈ Γ 
Γ ⊩ (forall Δ . τ') ≤ τ
---------------------------                        
Γ ⊢ x : τ ≥ forall Δ . τ'
```


example: is `first(x, x)` well-typed?
```
(x : ∀ α ≤ ? . list[α]) ∈ Γ 
Γ ⊩ (forall α ≤ ? . list[α]) ≤ list[str]
--------------------------------------------------------------------
Γ ⊢ x : list[str] ≥ (forall α ≤ ? . list[α])
```



consider:
```
let x = [] 
-- x : (∀ α ≤ ? . list[α])

let first : list[str] ; ? -> list[str] 
let first = (a , b) => a 
-- first : (list[str] ; ?) -> list[str]

let _ = first (x, x)  
-- ok 
-- treat first as a constraint on the type of x
-- x : list[str] 

let y = x ++ [4]
-- ++ : ∀ {α} . list[α] ; list[α] -> list[α]
-- strict option. error: list[int] ≤ list[str] 
-- lenient option. x : forall α . list[str | α]
-- list[str] <: α ==> {a -> str | b, b -> ?}
-- [4] <: α ==> [4] <: {str | b} ==> 4 <: str \/ [4] <: b ==> {b -> [4] | c}
```

supertyping + constraints: <br>
`Γ ; C ⊢ t : τ ≥ τ ; C` <br>
`Γ ; C ⊩ C` <br>

variable
```
(x : ∀ Δ ⟨D⟩. τ') ∈ Γ 
Γ ; C ⊩ forall Δ ⟨D ∧ τ' ≤ τ⟩
-----------------------------------------------------             
Γ ; C ⊢ x : τ ≥ forall Δ . τ' ; (forall Δ ⟨D ∧ τ' ≤ τ⟩)
```
Note: cumbersome redundancy between supertyping and constraints
Note: type information may not be readable from constraints

supertyping * constraints, simple: <br>
`Γ ; C ⊢ t : τ ≥ τ` <br>
`Γ ; C ⊩ τ ≤ τ` <br>

variable
```
(x : ∀ Δ ⟨D⟩. τ') ∈ Γ 
Γ ; C ⊩ (forall Δ ⟨D⟩ .τ') ≤ τ
-----------------------------------------------------             
Γ ; C ⊢ x : τ ≥ forall Δ ⟨D ∧ τ' ≤ τ₁⟩ . τ'
```
Note: cumbersome redundancy between supertyping and constraints
Note: type information may not be readable from constraints


supertyping * constraints, eager unification: <br>
`Γ ; C ⊢ t : τ ≥ τ` <br>
`Γ ; C ⊩ τ ≤ τ ~> Δ ; D` <br>

variable
```
(x : ∀ Δ ⟨D⟩. τ') ∈ Γ 
Γ ; C ⊩ (forall Δ ⟨D⟩ .τ') ≤ τ ~> Δ' ; D'
-----------------------------------------------------             
Γ ; C ⊢ x : τ ≥ forall Δ' ⟨D'⟩ . τ'
```
Note: type information readable in incomplete program 


example: strict option
```
(x : forall α ≤ ? . list[α]) ∈ Γ 
Γ ⊩ (forall α ≤ ? . list[α]) ≤ list[str] ~> α ≤ str
--------------------------------------------------------------------
Γ ⊢ x : list[str] ≥ (forall α ≤ str . list[α])
```


example: lenient option
```
(x : forall α ≤ ? . list[α]) ∈ Γ 
Γ ⊩ (forall α ≤ ? . list[α]) ≤ list[str] ~> α ≤ (str | α)
--------------------------------------------------------------------
Γ ⊢ x : list[str] ≥ (forall α ≤ (str | α) . list[α])
``` -->


## Artifacts 

### Representation
- qualifiers 
    - second order (subtyping) vs first order (typing)
    - 2nd order makes annotations more uniform 
    - can express union types over existing type definitions or primitive constructors 
    - relies on subtyping qualifiers

### Token processing thread
- Parsing:
    - recursive descent; streaming left to right
- Analysis:
    - using single stack instead of mutual recursion
    - bidirectional/roundtrip propagation of types

### Token generation thread
- Testing 
    - feed in correct or incorrect 
- Neural model 


