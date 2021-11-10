package com.wapsi.config

import scala.util.Random

object Feeders {
  val randomNumber: Iterator[Map[String, Int]] = Iterator.continually(
    Map("randomNbr" -> Random.nextInt(100))
  )

  val randomUser: Iterator[Map[String, Int]] = Iterator.continually(
    Map("randomUsr" -> Random.nextInt(100))
  )
}