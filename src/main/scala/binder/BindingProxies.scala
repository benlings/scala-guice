/*
 *  Copyright 2010-2011 Benjamin Lings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.me.lings.scalaguice
package binder

import javax.inject.{Provider => JXProvider}
import com.google.inject._
import com.google.inject.binder._
import java.lang.annotation.{Annotation => JAnnotation}
import java.lang.reflect.Constructor

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
  def toConstructor[S <: T](constructor: Constructor[S], constructorType: TypeLiteral[_ <: S]) = self.toConstructor(constructor, constructorType)
  def toConstructor[S <: T](constructor: Constructor[S]) = self toConstructor constructor
  def toProvider(provider: Provider[_ <: T]) = self toProvider provider
  def toProvider(provider: Class[_ <: JXProvider[_ <: T]]) = self toProvider provider
  def toProvider(providerType: TypeLiteral[_ <: JXProvider[_ <: T]]) = self toProvider providerType
  def toProvider(providerKey: Key[_ <: JXProvider[_ <: T]]) = self toProvider providerKey
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
