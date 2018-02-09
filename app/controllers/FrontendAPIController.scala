package controllers

import javax.inject._
import java.io.File

import kantan.csv._
import kantan.csv.ops._
import java.nio.file.{Files, Paths}

import play.api.libs.json.Json
import play.api.mvc._
import play.api.http.HttpEntity
import akka.util.ByteString
import models._
import play.api.Logger
import utility.SportActionUtilities

class FrontendAPIController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  var sportActionUtilities: SportActionUtilities= new SportActionUtilities();
  var actions: List[SportAction]=_;

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("data").map { data =>

      // only get the last part of the filename
      // otherwise someone can send a path like ../../home/foo/bar.txt to write to other files on the system
      val filename = Paths.get(data.filename).getFileName
      data.ref.moveTo(Paths.get(s"D:/ProiecteScala/$filename"), replace = true)
      actions = sportActionUtilities.readCSV(s"D:/ProiecteScala/Dataset2roundsEredivie20172018.csv").map(_.get)
      Logger.debug(actions.length.toString)
      if(actions.length>0)
        Logger.debug(actions(actions.length-1).toString())
      Ok(Json.obj("message"->"File uploaded"))
    }.getOrElse {
      Redirect("/").flashing(
        "error" -> "Missing file")
    }
  }

  def teams = Action { implicit request =>

    if( actions!=null) {
      val teamsList = actions.groupBy(_.n_TeamID).map(_._2.head).map(a => (a.n_TeamID, a.c_Team)).filter(t => t._2 != "NULL").map(x => Team(x._1, x._2)).toList
      Logger.debug(teamsList(teamsList.length - 1).toString())
      Ok(Json.obj("actions" -> teamsList))
    }
    else
      Ok(Json.obj("actions"->""))
  }

  def teamsofCompetition(name: String) = Action { implicit request =>

    if( actions!=null) {
      val teamsList = actions.groupBy(_.n_TeamID).map(_._2.head).filter(a=>a.c_competition==name).map(a => (a.n_TeamID, a.c_Team)).filter(t => t._2 != "NULL").map(x => Team(x._1, x._2)).toList
      Logger.debug(teamsList(teamsList.length - 1).toString())
      Ok(Json.obj("actions" -> teamsList))
    }
    else
      Ok(Json.obj("actions"->""))
  }

  def competitions = Action { implicit request =>
    if(actions!=null) {
      val competitionsList = actions.groupBy(_.c_competition).map(_._2.head).map(a => (a.c_competition)).filter(t => t != "NULL").map(x => Competition(x)).toList
      Logger.debug(competitionsList(competitionsList.length - 1).toString())
      Ok(Json.obj("actions" -> competitionsList))
    }
    else
      Ok(Json.obj("actions"->""))
  }

  def competitionRankins(name: Option[String])= Action { implicit request =>

    if(actions!=null && name.get!=null) {

      Ok(Json.obj("actions" -> sportActionUtilities.GetCompetitionRankings(name.get,actions)))
    }
    else{
      Ok(Json.obj("actions"->""))
    }
  }
  def competitionMatches(name: Option[String])= Action { implicit request =>

    if(actions!=null && name.get!=null) {
      Ok(Json.obj("actions" -> sportActionUtilities.GetCompetitionMatches(name.get,actions)))
    }
    else{
      Ok(Json.obj("actions"->""))
    }
  }

  def players = Action { implicit request =>
    if(actions!=null) {

      Ok(Json.obj("actions" -> sportActionUtilities.getPlayers(actions)))
    }
    else
      Ok(Json.obj("actions"->""))
  }

  def playersOfTeam(id: Int) = Action { implicit request =>

    if(actions!=null) {
      Ok(Json.obj("actions" ->sportActionUtilities.getTeamPlayers(id,actions)))
    }
    else
      Ok(Json.obj("actions"->""))
  }

}
