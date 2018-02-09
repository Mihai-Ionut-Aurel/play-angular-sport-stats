import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { AppService } from './app.service';
import { HttpClientModule } from '@angular/common/http';
import {APIService} from "./_services/_api/index";
import { MyOwnCustomMaterialModule} from './_components/material'
import {UploadComponent} from "./_components/upload/index";
import {TeamsComponent} from "./_components/teams/index";
import { AppRoutingModule }        from './app.routing';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PlayersComponent} from "./_components/players/index";
import {CompetitionsComponent} from "./_components/competitions/index";
import {LeaderboardComponent} from "./_components/leaderboard/index";
import {MatchesComponent} from "./_components/matches/index";

@NgModule({
  declarations: [
    AppComponent,
    UploadComponent,
    TeamsComponent,
    PlayersComponent,
    CompetitionsComponent,
    LeaderboardComponent,
    MatchesComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MyOwnCustomMaterialModule,
  ],
  providers: [AppService, APIService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
