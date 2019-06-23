package io.kellydavid.http4sawslambda

import cats.effect.IO
import io.github.howardjohn.lambda.http4s.Http4sLambdaHandler

class HelloLambda extends Http4sLambdaHandler(HelloRoutes.helloWorldRoutes[IO](HelloWorld.impl[IO]))
