package com.keithalcock.graph.searcher

import com.keithalcock.graph.Graph
import com.keithalcock.graph.common.utils.Test

class EqualsTest extends Test:

  case class SlowHolder[T](val value: T):

    override def toString: String =
      s"SlowHolder[${value.toString}]"

    override def equals(any: Any): Boolean =
      println(s"$this == ${any.toString}")
      Thread.sleep(1000)
      any match
        case other: SlowHolder[T] =>
          this.value == other.value
        case _ => false

  case class FastHolder[T](val value: T):

    override def toString: String =
      s"FastHolder[${value.toString}]"

    override def equals(any: Any): Boolean =
      println(s"$this == ${any.toString}")
      Thread.sleep(100)
      any match
        case other: FastHolder[T] => this.eq(other)
        case _ => false

  val slowHolderA = SlowHolder('A')
  val slowHolderB = SlowHolder('B')
  val slowHolderC = SlowHolder('C')
  val slowHolderD = SlowHolder('D')
  val slowHolderE = SlowHolder('E')
  val slowHolderF = SlowHolder('F')
  val slowHolderG = SlowHolder('G')

  val slowGraph: Graph[SlowHolder[Char]] = Map(
    slowHolderA -> Set(slowHolderB, slowHolderC, slowHolderE),
    slowHolderB -> Set(slowHolderD, slowHolderF),
    slowHolderC -> Set(slowHolderG),
    slowHolderD -> Set.empty,
    slowHolderE -> Set.empty,
    slowHolderF -> Set(slowHolderE),
    slowHolderG -> Set.empty
  )

  def slowTest(searcher: Searcher[SlowHolder[Char]]): Unit =
    behavior of s"{$searcher.getClass.getName} in testA"

    it should "search" in {
      searcher.search(slowGraph, slowHolderA, slowHolderA) should be(true)
      searcher.search(slowGraph, slowHolderA, slowHolderE) should be(true)
      searcher.search(slowGraph, slowHolderA, slowHolderG) should be(true)
      searcher.search(slowGraph, slowHolderB, slowHolderG) should be(false)
    }

  slowTest(new DepthFirstRecursiveSearcher())
  slowTest(new DepthFirstIterativeSearcher())


  val fastHolderA = FastHolder(SlowHolder('A'))
  val fastHolderB = FastHolder(SlowHolder('B'))
  val fastHolderC = FastHolder(SlowHolder('C'))
  val fastHolderD = FastHolder(SlowHolder('D'))
  val fastHolderE = FastHolder(SlowHolder('E'))
  val fastHolderF = FastHolder(SlowHolder('F'))
  val fastHolderG = FastHolder(SlowHolder('G'))

  val fastGraph: Graph[FastHolder[SlowHolder[Char]]] = Map(
    fastHolderA -> Set(fastHolderB, fastHolderC, fastHolderE),
    fastHolderB -> Set(fastHolderD, fastHolderF),
    fastHolderC -> Set(fastHolderG),
    fastHolderD -> Set.empty,
    fastHolderE -> Set.empty,
    fastHolderF -> Set(fastHolderE),
    fastHolderG -> Set.empty
  )

  def fastTest(searcher: Searcher[FastHolder[SlowHolder[Char]]]): Unit =
    behavior of s"{$searcher.getClass.getName} in fastTest"

    it should "search" in {
      searcher.search(fastGraph, fastHolderA, fastHolderA) should be(true)
      searcher.search(fastGraph, fastHolderA, fastHolderE) should be(true)
      searcher.search(fastGraph, fastHolderA, fastHolderG) should be(true)
      searcher.search(fastGraph, fastHolderB, fastHolderG) should be(false)
    }

  fastTest(new DepthFirstRecursiveSearcher())
  fastTest(new DepthFirstIterativeSearcher())
