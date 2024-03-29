import { of as observableOf, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { VSTrophyTeam } from './vstrophyteam';
import { catchError, map, share, filter, single, expand, mergeAll, mergeMap, merge } from 'rxjs/operators';
import { ExceptionService } from '../core/exception.service';

@Injectable()
export class TeamsService {

  private _logoBasePath = '/assets/images/teamlogos/';
  private _fileSuffix = '.png';
  private _teamsPath = 'teams';
  private _teamMap: Map<String, VSTrophyTeam>;
  private _observable: Observable<VSTrophyTeam[]>;


  constructor(private _http: HttpClient, private _exceptionService: ExceptionService) {
  }

  public getTeamById(nflId: string): Observable<VSTrophyTeam> {
    if (this._teamMap) {
      const team = this._teamMap.get(nflId);
      if (team == null) {
        console.warn('Could not load team with nflId ' + nflId);
      }
      return observableOf(team);
    } else if (this._observable) {
      return this._observable.pipe(
        map(teams => teams.filter(team => team._key === nflId)[0])
      );
    } else {
      return this.getAllTeams().pipe(
        map(teams => teams.filter(team => team._key === nflId)[0])
      );
    }
  }



  private addTeamToTeamMap(team: VSTrophyTeam): void {
    team.logoPath = this._logoBasePath + team._key + this._fileSuffix;
    this._teamMap.set(team._key, team);
  }

  private getCachedTeams(): VSTrophyTeam[] {
    return Array.from(this._teamMap.values());
  }

  public getAllTeams(): Observable<VSTrophyTeam[]> {
    if (this._teamMap) {
      return observableOf(this.getCachedTeams());
    } else if (this._observable) {
      return this._observable;
    } else {
      this._observable = this._http.get<VSTrophyTeam[]>(environment.apiRoot + this._teamsPath).pipe(
        catchError(this._exceptionService.handleHttpError('getAllMatches', [])),

        map(teams => {
          this._observable = null;
          this._teamMap = new Map<String, VSTrophyTeam>();
          teams.forEach(team => this.addTeamToTeamMap(team));
          return this.getCachedTeams();
        }),
        share()
      );
      return this._observable;
    }
  }
}
