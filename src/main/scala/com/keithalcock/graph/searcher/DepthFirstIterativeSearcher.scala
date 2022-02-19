package com.keithalcock.graph.searcher

import com.keithalcock.graph.Types.Graph

import scala.collection.mutable

class DepthFirstIterativeSearcher[T] extends Searcher[T] {

  override def search(graph: Graph[T], start: T, end: T): Boolean = {
    val searched = mutable.Set.empty[T]
    val queue = mutable.Queue(start)

    require(isValid(graph))
    graph.contains(start) &&
        graph.contains(end) &&
        queue.dropWhile { key =>
          true
        }.nonEmpty
  }
}
