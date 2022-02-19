package com.keithalcock.graph.searcher

import com.keithalcock.graph.Graph

import scala.collection.mutable

class DepthFirstRecursiveSearcher[T] extends Searcher[T]:

  override def search(graph: Graph[T], start: T, end: T): Boolean =
    val searched = mutable.Set.empty[T]

    // This cannot be tail recursive because it needs to keep
    // track of where in the set it is still searching.
    def loop(key: T): Boolean =
      if key == end then true
      else if searched.contains(key) then false
      else
        searched += key
        graph(key).exists(loop)

    require(isValid(graph))
    graph.contains(start) &&
        graph.contains(end) &&
        loop(start)
