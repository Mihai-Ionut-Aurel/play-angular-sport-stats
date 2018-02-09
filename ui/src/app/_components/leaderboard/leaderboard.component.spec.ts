import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {HttpClientModule, HttpEvent} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';


import {MatchDetails,Team,Competition,CompetitionTeamResult,Player} from "../../_models/index";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {APIService} from "../../_services/_api/api_service";
import {MyOwnCustomMaterialModule} from "../material/material_module";
import {LeaderboardComponent} from "./leaderboard.component";
import {CompetitionsComponent} from "../competitions/index";


class FakeLeaderboardAPIService extends APIService {

  data: CompetitionTeamResult[]=[
    {n_TeamID: 0, c_Team: "Test 1", wins_home: 5, wins_away: 5, draws: 0, points: 30},
    {n_TeamID: 1, c_Team: "Test 2", wins_home: 1, wins_away: 5, draws: 2, points: 20},
    {n_TeamID: 2, c_Team: "Test 3", wins_home: 3, wins_away: 0, draws: 1, points: 10},
    {n_TeamID: 3, c_Team: "Test 4", wins_home: 0, wins_away: 1, draws: 1, points: 5},
    {n_TeamID: 4, c_Team: "Test 5", wins_home: 0, wins_away: 5, draws: 2, points: 2}
    ];



  getLeaderboard(): Observable<CompetitionTeamResult[]> {
    return Observable.of(this.data);
  }


}

describe('LeaderboardComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        LeaderboardComponent
      ],
      imports: [
        HttpClientModule,
        RouterTestingModule,
        MyOwnCustomMaterialModule,
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).overrideComponent(LeaderboardComponent, {
      set: {
        providers: [
          { provide: APIService, useClass: FakeLeaderboardAPIService}
        ]
      }
    }).compileComponents();
  }));


  it(`Should have 5 row colomns in table'`, async(() => {
    const fixture = TestBed.createComponent(LeaderboardComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelectorAll('.mat-row').length).toEqual(5);

  }));

  it(`Should have Test 1 5 5 0 3 0 as first row'`, async(() => {
    const fixture = TestBed.createComponent(LeaderboardComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelectorAll('.mat-row').item(0).innerText).toEqual("Test 1\n" +
      "5\n" +
      "5\n" +
      "0\n" +
      "30\n");

  }));

});
