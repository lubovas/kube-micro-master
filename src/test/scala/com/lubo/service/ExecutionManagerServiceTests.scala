package com.lubo.service

import org.scalatra.test.scalatest._

class ExecutionManagerServiceTests extends ScalatraFunSuite {

  addServlet(classOf[ExecutionManagerService], "/*")

  test("GET / on MasterServer should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
