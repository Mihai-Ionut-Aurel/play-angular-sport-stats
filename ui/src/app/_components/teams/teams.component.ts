import { Component, OnInit } from '@angular/core';
import {APIService} from "../../_services/_api/index"
import {Observable} from "rxjs";
import {Team} from "../../_models/index";
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  templateUrl: 'teams.component.html'
})

export class TeamsComponent implements OnInit {
  displayedColumns = [ 'name'];
  dataSource : TeamDataSource | null
  constructor(private api : APIService, private router: Router) {

  }

  ngOnInit() {
    this.dataSource = new TeamDataSource(this.api)
  }

  selectTeam(row)
  {
    this.api.selectTeam(row.n_TeamID)
    this.router.navigate(["/players"])
  }
}

export class TeamDataSource extends DataSource<any> {
  constructor(private APIService: APIService) {
    super();
  }
  connect(): Observable<Team[]> {
    console.log("get teams ")
    return this.APIService.getTeams();
  }
  disconnect() {}
}
