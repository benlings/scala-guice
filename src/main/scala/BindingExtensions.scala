package uk.me.lings.scalaguice

import com.google.inject._

object BindingExtensions {
    
    class ScalaBinder(b:Binder) {
        def bindType[T : Manifest] = b.bind(typeLiteral[T])
    }
    
    implicit def enrichBinder(b:Binder) = new ScalaBinder(b) 
    
    import com.google.inject.binder._
    import java.lang.annotation.{Annotation => JAnnotation}
    
    class ScalaAnnotatedBindingBuilder[T](b: AnnotatedBindingBuilder[T]) {
        def annotatedWithType[TAnn <: JAnnotation : ClassManifest] = b.annotatedWith(annotation[TAnn])
    }
    
    implicit def enrichAnnotatedBinding[T](b: AnnotatedBindingBuilder[T]) = new ScalaAnnotatedBindingBuilder[T](b)
    
    class ScalaAnnotatedConstantBindingBuilder(b: AnnotatedConstantBindingBuilder) {
        def annotatedWithType[TAnn <: JAnnotation : ClassManifest] = b.annotatedWith(annotation[TAnn])
    }
    
    implicit def enrichAnnotatedConstantBindingBuilder(b: AnnotatedConstantBindingBuilder) = new ScalaAnnotatedConstantBindingBuilder(b)

    class ScalaLinkedBindingBuilder[T](b: LinkedBindingBuilder[T]) {
        def toType[TImpl <: T : Manifest] = b.to(typeLiteral[TImpl])
    }
    
    implicit def enrichLinkedBinding[T](b: LinkedBindingBuilder[T]) = new ScalaLinkedBindingBuilder[T](b)
    
    class ScalaConstantBindingBuilder[T](b: ConstantBindingBuilder) {
        def toType[TImpl: ClassManifest] = b.to(classManifest[TImpl].erasure)
    }
    
    implicit def enrichConstantBinding[T](b: ConstantBindingBuilder) = new ScalaConstantBindingBuilder(b)
    
    class ScalaScopedBindingBuilder(b: ScopedBindingBuilder) {
        def inType[TAnn <: JAnnotation : ClassManifest] = b.in(annotation[TAnn])
    }
    
    implicit def enrichScopedBindingBuilder(b: ScopedBindingBuilder) = new ScalaScopedBindingBuilder(b)
}

