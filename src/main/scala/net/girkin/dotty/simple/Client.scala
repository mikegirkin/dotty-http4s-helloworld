package net.girkin.dotty.simple

import cats.effect.{ContextShift, IO, Timer}
import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder

object Client {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.global

    given cs as ContextShift[IO] = IO.contextShift(global)
    given timer as Timer[IO] = IO.timer(global)

    val clientResource = BlazeClientBuilder[IO](global).resource

    val program = clientResource.use { client =>
      makeGoogleCall(client)
    }.flatMap { response =>
      IO {
        println(response)
      }
    }

    program.unsafeRunSync
  }

  private def makeGoogleCall(httpClient: Client[IO]): IO[String] = {
    httpClient.expect[String]("http://www.google.com")
  }

}
