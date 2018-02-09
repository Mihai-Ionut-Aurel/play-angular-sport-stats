package utility

import java.io.File

import kantan.csv.{RowDecoder, rfc}
import models.{CompetitionTeamResult, MatchDetails, Player, SportAction}
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import kantan.csv._
import kantan.csv.ops._
import org.joda.time._

class SportActionUtilities {
  val format = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm")
  implicit val decoder: CellDecoder[DateTime] = joda.time.dateTimeDecoder(format)
  implicit val integerDecoder: CellDecoder[Int] = {
    CellDecoder.from(s => DecodeResult(
      try {
        s.toInt
      } catch {
        case e: Exception => 0
      }));
  }
  implicit val sportActionDecoder: RowDecoder[SportAction] = RowDecoder.ordered { (n_actionid: Int,
                                                                                   c_competition: String,
                                                                                   n_Matchid: Int,
                                                                                   d_date: org.joda.time.DateTime,
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
                                                                                   c_Subperson: String) =>
    new SportAction(n_actionid,
      c_competition,
      n_Matchid,
      d_date,
      c_Action,
      c_Period,
      n_StartTime,
      n_Endtime,
      c_HomeOrAway,
      n_TeamID,
      c_Team,
      n_personid,
      c_person,
      c_Function,
      n_ShirtNr,
      c_ActionReason,
      c_actionInfo,
      n_Subpersonid,
      c_Subperson)
  }

  def readCSV(path: String): List[kantan.csv.ReadResult[SportAction]] = {
    new File(path).readCsv[List, SportAction](rfc.withHeader)
  }

  def GetTeamMatches(team_id:Int,actions: Iterable[SportAction]): Iterable[MatchDetails] = {
    // have to consider how group by will do and pass based on it as home or away
    return actions.groupBy(_.n_Matchid).map(m => (m._1,
      m._2.filter(a => a.c_HomeOrAway == "Away" || a.c_HomeOrAway == "Home").groupBy(_.c_HomeOrAway)
        .map(a => a._2.groupBy(_.n_TeamID).map(t => (t._1, t._2.head.c_Team, t._2.head.c_HomeOrAway, t._2.filter(_.c_Action == "Goal").size)).toList).toList.flatten)).filter(
      res=>res._2(0)._1==team_id||res._2(1)._1==team_id).map(m=>MatchDetails(m._1,m._2(0)._1,m._2(0)._2,m._2(0)._4,m._2(1)._1,m._2(1)._2,m._2(1)._4))
  }

  def GetCompetitionMatches(name: String,actions: Iterable[SportAction]): Iterable[MatchDetails] = {

    return actions.filter(a => a.c_competition == name).groupBy(_.n_Matchid).map(m => (m._1,
      m._2.filter(a => a.c_HomeOrAway == "Away" || a.c_HomeOrAway == "Home").groupBy(_.c_HomeOrAway)
        .map(a => a._2.groupBy(_.n_TeamID).map(t => (t._1, t._2.head.c_Team, t._2.head.c_HomeOrAway, t._2.filter(_.c_Action == "Goal").size)).toList).toList.flatten))
      .map(m=>MatchDetails(m._1,m._2(0)._1,m._2(0)._2,m._2(0)._4,m._2(1)._1,m._2(1)._2,m._2(1)._4))
  }


  def GetCompetitionRankings(name: String,actions: Iterable[SportAction]): Iterable[CompetitionTeamResult] = {
  /*  val matches_results = actions.filter(a => a.c_competition == name).groupBy(_.n_Matchid).map(m => (m._1,
    m._2.filter(a => a.c_HomeOrAway == "Away" || a.c_HomeOrAway == "Home").groupBy(_.c_HomeOrAway)
    .map(a => a._2.groupBy(_.n_TeamID).map(t => (t._1, t._2.head.c_Team, t._2.head.c_HomeOrAway, t._2.filter(_.c_Action == "Goal").size)).toList).toList.flatten));*/

    val matches_results=GetCompetitionMatches(name,actions);
    //produce a result as: team_id,team_name,wins home,wins away, draws , points

    val results = matches_results.groupBy(t => t.n_TeamID_home).map(tr => (tr._1, //team id
      tr._2.head.c_Team_home, // team name
      matches_results.filter(m=>m.n_TeamID_home==tr._1 && m.team_goals_home>m.team_goals_away).size, // wins home
      matches_results.filter(m=>m.n_TeamID_away==tr._1 && m.team_goals_home<m.team_goals_away).size, // wins away
      matches_results.filter(m=>(m.n_TeamID_home==tr._1||m.n_TeamID_away==tr._1) && m.team_goals_home==m.team_goals_away).size))
      .map(fr => CompetitionTeamResult(
        fr._1,// team id
        fr._2,// team name
        fr._3,//wins home
        fr._4,// wins away
        fr._5,// draws
        fr._3 * 3 + fr._4 * 3 + fr._5)).toList.sortBy(s=>(-s.points,-s.wins_away,-s.wins_home,s.c_Team)); //points
    return results;
  }

  def getPlayers(actions: Iterable[SportAction]): Iterable[Player]={
    val playerList = actions.groupBy(_.n_personid).map(_._2.head).map(a => (a.n_personid, a.c_person, a.c_Function, a.n_ShirtNr)).filter(t => t._2 != "NULL").map(x => Player(x._1, x._2, x._3, x._4)).toList
    return  playerList;
  }

  def getTeamPlayers(team_id:Int,actions: Iterable[SportAction]): Iterable[Player]={
    val teamPlayers = actions.groupBy(_.n_personid).map(_._2.head).filter(a=>a.n_TeamID==team_id).map(a=>(a.n_personid,a.c_person,a.c_Function,a.n_ShirtNr)).filter(t=>t._2!="NULL").map(x=>Player(x._1,x._2,x._3,x._4))
    return  teamPlayers;
  }
}
