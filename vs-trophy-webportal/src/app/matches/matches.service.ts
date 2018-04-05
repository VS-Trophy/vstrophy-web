import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Match } from './match';
import { Observable } from 'rxjs/Observable';
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
    return this.http.get<Match[]>(environment.apiRoot + this.matchesPath + '?season=' + season + '&week=' + week).pipe(
      catchError(this.exceptionService.handleHttpError("getMatchesForWeek", [])),
      //Resolve the team name by id
      map(matches => this.loadTeams(matches))
    )
  }


  private loadTeams(matches: Match[]): Match[] {
    matches.forEach(match => {
      var firstTeam = this.teamsService.getTeamById(match.firstTeamId)
      if (firstTeam != null) {
        match.firstTeamName = firstTeam.name
        match.firstTeamLogoPath = firstTeam.logoPath
      }
      var secondTeam = this.teamsService.getTeamById(match.secondTeamId)
      if (secondTeam != null) {
        match.secondTeamName = secondTeam.name
        match.secondTeamLogoPath = secondTeam.logoPath
      }
    })
    return matches
  }
}
