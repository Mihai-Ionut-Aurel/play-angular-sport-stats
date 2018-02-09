package models

import org.joda.time.DateTime
import play.api.libs.json._

case class MatchDetails (n_Matchid: Int,n_TeamID_home: Int, c_Team_home: String,team_goals_home: Int,n_TeamID_away: Int, c_Team_away: String,team_goals_away: Int)

object MatchDetails {

  implicit object MatchDetailsFormat extends Format[MatchDetails] {
    // convert from SportAction object to JSON (serializing to JSON)
    def writes(team: MatchDetails): JsValue = {
      //  tweetSeq == Seq[(String, play.api.libs.json.JsString)]
      val actionSeq = Seq(
        "n_Matchid" -> JsNumber(team.n_TeamID_home),
        "n_TeamID_home" -> JsNumber(team.n_TeamID_home),
        "c_Team_home" -> JsString(team.c_Team_home),
        "team_goals_home" -> JsNumber(team.team_goals_home),
      "n_TeamID_away" -> JsNumber(team.n_TeamID_away),
      "c_Team_away" -> JsString(team.c_Team_away),
      "team_goals_away" -> JsNumber(team.team_goals_away),
      )
      JsObject(actionSeq)
    }

    // convert from JSON string to a CompetitionLeaderboard object (de-serializing from JSON)
    def reads(json: JsValue): JsResult[MatchDetails] = {
      JsSuccess(MatchDetails(
        json("n_Matchid").as[Int],
        json("n_TeamID_home").as[Int],
        json("c_Team_home").as[String],
        json("team_goals_home").as[Int],
        json("n_TeamID_away").as[Int],
        json("c_Team_away").as[String],
        json("team_goals_away").as[Int],
      ))
    }
  }
}