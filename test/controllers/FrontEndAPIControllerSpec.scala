package controllers
import java.io._
import java.nio.file.Files

import akka.stream.scaladsl._
import akka.util.ByteString
import org.scalatestplus.play._
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.libs.ws.WSClient
import play.api.mvc._
import play.api.test.Helpers._
import play.api.test._
import play.api.libs.json.Json

class FrontEndAPIControllerSpec extends PlaySpec with GuiceOneServerPerSuite with Injecting {


  "FrontEndAPIController" must {
    "upload a file successfully" in {
      val tmpFile = java.io.File.createTempFile("hello", "csv")
      tmpFile.deleteOnExit()
      val msg = "hello world"
      Files.write(tmpFile.toPath, msg.getBytes())

      val url = s"http://localhost:${Helpers.testServerPort}/api/upload"
      val responseFuture = inject[WSClient].url(url).post(postSource(tmpFile))
      val response = await(responseFuture)
      response.status mustBe OK
      response.body mustBe Json.obj("message"->"File uploaded").toString()
    }
  }

  def postSource(tmpFile: File): Source[MultipartFormData.Part[Source[ByteString, _]], _] = {
    import play.api.mvc.MultipartFormData._
    Source(FilePart("data", "hello.csv", Option("text/plain"),
      FileIO.fromPath(tmpFile.toPath)) :: DataPart("key", "value") :: List())
  }
}
