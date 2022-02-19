package com.keithalcock.graph

object Types {
  type Graph[T] = Map[T, Set[T]]
}
