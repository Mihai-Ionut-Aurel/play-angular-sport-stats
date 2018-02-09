import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {HttpClientModule, HttpEvent} from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppService } from './app.service';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/observable/of';
import {APIService} from "./_services/_api/api_service";

import {MatchDetails,Team,Competition,CompetitionTeamResult,Player} from "./_models/index";
import {NO_ERRORS_SCHEMA} from "@angular/core";


class FakeAppService extends AppService {
  getWelcomeMessage() {
    return Observable.of({
      content: 'Test content'
    });
  }
}

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
      imports: [
        HttpClientModule,
        RouterTestingModule
      ],
      schemas: [NO_ERRORS_SCHEMA],
    }).overrideComponent(AppComponent, {
      set: {
        providers: [
          { provide: AppService, useClass: FakeAppService}
        ]
      }
    }).compileComponents();
  }));
  it('should create the app', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'Gracenote Job Demo'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Gracenote Job Demo');
  }));

});
