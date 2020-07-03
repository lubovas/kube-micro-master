package com.lubo.service

import com.lubo.domain.Constants._
import org.scalatra._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.util.{Failure, Success, Try}

class ExecutionManagerService extends ScalatraServlet {

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  get("/") {
    views.html.hello()
  }

  get("/users") {
    val result = Try(Await.result(ExecutionManager.getUsersFromWorker(), 30.seconds))
    result match {
      case Success(users) => Ok(users, ApplicationJson)
      case Failure(ex) => InternalServerError(ex, ApplicationJson)
    }
  }
}
