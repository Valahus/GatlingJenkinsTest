package com.wapsi.simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.util.Random

class Nominal_sans_opti extends Simulation {

  val randomNumber: Iterator[Map[String, Int]] = Iterator.continually(
    Map("randomNbr" -> Random.nextInt(100))
  )

  /*
  ** Créer un objet qui stocke la configuration globale des requêtes HTTP
   */
  val httpConfig: HttpProtocolBuilder = http
    .baseUrl("https://jsonplaceholder.typicode.com")
    .headers(Map(
      "Content-Type" -> "application/json; charset=UTF-8"
    ))
    .disableCaching

  /*
  ** Créer un scénario qui contient un enchaînement de requêtes HTTP
   */
  val getAllResources: ScenarioBuilder = scenario("SC01_get_all_resources")
    .exec(
      http("Get_all_resources")
        .get("/posts")
        .check(status.is(200))
        .check(bodyString.saveAs("BODY"))
    )
    .exec{
      session =>
        println("Body: " + session("BODY"))
        session
    }

  val getResource: ScenarioBuilder = scenario("SC02_get_resource")
    .feed(randomNumber)
    .exec(
      http("Get_resource")
        .get("/posts/${randomNbr}")
        .check(status.is(200))
        .check(jsonPath("$..id").is("${randomNbr}"))
        .check(bodyString.saveAs("BODY"))
    )
    .exec{
      session =>
        println("Body: " + session("BODY").as[String])
        session
    }

  val createResource: ScenarioBuilder = scenario("SC03_create_resource")
    .exec(
      http("Create_resource")
        .post("/posts")
        .body(StringBody("""{
                           |    "title": "blabla",
                           |    "body": "blabla",
                           |    "userId": 56
                           |  }""".stripMargin)).asJson
        .check(status.is(200))
        .check(bodyString.saveAs("BODY"))
    )
    .exec{
      session =>
        println("Body: " + session("BODY").as[String])
        session
    }



  /*
  ** Créer une simulation qui contient plusieurs scénarios avec leur modèle de charge
   */
  setUp(
    createResource.inject(
      atOnceUsers(1)
    )
  ).protocols(httpConfig)

}