package com.keithalcock.graph

import collection.mutable

package object lang {

  implicit class StackOps[T](stack: mutable.Stack[T]) {
    def ++=(elems: TraversableOnce[T]): mutable.Stack[T] = stack.pushAll(elems)
  }
}
