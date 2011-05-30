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
