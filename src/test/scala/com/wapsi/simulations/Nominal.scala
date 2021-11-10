package com.wapsi.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import com.wapsi.config.Config._
import scala.concurrent.duration._
import com.wapsi.scenarios.SC01_getResource._
import com.wapsi.scenarios.SC02_getAllResources._
import com.wapsi.scenarios.SC03_createResource._

class Nominal extends Simulation {

  /*
  ** Créer un objet qui stocke la configuration globale des requêtes HTTP
   */
  val httpConfig: HttpProtocolBuilder = http
    .baseUrl(project_url)
    .headers(project_headers)
    .disableCaching

  /*
  ** Créer une simulation qui contient plusieurs scénarios avec leur modèle de charge
   */
  setUp(
    SC01_getResource.inject(
      rampUsersPerSec(1).to(2).during(5.seconds),
      constantUsersPerSec(2).during(10.seconds)
    ),
    SC02_getAllResources.inject(
      rampUsersPerSec(1).to(2).during(5.seconds),
      constantUsersPerSec(2).during(10.seconds)
    ),
    SC03_createResource.inject(
      rampUsersPerSec(1).to(2).during(5.seconds),
      constantUsersPerSec(2).during(10.seconds)
    )
  ).assertions(
    forAll.successfulRequests.percent.gt(90),
    forAll.responseTime.percentile3.lte(5000)
  ).protocols(httpConfig)

}