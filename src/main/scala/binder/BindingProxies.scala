package uk.me.lings.scalaguice
package binder

import com.google.inject._
import com.google.inject.binder._
import java.lang.annotation.{Annotation => JAnnotation}

/**
 * Proxy for [[com.google.inject.binder.ScopedBindingBuilder]]
 */
trait ScopedBindingBuilderProxy extends ScopedBindingBuilder
                          with Proxy {

  override def self: ScopedBindingBuilder

  def asEagerSingleton = self asEagerSingleton
  def in(scope: Scope) = self in scope
  def in(scopeAnnotation: Class[_ <: JAnnotation]) = self in scopeAnnotation
}

/**
 * Proxy for [[com.google.inject.binder.LinkedBindingBuilder]]
 */
trait LinkedBindingBuilderProxy[T] extends LinkedBindingBuilder[T]
                          with ScopedBindingBuilderProxy {

  override def self: LinkedBindingBuilder[T]

  def to(implementation: Class[_ <: T]) = self to implementation
  def to(implementation: TypeLiteral[_ <: T]) = self to implementation
  def to(targetKey: Key[_ <: T]) = self to targetKey
  def toInstance(instance: T) = self toInstance instance
  def toProvider(provider: Provider[_ <: T]) = self toProvider provider
  def toProvider(provider: Class[_ <: Provider[_ <: T]]) = self toProvider provider
  def toProvider(providerKey: Key[_ <: Provider[_ <: T]]) = self toProvider providerKey
}

/**
 * Proxy for [[com.google.inject.binder.AnnotatedBindingBuilder]]
 */
trait AnnotatedBindingBuilderProxy[T] extends AnnotatedBindingBuilder[T]
                           with LinkedBindingBuilderProxy[T] {

  override def self: AnnotatedBindingBuilder[T]

  def annotatedWith(annotation: JAnnotation) = self annotatedWith annotation
  def annotatedWith(annotationType: Class[_ <: JAnnotation]) = self annotatedWith annotationType
}
