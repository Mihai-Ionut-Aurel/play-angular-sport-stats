package models

import org.joda.time.DateTime
import play.api.libs.json._

case class Competition (c_competition: String)

object Competition {

  implicit object CompetitionFormat extends Format[Competition] {
    // convert from SportAction object to JSON (serializing to JSON)
    def writes(team: Competition): JsValue = {
      //  tweetSeq == Seq[(String, play.api.libs.json.JsString)]
      val actionSeq = Seq(
        "c_competition" -> JsString(team.c_competition)
      )
      JsObject(actionSeq)
    }

    // convert from JSON string to a Competition object (de-serializing from JSON)
    def reads(json: JsValue): JsResult[Competition] = {
      JsSuccess(Competition(
        json("c_competition").as[String],
      ))
    }
  }
}