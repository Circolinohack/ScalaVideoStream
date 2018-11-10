package org.circolinohack

import org.circolinohack.UserRegistryActor.ActionPerformed
import spray.json.RootJsonFormat

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {
  import DefaultJsonProtocol._

  implicit val userJsonFormat: RootJsonFormat[User] = jsonFormat3(User)
  implicit val usersJsonFormat: RootJsonFormat[Users] = jsonFormat1(Users)

  implicit val actionPerformedJsonFormat: RootJsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed)
}
