package com.wapsi.transactions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object createResource {
  val createResource: HttpRequestBuilder = http("Create_resource")
    .post("/posts")
    .body(ElFileBody("data/createResource.json")).asJson
    .check(status.is(201))
    .check(bodyString.saveAs("BODY"))
}