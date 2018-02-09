package models


import org.joda.time._
import play.api.libs.json.{JsNumber, JsString, Json, _}


case class SportAction (n_actionid: Int,
                        c_competition: String,
                        n_Matchid: Int,
                        d_date: DateTime,
                        c_Action: String,
                        c_Period: String,
                        n_StartTime: Int,
                        n_Endtime: Int,
                        c_HomeOrAway: String,
                        n_TeamID: Int,
                        c_Team: String,
                        n_personid: Int,
                        c_person: String,
                        c_Function: String,
                        n_ShirtNr: Int,
                        c_ActionReason: String,
                        c_actionInfo: String,
                        n_Subpersonid: Int,
                        c_Subperson: String)

object SportAction {

  implicit object SportActionFormat extends Format[SportAction] {

    // convert from SportAction object to JSON (serializing to JSON)
    def writes(action: SportAction): JsValue = {
      //  tweetSeq == Seq[(String, play.api.libs.json.JsString)]
      val actionSeq = Seq(

        "n_actionid" -> JsNumber(action.n_actionid),
        "c_competition" -> JsString(action.c_competition),
        "n_Matchid" -> JsNumber(action.n_Matchid),
        "d_date" -> JsString(action.d_date.toString()),
        "c_Action" -> JsString(action.c_Action),
        "c_Period" -> JsString(action.c_Period),
        "n_StartTime" -> JsNumber(action.n_StartTime),
        "n_Endtime" -> JsNumber(action.n_Endtime),
        "c_HomeOrAway" -> JsString(action.c_HomeOrAway),
        "n_TeamID" -> JsNumber(action.n_TeamID),
        "c_Team" -> JsString(action.c_Team),
        "n_personid" -> JsNumber(action.n_personid),
        "c_person" -> JsString(action.c_person),
        "c_Function" -> JsString(action.c_Function),
        "n_ShirtNr" -> JsNumber(action.n_ShirtNr),
        "c_ActionReason" -> JsString(action.c_ActionReason),
        "c_actionInfo" -> JsString(action.c_actionInfo),
        "n_Subpersonid" -> JsNumber(action.n_Subpersonid),
        "c_Subperson" -> JsString(action.c_Subperson)
      )
      JsObject(actionSeq)
    }

    // convert from JSON string to a Tweet object (de-serializing from JSON)
    // (i don't need this method; just here to satisfy the api)
    def reads(json: JsValue): JsResult[SportAction] = {
      JsSuccess(SportAction(json("n_actionid").as[Int],
        json("c_competition").as[String],
        json("n_Matchid").as[Int],
        DateTime.parse(json("d_date").as[String]),
        json("c_Action").as[String],
        json("c_Period").as[String],
        json("n_StartTime").as[Int],
        json("n_Endtime").as[Int],
        json("c_HomeOrAway").as[String],
        json("n_TeamID").as[Int],
        json("c_Team").as[String],
        json("n_personid").as[Int],
        json("c_person").as[String],
        json("c_Function").as[String],
        json("n_ShirtNr").as[Int],
        json("c_ActionReason").as[String],
        json("c_actionInfo").as[String],
        json("n_Subpersonid").as[Int],
        json("c_Subperson").as[String]))
    }
  }
}

