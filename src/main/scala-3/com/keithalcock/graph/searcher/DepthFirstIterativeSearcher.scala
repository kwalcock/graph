package com.keithalcock.graph.searcher

import com.keithalcock.graph.Graph
import com.keithalcock.graph.lang._

import scala.collection.mutable

class DepthFirstIterativeSearcher[T] extends Searcher[T]:

  override def search(graph: Graph[T], start: T, end: T): Boolean =
    val searched = mutable.Set.empty[T]
    val stack = mutable.Stack(start)

    def popUntilEnd =
      while stack.nonEmpty && stack.head != end do
        val key = stack.pop()
        if !searched(key) then
          searched += key
          stack ++= graph(key)
      stack

    require(isValid(graph))
    graph.contains(start) &&
        graph.contains(end) &&
        popUntilEnd.nonEmpty
