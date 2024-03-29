package io.kellydavid.http4sawslambda

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Main extends IOApp {
  def run(args: List[String]) =
    Http4sawslambdaServer.stream[IO].compile.drain.as(ExitCode.Success)
}