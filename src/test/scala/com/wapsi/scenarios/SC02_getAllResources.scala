package com.wapsi.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import com.wapsi.config.Feeders._
import com.wapsi.transactions.getAllResources._

object SC02_getAllResources {
  val SC02_getAllResources: ScenarioBuilder = scenario("SC02_get_all_resources")
    .exec(getAllResources)
}