package com.wapsi.transactions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object getAllResources {
  val getAllResources: HttpRequestBuilder = http("Get_all_resources")
    .get("/posts")
    .check(status.is(200))
}