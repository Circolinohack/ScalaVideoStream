package org.circolinohack.scalavideostream

import java.nio.file.FileSystems

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.stream.ActorMaterializer
import akka.stream.alpakka.file.scaladsl.FileTailSource
import akka.stream.scaladsl.Source
import akka.util.ByteString

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}

object WebServer extends App {

  implicit val system: ActorSystem = ActorSystem("ScalaVideoStream")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val fs = FileSystems.getDefault

  val route =
    path("hello") {
      get {
        complete {
          val chunks: Source[ByteString, NotUsed] = FileTailSource(
            path =
              fs.getPath("./src/main/resources/big_buck_bunny_720p_30mb.mp4"),
            maxChunkSize = 8192,
            startingPosition = 0,
            pollingInterval = 250.millis
          )
          HttpEntity(ContentTypes.`application/octet-stream`, chunks)
        }
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
