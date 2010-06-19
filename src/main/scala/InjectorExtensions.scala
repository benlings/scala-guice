package uk.me.lings.scalaguice

import com.google.inject.Injector

object InjectorExtensions {
  
  import KeyExtensions._
  
  class ScalaInjector(i:Injector) {
    def instance[T : Manifest] = i.getInstance(typeLiteral[T].toKey)
  }
  
  implicit def enrichInjector(i:Injector) = new ScalaInjector(i)
}
