package com.keithalcock.graph.searcher

import com.keithalcock.graph.Graph

import scala.collection.mutable

class DepthFirstIterativeSearcher[T] extends Searcher[T]:

  override def search(graph: Graph[T], start: T, end: T): Boolean =
    val searched = mutable.Set.empty[T]
    val stack = mutable.Stack(start)

    require(isValid(graph))
    graph.contains(start) &&
        graph.contains(end) && {
          while (stack.nonEmpty && {
            val key = stack.pop()
            println(s"Checking ${key.toString}")
            if key == end then false
            else if searched(key) then true
            else
              searched += key
              stack.pushAll(graph(key).reverse)
              true
          }) ()
          stack.nonEmpty
        }
