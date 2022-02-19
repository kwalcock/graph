package com.keithalcock.graph.searcher

import com.keithalcock.graph.Graph
import com.keithalcock.graph.common.utils.Test

class SearcherTest extends Test:
  val graphN: Graph[Int] = Map(
    0 -> Set(0, 1, 2, 4),
    1 -> Set(2, 3, 4),
    2 -> Set.empty,
    3 -> Set.empty,
    4 -> Set(0)
  )

  def testN(searcher: Searcher[Int]): Unit =
    behavior of s"{$searcher.getClass.getName} in testN"

    it should "search" in {
      searcher.search(graphN, -1, 4) should be(false)
      searcher.search(graphN, 4, -1) should be(false)
      searcher.search(graphN, -1, -1) should be(false)

      searcher.search(graphN, 0, 4) should be(true)
      searcher.search(graphN, 1, 3) should be(true)

      searcher.search(graphN, 2, 2) should be(true)
      searcher.search(graphN, 2, 3) should be(false)

      searcher.search(graphN, 0, 3) should be(true)
      searcher.search(graphN, 1, 0) should be(true)
    }

  testN(new DepthFirstRecursiveSearcher())
  testN(new DepthFirstIterativeSearcher())

  val graphA: Graph[Char] = Map(
    'A' -> Set('B', 'C', 'E'),
    'B' -> Set('D', 'F'),
    'C' -> Set('G'),
    'D' -> Set.empty,
    'E' -> Set.empty,
    'F' -> Set('E'),
    'G' -> Set.empty
  )

  def testA(searcher: Searcher[Char]): Unit =
    behavior of s"{$searcher.getClass.getName} in testA"

    it should "search" in {
      searcher.search(graphA, 'A', 'A') should be(true)
      searcher.search(graphA, 'A', 'E') should be(true)
      searcher.search(graphA, 'A', 'G') should be(true)
      searcher.search(graphA, 'B', 'G') should be(false)
    }

  testA(new DepthFirstRecursiveSearcher())
  testA(new DepthFirstIterativeSearcher())
