import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpRequest, HttpEvent, HttpParameterCodec,} from '@angular/common/http';
import {Observable} from "rxjs";
import { Team , Competition, Player,CompetitionTeamResult} from "../../_models/index";
import { environment} from "../../../environments/environment";
import {MatchDetails} from "../../_models/match";

@Injectable()
export class APIService {

  constructor(private http: HttpClient) { }
  private teamId:string;
  private competitionName:string;
  // file from event.target.files[0]
  uploadFile(url: string, file: File): Observable<HttpEvent<any>> {

    let formData = new FormData();
    formData.append('data', file);

    let params = new HttpParams();

    const options = {
      params: params,
      reportProgress: true,
    };

    const req = new HttpRequest('POST', url, formData, options);
    return this.http.request(req);
  }


  getTeams():Observable<Team[]> {
    let url =`${environment.api}/${environment.teams}`;
    return this.http.get(url).map(
      data => {
        console.log(data);
        return data["actions"];
      }
    )

  }

  selectCompetition(name: string){
    this.competitionName=name;
  }


  getCompetitions():Observable<Competition[]> {
    return this.http.get(`${environment.api}/${environment.competitions}`).map(
      data => {
        console.log(data);
        return data["actions"];
      }
    )
  }

  getLeaderboard():Observable<CompetitionTeamResult[]> {
    // Setup log namespace query parameter
    let params = new HttpParams({encoder: new CustomEncoder()}).set(environment.urlIDCompetition, this.competitionName);
    return this.http.get(`${environment.api}/${environment.leaderboard}`, { params: params }).map(
      data => {
        console.log(data);
        return data["actions"];
      }
    )
  }

  getMatches():Observable<MatchDetails[]> {
    // Setup log namespace query parameter
    let params = new HttpParams({encoder: new CustomEncoder()}).set(environment.urlIDCompetition, this.competitionName);
    return this.http.get(`${environment.api}/${environment.matches}`, { params: params }).map(
      data => {
        console.log(data);
        return data["actions"];
      }
    )
  }

  selectTeam(id: string){
    this.teamId=id;
  }
  getPlayers():Observable<Player[]> {
    let url =`${environment.api}/${environment.players}`;
    if(this.teamId)
      url = `${url}:${environment.urlIDTeam}`;
    return this.http.get(url).map(
      data => {
        console.log(data);
        return data["actions"];
      }
    )
  }
}


class CustomEncoder implements HttpParameterCodec {
  encodeKey(key: string): string {
    return encodeURIComponent(key);
  }

  encodeValue(value: string): string {
    return encodeURIComponent(value);
  }

  decodeKey(key: string): string {
    return decodeURIComponent(key);
  }

  decodeValue(value: string): string {
    return decodeURIComponent(value);
  }
}
