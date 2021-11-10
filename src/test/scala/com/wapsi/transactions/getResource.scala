package com.wapsi.transactions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object getResource {
  val getResource: HttpRequestBuilder = http("Get_resource")
    .get("/posts/${randomNbr}")
    .check(status.is(200))
    .check(jsonPath("$..id").is("${randomNbr}"))
    .check(bodyString.saveAs("BODY"))
}