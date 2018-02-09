import { Component, OnInit } from '@angular/core';
import {APIService} from "../../_services/_api/index"
import {Observable} from "rxjs";
import {MatchDetails} from "../../_models/index";
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import {Router} from "@angular/router";



@Component({
  moduleId: module.id,
  templateUrl: 'matches.component.html'
})

export class MatchesComponent implements OnInit {
  displayedColumns = ['c_Team_home','score','c_Team_away',];
  dataSource : MatchDetailsDataSource | null
  constructor(private api : APIService, private router: Router) {

  }

  ngOnInit() {
    this.dataSource = new MatchDetailsDataSource(this.api)

  }
  selectTeam(row)
  {
    this.api.selectTeam(row.n_TeamID);
    this.router.navigate(['/team']);
  }
}

export class MatchDetailsDataSource extends DataSource<any> {
  constructor(private APIService: APIService) {
    super();
  }
  connect(): Observable<MatchDetails[]> {
    console.log("get matches ")
    return this.APIService.getMatches();
  }
  disconnect() {}
}
