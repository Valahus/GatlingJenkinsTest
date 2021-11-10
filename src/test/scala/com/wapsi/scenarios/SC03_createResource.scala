package com.wapsi.scenarios

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import com.wapsi.transactions.createResource._
import com.wapsi.config.Feeders._

object SC03_createResource {
  val SC03_createResource: ScenarioBuilder = scenario("Create_resource")
    .feed(randomUser)
    .exec(createResource)
    .exec {
      session =>
        println(session("BODY").as[String])
        session
    }
}