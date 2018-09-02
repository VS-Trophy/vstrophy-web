import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WinLossRecord } from './win-loss-record';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ExceptionService } from '../core/exception.service';
import { catchError, map } from 'rxjs/operators';
import { WinLossRecordOpponent } from './win-loss-record-opponent';
import { TeamsService } from '../teams/teams.service';
import { WinLossRecordSeason } from './win-loss-record-opponent.1';
import { PointStats } from './point-stats';


@Injectable()
export class StatsService {

  constructor(private exceptionService: ExceptionService, private teamsService: TeamsService, private http: HttpClient) { }

  public getTeamRecord(nflId: string): Observable<WinLossRecord> {
    return this.http.get<WinLossRecord>(environment.apiRoot + `stats/team/${nflId}/winloss`)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getTeamRecord", new WinLossRecord()))
      )
  }

  public getTeamRecordForSeason(nflId: string, season: string): Observable<WinLossRecordSeason> {
    return this.http.get<WinLossRecord>(environment.apiRoot + `stats/team/${nflId}/winloss?season=${season}`)
      .pipe(
        map(record => {const seasonRecord: WinLossRecordSeason = new WinLossRecordSeason(); seasonRecord.season = +season; seasonRecord.record = record; return seasonRecord;}),
        catchError(this.exceptionService.handleHttpError("getTeamRecordSeason", new WinLossRecordSeason()))
      )
  }

  public getTeamRecordAgainstOpponent(nflId: string, opponent: string): Observable<WinLossRecord> {
    return this.http.get<WinLossRecord>(environment.apiRoot + `stats/team/${nflId}/winloss?opponent=${opponent}`)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getTeamRecordSeason", new WinLossRecord()))
      )
  }

  public getTeamRecordsPerOpponent(nflId: string): Observable<WinLossRecordOpponent[]> {
    return this.http.get<WinLossRecordOpponent[]>(environment.apiRoot + `stats/team/${nflId}/winloss/opponents`)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getTeamRecordMap", []))
      )
  }


  public getTeamRecordMapForSeason(nflId: string, season: string): Observable<Map<String, WinLossRecord>> {
    return this.http.get<Map<String, WinLossRecord>>(environment.apiRoot + `stats/team/${nflId}/winloss/map?=${season}`)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getTeamRecordMap", new Map()))
      )
  }

  public getTeamPointStats(nflId: string): Observable<PointStats> {
    return this.http.get<PointStats>(environment.apiRoot + `stats/team/${nflId}/pointstats`)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getTeamRecordMap", new PointStats))
      )
  }

}
