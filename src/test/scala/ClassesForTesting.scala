package uk.me.lings.scalaguice

object Outer {
  trait A
  class B extends A
  trait Gen[T] {
    def get: T
  }

  class C extends Gen[String] {
    def get = "String"
  }
}

trait A
class B extends A

trait Gen[T] {
  def get: T
}

class C extends Gen[String] {
  def get = "String"
}

