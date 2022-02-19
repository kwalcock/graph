package com.keithalcock.graph.searcher

import com.keithalcock.graph.Types.Graph
import com.keithalcock.graph.common.utils.Test

class SearcherTest extends Test {

  val graph: Graph[Int] = Map(
    0 -> Set(0, 1, 2, 4),
    1 -> Set(2, 3, 4),
    2 -> Set.empty,
    3 -> Set.empty,
    4 -> Set(0)
  )

  def test(searcher: Searcher[Int]): Unit = {
    behavior of searcher.getClass.getName

    it should "search" in {
      val searcher = new DepthFirstRecursiveSearcher[Int]()

      searcher.search(graph, -1, 4) should be (false)
      searcher.search(graph, 4, -1) should be (false)
      searcher.search(graph, -1, -1) should be (false)

      searcher.search(graph, 0, 4) should be (true)
      searcher.search(graph, 1, 3) should be (true)

      searcher.search(graph, 2, 2) should be (true)
      searcher.search(graph, 2, 3) should be (false)

      searcher.search(graph, 0, 3) should be (true)
      searcher.search(graph, 1, 0) should be (true)
    }
  }

  test(new DepthFirstRecursiveSearcher())
  test(new BreadthFirstSearcher())
}
