package kata.problema1

import scala.annotation.tailrec
import minitest.SimpleTestSuite

object ListSuite extends SimpleTestSuite {
  test("map") {
    try {
      val l1 = List(1,2,3,4,5)
      val l2 = l1.map(_ + 1)
      assertEquals(l2, List(2,3,4,5,6))
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("bigList.map") {
    try {
      val mapped = List((0 until 1000000): _*).map(_ * 2)
      val expected = List((0 until 1000000).map(_ * 2): _*)
      assertEquals(mapped, expected)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("empty.map") {
    try {
      val l = List.empty[Int].map(_ + 1)
      assertEquals(l, Nil)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("filter") {
    try {
      val l1 = List(1,2,3,4,5,6)
      val l2 = l1.filter(_ % 2 == 0)
      assertEquals(l2, List(2,4,6))
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("bigList.filter") {
    try {
      val mapped = List((0 until 1000000): _*).filter(_ % 2 == 0)
      val expected = List((0 until 1000000).filter(_ % 2 == 0): _*)
      assertEquals(mapped, expected)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("empty.filter") {
    try {
      val l = List.empty[Int].filter(_ % 2 == 0)
      assertEquals(l, Nil)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("flatMap") {
    try {
      val l1 = List(1,2,3,4,5,6)
      val l2 = l1.flatMap(x => List(x,x))
      assertEquals(l2, List(1,1,2,2,3,3,4,4,5,5,6,6))
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("bigList.flatMap") {
    try {
      val mapped = List((0 until 1000000): _*).flatMap(x => List(x,x))
      val expected = List((0 until 1000000).flatMap(x => Seq(x,x)): _*)
      assertEquals(mapped, expected)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("empty.flatMap") {
    try {
      val l = List.empty[Int].flatMap(x => List(x,x))
      assertEquals(l, Nil)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("reverse") {
    try {
      val l1 = List(1,2,3,4,5,6)
      val l2 = l1.reverse
      assertEquals(l2, List(6,5,4,3,2,1))
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("bigList.reverse") {
    try {
      val mapped = List((0 until 1000000): _*).reverse
      val expected = List((0 until 1000000).reverse: _*)
      assertEquals(mapped, expected)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("empty.reverse") {
    try {
      val l = List.empty[Int].reverse
      assertEquals(l, Nil)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("prepend ::") {
    try {
      val l = 1 :: List(2,3,4,5,6)
      assertEquals(l, List(1,2,3,4,5,6))
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("prepend +:") {
    try {
      val l = 1 +: List(2,3,4,5,6)
      assertEquals(l, List(1,2,3,4,5,6))
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("append") {
    try {
      val l = List(1,2,3,4,5) :+ 6
      assertEquals(l, List(1,2,3,4,5,6))
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("bigList.append") {
    try {
      val l = List((0 until 1000000): _*) :+ 1
      val expected = List(((0 until 1000000) :+ 1): _*)
      assertEquals(l, expected)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  test("foreach") {
    try {
      var sum = 0L
      val list = List((0 until 1000000): _*)
      for (x <- list) sum += x
      val expected = 500000L * 999999
      assertEquals(sum, expected)
    }
    catch {
      case _: NotImplementedError =>
        ignore()
    }
  }

  def assertEquals[T](given: List[T], expected: List[T]): Unit = {
    @tailrec
    def loop(self: List[T], other: List[T]): Boolean =
      if ((self eq Nil) || (other eq Nil)) self eq other
      else {
        val selfC = self.asInstanceOf[Cons[T]]
        val otherC = other.asInstanceOf[Cons[T]]
        if (selfC.head != otherC.head) false
        else loop(selfC.tail, otherC.tail)
      }

    assert(loop(given, expected), s"equality failed")
  }
}
