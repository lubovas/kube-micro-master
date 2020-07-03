package com.lubo.service

import com.lubo.domain.Domain.Users
import sttp.model.Uri

import scala.concurrent.{ExecutionContext, Future}

object ExecutionManager {

  import sttp.client._
  import sttp.client.asynchttpclient.future.AsyncHttpClientFutureBackend
  import sttp.client.circe._

  import io.circe.generic.auto._

  implicit val sttpBackend = AsyncHttpClientFutureBackend()

  def getUsersFromWorker()(implicit ec: ExecutionContext): Future[Users] = {
    val uri = Uri(host = "worker-service", port = 8080, path = Seq("users"))
    println("I am the new master....")
    val request = basicRequest.get(uri).response(asJson[Users])
    request.send().map(response => {
          response.body match {
            case Right(users) => users
            case Left(ex) => throw new Exception(s"Getting users from worker failed: ${ex.getMessage}")
          }
    })
  }
}
