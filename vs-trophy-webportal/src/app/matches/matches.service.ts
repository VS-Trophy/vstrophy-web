import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Match } from './match';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { ExceptionService } from '../core/exception.service';
import { TeamsService } from '../teams/teams.service';
import { VSTrophyTeam } from '../teams/vstrophyteam';
import { WeekPointer } from '../season/week-pointer';

@Injectable()
export class MatchesService {

  private matchesPath = 'matches';
  private margin = '/margin';
  private totalpoints = '/totalpoints';

  constructor(private http: HttpClient, private exceptionService: ExceptionService, private teamsService: TeamsService) { }

  getAllMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(environment.apiRoot + this.matchesPath)
      .pipe(
        catchError(this.exceptionService.handleHttpError('getAllMatches', [])),
        // Resolve the team name by id
        map(matches => this.loadTeams(matches))
      );
  }

  getClosestMatches(season: number, week: number, limit: number): Observable<Match[]> {
    const path: string = environment.apiRoot + this.matchesPath + this.margin;
    let parameters: HttpParams = new HttpParams();
    if (season != null) {
      parameters =  parameters.set('season', season + '');
    }
    if (week != null) {
      parameters =   parameters.set('week', week + '');
    }
    if (limit != null) {
      parameters =    parameters.set('limit', limit + '');
    }
    parameters.set('sortorder', 'asc'); // This lets us get the closest matches
    return this.http.get<Match[]>(path, { params: parameters }).pipe(
      catchError(this.exceptionService.handleHttpError('getClosestMatches', [])),
      // Resolve the team name by id
      map(matches => this.loadTeams(matches))
    );
  }

  getMostDecisiveMatches(season: number, week: number, limit: number): Observable<Match[]> {
    const path: string = environment.apiRoot + this.matchesPath + this.margin;
    let parameters: HttpParams = new HttpParams();
    if (season != null) {
      parameters = parameters.set('season', season + '');
    }
    if (week != null) {
      parameters = parameters.set('week', week + '');
    }
    if (limit != null) {
      parameters = parameters.set('limit', limit + '');
    }
    parameters = parameters.set('sortorder', 'desc'); // This lets us get the most decisive matches
     return this.http.get<Match[]>(path, { params: parameters }).pipe(
      catchError(this.exceptionService.handleHttpError('getClosestMatches', [])),
      // Resolve the team name by id
      map(matches => this.loadTeams(matches))
    );
  }

  getTopScoringMatches(season: number, week: number, limit: number): Observable<Match[]> {
    const path: string = environment.apiRoot + this.matchesPath + this.totalpoints;
    let parameters: HttpParams = new HttpParams();
    if (season != null) {
      parameters = parameters.set('season', season + '');
    }
    if (week != null) {
      parameters = parameters.set('week', week + '');
    }
    if (limit != null) {
      parameters = parameters.set('limit', limit + '');
    }
    parameters = parameters.set('sortorder', 'desc'); // This lets us get the top scoring matches

    return this.http.get<Match[]>(path, { params: parameters }).pipe(
      catchError(this.exceptionService.handleHttpError('getClosestMatches', [])),
      // Resolve the team name by id
      map(matches => this.loadTeams(matches))
    );
  }

  getLeastScoringMatches(season: number, week: number, limit: number): Observable<Match[]> {
    const path: string = environment.apiRoot + this.matchesPath + this.totalpoints;
    let parameters: HttpParams = new HttpParams();
    if (season != null) {
      parameters = parameters.set('season', season + '');
    }
    if (week != null) {
      parameters = parameters.set('week', week + '');
    }
    if (limit != null) {
      parameters = parameters.set('limit', limit + '');
    }
    parameters = parameters.set('sortorder', 'asc'); // This lets us get the least scoring matches

    return this.http.get<Match[]>(path, { params: parameters }).pipe(
      catchError(this.exceptionService.handleHttpError('getClosestMatches', [])),
      // Resolve the team name by id
      map(matches => this.loadTeams(matches))
    );
  }

  getMatchesForWeek(weekPointer: WeekPointer, team1: VSTrophyTeam, team2: VSTrophyTeam): Observable<Match[]> {
    const path: string = environment.apiRoot + this.matchesPath;
    let parameters: HttpParams = new HttpParams();
    if (weekPointer.season !== -1) {
      parameters = parameters.set('season', weekPointer.season + '');
    }
    if (weekPointer.week !== -1) {
      parameters = parameters.set('week', weekPointer.week + '');
    }
    if (team1 != null) {
      parameters = parameters.set('team1', team1.nflId);
    }
    if (team2 != null) {
      parameters = parameters.set('team2', team2.nflId);
    }

    return this.http.get<Match[]>(path, { params: parameters }).pipe(
      catchError(this.exceptionService.handleHttpError('getMatchesForWeek', [])),
      // Resolve the team name by id
      map(matches => this.loadTeams(matches))
    );
  }


  private loadTeams(matches: Match[]): Match[] {
    matches.forEach(match => {
      this.teamsService.getTeamById(match.firstTeamId).subscribe(team => {
        match.firstTeamName = team.name;
        match.firstTeamLogoPath = team.logoPath;
      });

      this.teamsService.getTeamById(match.secondTeamId).subscribe(team => {
        match.secondTeamName = team.name;
        match.secondTeamLogoPath = team.logoPath;
      });
    });
    return matches;
  }
}
