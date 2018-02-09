package models

import org.joda.time.DateTime
import play.api.libs.json.{JsNumber, JsString, _}

case class Player (n_personid: Int,
                   c_person: String,
                   c_Function: String,
                   n_ShirtNr: Int)

object Player {

  implicit object PlayerFormat extends Format[Player] {
    // convert from SportAction object to JSON (serializing to JSON)
    def writes(player: Player): JsValue = {
      //  tweetSeq == Seq[(String, play.api.libs.json.JsString)]
      val actionSeq = Seq(
        "n_personid"-> JsNumber(player.n_personid),
          "c_person"-> JsString(player.c_person),
            "c_Function"-> JsString(player.c_Function),
        "n_ShirtNr"->JsNumber(player.n_ShirtNr),

      )
      JsObject(actionSeq)
    }

    // convert from JSON string to a Player object (de-serializing from JSON)
    def reads(json: JsValue): JsResult[Player] = {
      JsSuccess(Player(
        json("n_personid").as[Int],
          json("c_person").as[String],
          json("c_Function").as[String],
          json("n_ShirtNr").as[Int],
      ))
    }
  }
}