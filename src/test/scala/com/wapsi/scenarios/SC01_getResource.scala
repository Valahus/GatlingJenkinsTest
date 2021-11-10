package com.wapsi.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import com.wapsi.config.Feeders._
import com.wapsi.transactions.getResource._

object SC01_getResource {
  val SC01_getResource: ScenarioBuilder = scenario("SC01_get_resource")
    .feed(randomNumber)
    .exec(getResource)
}