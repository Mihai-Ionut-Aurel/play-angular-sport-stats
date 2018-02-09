package models

import org.joda.time.DateTime
import play.api.libs.json._

case class Team (n_TeamID: Int, c_Team: String)

object Team {

  implicit object TeamFormat extends Format[Team] {
    // convert from SportAction object to JSON (serializing to JSON)
    def writes(team: Team): JsValue = {
      //  tweetSeq == Seq[(String, play.api.libs.json.JsString)]
      val actionSeq = Seq(
        "n_TeamID" -> JsNumber(team.n_TeamID),
        "c_Team" -> JsString(team.c_Team)
      )
      JsObject(actionSeq)
    }

    // convert from JSON string to a Team object (de-serializing from JSON)
    def reads(json: JsValue): JsResult[Team] = {
      JsSuccess(Team(
        json("n_TeamID").as[Int],
        json("c_Team").as[String],
       ))
    }
  }
}