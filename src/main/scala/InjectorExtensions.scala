package uk.me.lings.scalaguice

import com.google.inject.Injector

object InjectorExtensions {
    
    class ScalaInjector(i:Injector) {
        def instance[T : Manifest] = i.getInstance(typeLiteral[T])
    }
    
    implicit def enrichInjector(i:Injector) = new ScalaInjector(i)
}