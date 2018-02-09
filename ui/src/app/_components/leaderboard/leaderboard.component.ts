import { Component, OnInit } from '@angular/core';
import {APIService} from "../../_services/_api/index"
import {Observable} from "rxjs";
import {CompetitionTeamResult} from "../../_models/index";
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import {Router} from "@angular/router";



@Component({
  moduleId: module.id,
  templateUrl: 'leaderboard.component.html'
})

export class LeaderboardComponent implements OnInit {
  displayedColumns = [ 'team','wins_home','wins_away', 'draws', 'points'];
  dataSource : CompetitionTeamResultDataSource | null
  constructor(private api : APIService, private router: Router) {

  }

  ngOnInit() {
    this.dataSource = new CompetitionTeamResultDataSource(this.api)

  }
  selectTeam(row)
  {
    this.api.selectTeam(row.n_TeamID);
    this.router.navigate(['/team']);
  }
}

export class CompetitionTeamResultDataSource extends DataSource<any> {
  constructor(private APIService: APIService) {
    super();
  }
  connect(): Observable<CompetitionTeamResult[]> {
    console.log("get leaderboard ")
    return this.APIService.getLeaderboard();
  }
  disconnect() {}
}
