package Utilities
import models.SportAction
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.scalatestplus.play._
import play.api.test._
import utility.SportActionUtilities
class SportActionUtilitiesSpec extends PlaySpec{
  val format = DateTimeFormat.forPattern("dd-MMM-yyyy HH:mm")

  val noGoalActions: List[SportAction]= List[SportAction](
  SportAction(22039489,"Eredivisie 2017/2018",2174508,DateTime.parse("11-Aug-2017 19:00",format),"Foul committed","First half",
    24000,24000,"Home",77,"ADO Den Haag",1223164,"Sheraldo Becker","NULL",0,"Rough play","NULL",924566,"Anouar Kalig"),
    SportAction(22039490,"Eredivisie 2017/2018",2174508,DateTime.parse("11-Aug-2017 19:00",format),"Free kick","First half",
      29000,29000,"Away",16,"FC Utrecht",924566,"Anouar Kali","NULL",0,"NULL","Left foot",0,"NULL"),
  );

  "SportActionUtilities must" must {
    val sportActionUtilities = new SportActionUtilities();

    "return one matche for competition Eredivisie 2017/2018" in {
      sportActionUtilities.GetCompetitionMatches("Eredivisie 2017/2018",noGoalActions).size mustBe 1
    }

    "score for match should be 0 - 0" in {
      sportActionUtilities.GetCompetitionMatches("Eredivisie 2017/2018",noGoalActions).head.team_goals_home mustBe 0
      sportActionUtilities.GetCompetitionMatches("Eredivisie 2017/2018",noGoalActions).head.team_goals_away mustBe 0
    }

    "return one match for team FC Utrecht" in {
      sportActionUtilities.GetTeamMatches(16,noGoalActions).size mustBe 1
    }

    "any team should only have one point" in {
      sportActionUtilities.GetCompetitionRankings("Eredivisie 2017/2018",noGoalActions).head.points mustBe 1
    }

  }

}
