package org.circolinohack.scalavideostream

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.stream.ActorMaterializer

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object WebServer extends App {

  implicit val system: ActorSystem = ActorSystem("ScalaVideoStream")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val route =
    path("hello") {
      get {
        complete(
          HttpEntity(ContentTypes.`text/html(UTF-8)`,
                     "<h1>Say hello to akka-http</h1>"))
      }
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
