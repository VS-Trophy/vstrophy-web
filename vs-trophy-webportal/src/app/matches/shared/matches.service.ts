import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Match } from '../match';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../../environments/environment';

@Injectable()
export class MatchesService {

  private matchesPath = 'matches'

  constructor(private http: HttpClient) { }

  getAllMatches(): Observable<Match[]>{
    return this.http.get<Match[]>(environment.apiRoot + this.matchesPath)
  }

  getMatchesForWeek(season: number, week: number): Observable<Match[]>{
    return this.http.get<Match[]>(environment.apiRoot + this.matchesPath)
  }
}
