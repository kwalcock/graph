package com.keithalcock.graph.searcher

import com.keithalcock.graph.Types.Graph

class BreadthFirstSearcher[T] extends DepthFirstRecursiveSearcher[T] {

  override def search(graph: Graph[T], start: T, end: T): Boolean = ???
}
