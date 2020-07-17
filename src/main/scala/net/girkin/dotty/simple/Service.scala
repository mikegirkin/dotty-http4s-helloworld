package net.girkin.dotty.simple

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.blaze._

import scala.concurrent.ExecutionContext

object Service extends IOApp {
  
  val ec: ExecutionContext = scala.concurrent.ExecutionContext.global 

  override def run(args: List[String]): IO[ExitCode] = {
    val app = HttpRoutes.of[IO] {
      case GET -> Root / "hello" / name =>
        Ok(s"Hello, $name.")
    }.orNotFound

    BlazeServerBuilder[IO](ec)
      .bindHttp(8080, "localhost")
      .withHttpApp(app)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}
