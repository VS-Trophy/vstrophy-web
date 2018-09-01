import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Match } from './match';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { ExceptionService } from '../core/exception.service';
import { TeamsService } from '../teams/teams.service';
import { VSTrophyTeam } from '../teams/vstrophyteam';

@Injectable()
export class MatchesService {

  private matchesPath = 'matches'

  constructor(private http: HttpClient, private exceptionService: ExceptionService, private teamsService: TeamsService) { }

  getAllMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(environment.apiRoot + this.matchesPath)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getAllMatches", [])),
        //Resolve the team name by id
        map(matches => this.loadTeams(matches))
      )
  }

  getMatchesForWeek(season: number, week: number): Observable<Match[]> {
    var path: string = environment.apiRoot + this.matchesPath;
    var parameters: HttpParams =  new HttpParams();
    if (season != -1) {
     parameters = parameters.set('season',season+"");
    }
    if(week != -1){
      parameters = parameters.set('week',week+"");
    }
    this.http.get
    return this.http.get<Match[]>(path,{params: parameters}).pipe(
      catchError(this.exceptionService.handleHttpError("getMatchesForWeek", [])),
      //Resolve the team name by id
      map(matches => this.loadTeams(matches))
    )
  }


  private loadTeams(matches: Match[]): Match[] {
    matches.forEach(match => {
      this.teamsService.getTeamById(match.firstTeamId).subscribe(team => {
        match.firstTeamName = team.name;
        match.firstTeamLogoPath = team.logoPath
      })

      this.teamsService.getTeamById(match.secondTeamId).subscribe(team => {
        match.secondTeamName = team.name;
        match.secondTeamLogoPath = team.logoPath
      })
    })
    return matches
  }
}
