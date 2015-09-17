package kata.problema1

import scala.annotation.tailrec

sealed trait List[+T] {
  /**
   * Dându-se o funcție de mapare de la valori de tip T la
   * valori de tip U, transformă Lista sursă într-o Listă
   * de elemente de tip U.
   */
  def map[U](f: T => U): List[U] = {
    @tailrec def mapHelper(f: T => U, lT: List[T], lU: List[U]): List[U] = {
	  lT match {
		case Cons(h, t) => mapHelper(f, t, Cons(f(h), lU))
	    case Nil => lU
	  }
	}
	
	mapHelper(f, this, Nil).reverse
  }

  /**
   * Dându-se o funcție ce mapează elemente de tip T
   * la Liste de elemente de U, returnează o Listă
   * de elemente de tip U.
   */
  def flatMap[U](f: T => List[U]): List[U] = this.map(f).flatten

  /**
   * Dacă Lista sursă este o Listă de Liste cu elemente
   * de tip U, atunci concatenează Listele și returnează
   * rezultatul.
   */
  def flatten[U](implicit ev: T <:< List[U]): List[U] = {
    def append(xs: List[U], ys: List[U]): List[U] = {
	  xs match {
	    case Cons(h, t) => Cons(h, append(t, ys))
		case Nil => ys
	  }
	}
	
	@tailrec def flattenHelper(l: List[T], lF: List[U]): List[U] = {
	  l match {
	    case Cons(h, t) => flattenHelper(t, append(h, lF))
	    case Nil => lF
	  }
	}
	
	flattenHelper(this, Nil).reverse
  }

  /**
   * Dându-se o funcție predicat ce returnează adevăra sau
   * fals pentru fiecare element, returnează o nouă Listă ce
   * conține doar elementele pentru care predicatul a fost
   * adevărat.
   */
  def filter(p: T => Boolean): List[T] = {
    @tailrec def filterHelper(p: T => Boolean, l: List[T], lF: List[T]): List[T] = {
	  l match {
	    case Cons(h, t) => {
		  val tlF = if (p(h)) { Cons(h, lF) } else { lF }
		  filterHelper(p, t, tlF)
		}
	    case Nil => lF
	  }
	}
	
	filterHelper(p, this, Nil).reverse
  }

  /**
   * Returnează Lista sursă cu elementele în ordine inversă.
   */
  def reverse: List[T] = {
    @tailrec def reverseHelper(l: List[T], lR: List[T]): List[T] = {
	  l match {
	    case Cons(h, t) => reverseHelper(t, Cons(h, lR))
		case Nil => lR
	  }
	}
	
	reverseHelper(this, Nil)
  }

  /**
   * Prefixează Lista sursă cu elementul dat.
   */
  def ::[U >: T](head: U): List[U] = Cons(head, this)

  /**
   * Prefixează Lista sursă cu elementul dat.
   */
  def +:[U >: T](head: U): List[U] = Cons(head, this)

  /**
   * Adaugă la sfârșit elementul dat.
   */
  def :+[U >: T](elem: U): List[U] = {
    (elem :: this.reverse).reverse
  }

  /**
   * Parcurge Lista de la cap la coadă și apelează
   * funcția dată pentru fiecare element.
   */
  def foreach[U](f: T => U): Unit = {
    @tailrec def foreachHelper(items: List[T]) {
	  items match {
	    case Cons(h, t) => {
		  f(h)
		  foreachHelper(t)
		}
		case Nil => {}
	  }
	}
  
    foreachHelper(this)
  }
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
