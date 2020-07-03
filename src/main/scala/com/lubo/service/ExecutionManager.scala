package com.lubo.service

import com.lubo.domain.Constants._
import com.lubo.domain.Domain.Users
import sttp.model.Uri

import scala.concurrent.{ExecutionContext, Future}

object ExecutionManager {

  import io.circe.generic.auto._
  import sttp.client._
  import sttp.client.asynchttpclient.future.AsyncHttpClientFutureBackend
  import sttp.client.circe._

  implicit val sttpBackend = AsyncHttpClientFutureBackend()

  def getUsersFromWorker()(implicit ec: ExecutionContext): Future[Users] = {
    val workerUri = Uri(host = WorkerHost, path = UsersPath, port = WorkerPort)
    val request = basicRequest.get(workerUri).response(asJson[Users])
    request.send().map(response => {
          response.body match {
            case Right(users) => users
            case Left(ex) => throw new Exception(s"Getting users from worker failed: ${ex.getMessage}")
          }
    })
  }
}
