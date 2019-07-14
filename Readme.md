# Learn Functional Programming course/tutorial on Scala
Solutions by @spavikevik.

### Type classes ###

- [x] Observe general type class pattern in `learnfp/typeclass/TypeClass.scala`.
- [x] Implement `learnfp/typeclass/TotalOrder.scala`
- [x] Implement `learnfp/typeclass/Show.scala`
- [x] Implement `learnfp/typeclass/Eq.scala`
- Extra material: 
  - https://blog.scalac.io/2017/04/19/typeclasses-in-scala.html

### Monoids ###

- [x] Observe general monoid pattern in `learnfp/monoid/Monoid.scala`
- [x] Implement `learn-fp/src/main/scala/learnfp/monoid/ListMonoid.scala`
- [x] Implement `learn-fp/src/main/scala/learnfp/monoid/SimpleMonoid.scala`
- [x] Implement `learnfp/monoid/PairAdditiveMonoid.scala`
- Extra material:
  - Bartosz Milewski: Category Theory 3.1: Examples of categories, orders, monoids
  

### Functors ###

- [x] Observe general functor pattern in `learnfp/functor/Functor.scala`
- [x] Implement `learnfp/functor/Id.scala`
- [x] Implement `learnfp/functor/Maybe.scala`
- [x] Implement `learnfp/functor/List.scala`
- [x] Implement `learnfp/functor/Disjunction.scala`
- [x] Implement `learnfp/functor/Writer.scala`
- [x] Implement `learnfp/functor/State.scala`
- Extra material:
  - http://learnyouahaskell.com/functors-applicative-functors-and-monoids
  - https://thedet.wordpress.com/2012/04/28/functors-monads-applicatives-can-be-so-simple/
  - Bartosz Milewski: Category Theory 6.1: Functors 

### Monads ###

- [x] Observe general monad pattern in `learnfp/monad/Monad.scala`
- [x] Implement `learnfp/monad/Id.scala`
- [x] Implement `learnfp/monad/Maybe.scala`
- [x] Implement `learnfp/monad/List.scala`
- [x] Implement `learnfp/monad/Disjunction.scala`
- [x] Implement `learnfp/monad/Writer.scala`
- [x] Implement `learnfp/monad/State.scala`
- Extra material
  - Brian Beckman: Don't fear the Monad
  - http://eed3si9n.com/learning-scalaz/Monad+transformers.html
  
### Foldable ###

- [x] Implement foldable in `learnfp/foldable/Foldable.scala`

### Applicatives ###

- [x] Observe general applicative pattern in `learnfp/applicative/Applicative.scala`
- [x] Implement `learnfp/applicative/Id.scala`
- [x] Implement `learnfp/applicative/Maybe.scala`
- [x] Implement `learnfp/applicative/List.scala`
- [x] Implement `learnfp/applicative/Disjunction.scala`
- [x] Implement `learnfp/applicative/Writer.scala`
- [x] Implement `learnfp/applicative/State.scala`
- Extra material 
  - https://en.wikibooks.org/wiki/Haskell/Applicative_functors
  - http://eed3si9n.com/learning-scalaz/Applicative.html
  
### Traversable ###

- [ ] Implement `learnfp/traversable/Traversable.scala`

### Nested ###

- [ ] Implement `learnfp/nested/Nested.scala`

### IO ###

- [ ] Implement `learnfp/nested/IO.scala` 

### Monad Transformers ###

- [ ] Observe general monad transformer typeclass in `learnfp/transformer/MonadTransformer.scala`
- [ ] Implement `learnfp/transformer/IdT.scala`
- [ ] Implement `learnfp/transformer/MaybeT.scala`
- [ ] Implement `learnfp/transformer/WriterT.scala`
- [ ] Implement `learnfp/transformer/StateT.scala`
- Extra material: 
  - http://eed3si9n.com/learning-scalaz/Monad+transformers.html

### Free monad ###

- [ ] Implement `learnfp/free/Free.scala` and pass all unit tests in `learn-fp/src/test/scala/learnfp/free/FreeTest.scala`
- Extra material:
  - http://blog.krobinson.me/posts/monads-part-2-the-free-monad/
  - https://underscore.io/blog/posts/2015/04/14/free-monads-are-simple.html
  
### Contravariant functor ###

- [ ] Observe general contravariant functor pattern in `learnfp/contravariant/ContravariantFunctor.scala`
- [ ] Implement `learnfp/contravariant/Show.scala`
- [ ] Implement `learnfp/contravariant/Predicate.scala`

### CoMonads ###

- [ ] Observe general comonad pattern in `learnfp/comonad/CoMonad.scala`
- [ ] Implement `learnfp/comonad/Id.scala`
- [ ] Implement `learnfp/comonad/Env.scala`
  
### Bonus ###

- [ ] Implement Reader functor, monad, applicative and write unit tests for that
- [ ] Implement ReaderT and write unit tests for that
- [ ] Implement applicative for monad transformers
- [ ] Implement applicative for Free
  
# Credits #

- Yuriy Netesov ([@dehun](https://github.com/dehun))
