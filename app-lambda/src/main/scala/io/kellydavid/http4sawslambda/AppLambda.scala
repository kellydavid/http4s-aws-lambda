package io.kellydavid.http4sawslambda

import cats.effect.IO
import cats.effect.{ConcurrentEffect, ContextShift, Timer}
import io.github.howardjohn.lambda.http4s.Http4sLambdaHandler
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.client.blaze._
import org.http4s.client._
import fs2.Stream
import cats.implicits._

import scala.concurrent.ExecutionContext.Implicits.global._
import scala.concurrent.ExecutionContext.global

class AppLambda extends Http4sLambdaHandler(AppLambda.getAppRoutes)

object AppLambda {


//  implicit val ce: ConcurrentEffect[IO] = IO.concurrentEffect(global)
  def getAppRoutes: HttpRoutes[IO] =
    HelloRoutes.helloWorldRoutes[IO](HelloWorld.impl[IO]) <+> JokeRoutes.jokeRoutes[IO](Jokes.impl[IO](getClient))

  // Combine Service Routes into an HttpApp.
  // Can also be done via a Router if you
  // want to extract a segments not checked
  // in the underlying routes.

  def getClient: Client[IO] = {
    import scala.concurrent.ExecutionContext
    import java.util.concurrent._

    implicit val t: Timer[IO] = IO.timer(global)
    implicit val c: ContextShift[IO] = IO.contextShift(global)

    val blockingEC = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))
    val httpClient: Client[IO] = JavaNetClientBuilder[IO](blockingEC).create
    httpClient
  }

}