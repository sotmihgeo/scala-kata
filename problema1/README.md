# Problema 1

În Scala o listă simplu-înlănțuită și [persistentă](https://en.wikipedia.org/wiki/Persistent_data_structure)
ar putea avea definiția următoare ...

```scala
sealed trait List[+T]

case class Cons[+T](head: T, tail: List[T]) extends List[T]
case object Nil extends List[Nothing]
```

Misiunea ta, dacă o accepți, este să implementezi operațiile
`map`, `flatMap`, `filter`, `reverse`, `append`, `prepend`
și `foreach`. În fișierul
[List.scala](src/main/scala/kata/problema1/List.scala)
ai deja structura de date definită, iar în fișierul
[ListSuite.scala](src/test/scala/kata/problema1/ListSuite.scala)
ai suita de teste ce trebuie să treacă.

Ca și restricție **n-ai voie să folosești `var`**, sau orice construcție
alternativă ce-ți permite să lucrezi cu referințe mutabile.

## Mod de Lucru

Ai nevoie de:

- SBT, vezi [http://www.scala-sbt.org/download.html](http://www.scala-sbt.org/download.html)
- Git, vezi [https://help.github.com/articles/set-up-git/](https://help.github.com/articles/set-up-git/)
- un editor de texte (de exemplu [Atom](https://atom.io/)) sau un IDE
  (de exemplu [IntelliJ IDEA](https://www.jetbrains.com/idea/), Community Edition)

Clonează proiectul local:
```
> git clone git@github.com:scala-romania/scala-kata.git
```

Întră în consola SBT:
```
> cd scala-kata/
> sbt
```

Comută proiectul la `problema1` și rulează testele:
```
> project problema1
[info] Set current project to problema1

> test
```

Modifică fișierul [List.scala](src/main/scala/kata/problema1/List.scala) până
ajung toate testele pe verde.
