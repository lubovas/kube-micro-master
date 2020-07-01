package com.lubo.service

import org.scalatra._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class ExecutionManagerService extends ScalatraServlet {

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  get("/") {
    views.html.hello()
  }

  get("/executions") {
    ExecutionManager.getUsersFromWorker().onComplete({
      case Success(users) => println(users)
      case Failure(ex) => println(ex)
    })
  }

}
