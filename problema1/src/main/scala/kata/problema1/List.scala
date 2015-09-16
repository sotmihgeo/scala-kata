package kata.problema1

sealed trait List[+T] {
  /**
   * Dându-se o funcție de mapare de la valori de tip T la
   * valori de tip U, transformă Lista sursă într-o Listă
   * de elemente de tip U.
   */
  def map[U](f: T => U): List[U] = ???

  /**
   * Dându-se o funcție ce mapează elemente de tip T
   * la Liste de elemente de U, returnează o Listă
   * de elemente de tip U.
   */
  def flatMap[U](f: T => List[U]): List[U] = ???

  /**
   * Dacă Lista sursă este o Listă de Liste cu elemente
   * de tip U, atunci concatenează Listele și returnează
   * rezultatul.
   */
  def flatten[U](implicit ev: T <:< List[U]): List[U] = ???

  /**
   * Dându-se o funcție predicat ce returnează adevăra sau
   * fals pentru fiecare element, returnează o nouă Listă ce
   * conține doar elementele pentru care predicatul a fost
   * adevărat.
   */
  def filter(p: T => Boolean): List[T] = ???

  /**
   * Returnează Lista sursă cu elementele în ordine inversă.
   */
  def reverse: List[T] = ???

  /**
   * Prefixează Lista sursă cu elementul dat.
   */
  def ::[U >: T](head: U): List[U] = ???

  /**
   * Prefixează Lista sursă cu elementul dat.
   */
  def +:[U >: T](head: U): List[U] = ???

  /**
   * Adaugă la sfârșit elementul dat.
   */
  def :+[U >: T](elem: U): List[U] = ???

  /**
   * Parcurge Lista de la cap la coadă și apelează
   * funcția dată pentru fiecare element.
   */
  def foreach[U](f: T => U): Unit = ???
}

case class Cons[+T](head: T, tail: List[T]) extends List[T]
case object Nil extends List[Nothing]

object List {
  /** Constructor pentru List */
  def apply[T](elems: T*): List[T] =
    elems.reverse.foldLeft(empty[T]) {
      case (Nil, head) => Cons(head, Nil)
      case (tail, head) => Cons(head, tail)
    }

  /** Returnează o Listă goală */
  def empty[T]: List[T] = Nil
}
