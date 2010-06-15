package uk.me.lings.scalaguice

import com.google.inject._

object BindingExtensions {
    
    class ScalaBinder(b:Binder) {
        def bindType[T : Manifest] = b bind typeLiteral[T]
    }
    
    implicit def enrichBinder(b:Binder) = new ScalaBinder(b) 
    
    import com.google.inject.binder._
    import java.lang.annotation.{Annotation => JAnnotation}
    
    class ScalaScopedBindingBuilder(b: ScopedBindingBuilder) {
        def inType[TAnn <: JAnnotation : ClassManifest] = b in annotation[TAnn]
    }
    
    implicit def enrichScopedBindingBuilder(b: ScopedBindingBuilder) = new ScalaScopedBindingBuilder(b)

    class ScalaLinkedBindingBuilder[T](b: LinkedBindingBuilder[T]) {
        def toType[TImpl <: T : Manifest] = b to typeLiteral[TImpl]
    }
    
    implicit def enrichLinkedBinding[T](b: LinkedBindingBuilder[T]) = 
      new ScalaLinkedBindingBuilder[T](b)
    
    class ScalaAnnotatedBindingBuilder[T](b: AnnotatedBindingBuilder[T]) {
        def annotatedWithType[TAnn <: JAnnotation : ClassManifest] = 
          b annotatedWith annotation[TAnn]
    }

    implicit def enrichAnnotatedBinding[T](b: AnnotatedBindingBuilder[T]) = 
      new ScalaAnnotatedBindingBuilder[T](b)
    
    class ScalaAnnotatedConstantBindingBuilder(b: AnnotatedConstantBindingBuilder) {
        def annotatedWithType[TAnn <: JAnnotation : ClassManifest] = 
          b annotatedWith annotation[TAnn]
    }
    
    implicit def enrichAnnotatedConstantBindingBuilder(b: AnnotatedConstantBindingBuilder) =
      new ScalaAnnotatedConstantBindingBuilder(b)

    class ScalaConstantBindingBuilder(b: ConstantBindingBuilder) {
        def toType[T: ClassManifest] = b to classManifest[T].erasure
    }
    
    implicit def enrichConstantBinding(b: ConstantBindingBuilder) =
      new ScalaConstantBindingBuilder(b)
    
}

