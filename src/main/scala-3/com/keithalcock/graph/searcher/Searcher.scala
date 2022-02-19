package com.keithalcock.graph.searcher

import com.keithalcock.graph.Graph

trait Searcher[T]:
  def search(graph: Graph[T], start: T, end: T): Boolean

  // Every value in the Sets should also be a key in the Map.
  // Dead ends should be described in the Map as value: T -> Set.empty[T].
  def isValid(graph: Graph[T]): Boolean =
    graph.values.forall { values =>
      values.forall(graph.contains)
    }
