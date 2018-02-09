package models

import org.joda.time.DateTime
import play.api.libs.json._

case class CompetitionTeamResult (n_TeamID: Int, c_Team: String,wins_home: Int,wins_away: Int,draws: Int,points: Int)

object CompetitionTeamResult {

  implicit object CompetitionLeaderboardFormat extends Format[CompetitionTeamResult] {
    // convert from SportAction object to JSON (serializing to JSON)
    def writes(team: CompetitionTeamResult): JsValue = {
      //  tweetSeq == Seq[(String, play.api.libs.json.JsString)]
      val actionSeq = Seq(
        "n_TeamID" -> JsNumber(team.n_TeamID),
        "c_Team" -> JsString(team.c_Team),
        "wins_home" -> JsNumber(team.wins_home),
        "wins_away" -> JsNumber(team.wins_away),
        "draws" -> JsNumber(team.draws),
        "points" -> JsNumber(team.points),
      )
      JsObject(actionSeq)
    }

    // convert from JSON string to a CompetitionLeaderboard object (de-serializing from JSON)
    def reads(json: JsValue): JsResult[CompetitionTeamResult] = {
      JsSuccess(CompetitionTeamResult(
        json("n_TeamID").as[Int],
        json("c_Team").as[String],
        json("wins_home").as[Int],
        json("wins_away").as[Int],
        json("draws").as[Int],
        json("points").as[Int],
      ))
    }
  }
}