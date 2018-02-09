import { Component, OnInit } from '@angular/core';
import {APIService} from "../../_services/_api/index"
import {Observable} from "rxjs";
import {Player} from "../../_models/index";
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';


@Component({
  moduleId: module.id,
  templateUrl: 'players.component.html'
})

export class PlayersComponent implements OnInit {
  displayedColumns = [ 'name'];
  dataSource : PlayerDataSource | null
  constructor(private api : APIService) {

  }

  ngOnInit() {
    this.dataSource = new PlayerDataSource(this.api)
  }

  selectPlayer(row)
  {
    console.log(row)
  }
}

export class PlayerDataSource extends DataSource<any> {
  constructor(private APIService: APIService) {
    super();
  }

  connect(): Observable<Player[]> {
    console.log("get teams ")
    return this.APIService.getPlayers();
  }
  disconnect() {}
}
