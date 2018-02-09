import { Component, OnInit } from '@angular/core';
import {APIService} from "../../_services/_api/index"
import {Observable} from "rxjs";
import {Competition} from "../../_models/index";
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import {Router} from "@angular/router";


@Component({
  moduleId: module.id,
  templateUrl: 'competitions.component.html'
})

export class CompetitionsComponent implements OnInit {
  displayedColumns = [ 'name','leaderboard','matches'];
  dataSource : CompetitionDataSource | null
  constructor(private api : APIService, private router: Router) {

  }

  ngOnInit() {
    this.dataSource = new CompetitionDataSource(this.api)
  }

  viewLeaderboardCompetition(row)
  {
    this.api.selectCompetition(row.c_competition);
    this.router.navigate(['/leaderboard']);
  }

  viewMatchesCompetition(row)
  {
    this.api.selectCompetition(row.c_competition);
    this.router.navigate(['/matches']);
  }
}

export class CompetitionDataSource extends DataSource<any> {
  constructor(private APIService: APIService) {
    super();
  }
  connect(): Observable<Competition[]> {
    console.log("get teams ")
    return this.APIService.getCompetitions();
  }
  disconnect() {}
}
