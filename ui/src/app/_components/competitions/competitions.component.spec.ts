import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {HttpClientModule, HttpEvent} from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';


import {MatchDetails,Team,Competition,CompetitionTeamResult,Player} from "../../_models/index";
import {NO_ERRORS_SCHEMA} from "@angular/core";
import {APIService} from "../../_services/_api/api_service";
import {CompetitionsComponent} from "./competitions.component";
import {MyOwnCustomMaterialModule} from "../material/material_module";


class FakeCompetitionAPIService extends APIService {

  data: Competition[]=[{c_competition:"Eredivisie 2017/2018"},{c_competition:"Test Competition"}];

  selectCompetition(name: string): any {

  }

  getCompetitions(): Observable<Competition[]> {
    return Observable.of(this.data);
  }


}

describe('CompetitionsComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        CompetitionsComponent
      ],
      imports: [
        HttpClientModule,
        RouterTestingModule,
        MyOwnCustomMaterialModule,
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).overrideComponent(CompetitionsComponent, {
      set: {
        providers: [
          { provide: APIService, useClass: FakeCompetitionAPIService}
        ]
      }
    }).compileComponents();
  }));


  it(`Should have two row colomns in table'`, async(() => {
    const fixture = TestBed.createComponent(CompetitionsComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelectorAll('.mat-row').length).toEqual(2);

  }));

});
