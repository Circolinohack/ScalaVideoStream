package org.circolinohack.scalavideostream

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.directives.ContentTypeResolver.Default
import akka.stream.ActorMaterializer

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object WebServer extends App with Directives {

  implicit val system: ActorSystem = ActorSystem("ScalaVideoStream")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val route =
    path("hello") {
      getFromFile("./src/main/resources/big_buck_bunny_720p_30mb.mp4")
    }

  val serverBinding: Future[Http.ServerBinding] =
    Http().bindAndHandle(route, "localhost", 8080)

  serverBinding.onComplete {
    case Success(bound) =>
      println(
        s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}
