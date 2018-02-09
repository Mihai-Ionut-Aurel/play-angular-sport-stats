
import { Routes, RouterModule } from '@angular/router';
import { NgModule }      from '@angular/core';
import {UploadComponent} from "./_components/upload/index";
import {TeamsComponent} from "./_components/teams/index";
import {CompetitionsComponent} from "./_components/competitions/index";
import {PlayersComponent} from "./_components/players/index";
import {LeaderboardComponent} from "./_components/leaderboard/index";
import {MatchesComponent} from "./_components/matches/matches.component";


const appRoutes: Routes = [
  { path: 'upload', component: UploadComponent },
  // otherwise redirect to home
  { path: '', component: UploadComponent},
  { path: 'teams', component: TeamsComponent},
  { path: 'players', component: PlayersComponent},
  { path: 'competitions', component: CompetitionsComponent},
  {path: 'leaderboard', component: LeaderboardComponent},
  {path: 'matches',component: MatchesComponent},

  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    RouterModule,
  ],
})

export class AppRoutingModule { }
