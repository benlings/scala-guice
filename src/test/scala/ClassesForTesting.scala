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

import com.google.inject.Provider

class GenStringProvider extends Provider[Gen[String]] {
  def get = new C
}

class CProvider extends Provider[C] { 
  def get = new C
}
