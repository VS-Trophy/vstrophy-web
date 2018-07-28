import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WinLossRecord } from './win-loss-record';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { ExceptionService } from '../core/exception.service';
import { catchError, tap, map } from 'rxjs/operators';
import { WinLossRecordOpponent } from './win-loss-record-opponent';

@Injectable()
export class StatsService {

  constructor(private exceptionService: ExceptionService, private http:HttpClient) { }

  public getTeamRecord(nflId:string): Observable<WinLossRecord>{
    return this.http.get<WinLossRecord>(environment.apiRoot + `stats/team/${nflId}/winloss`)
    .pipe(
      catchError(this.exceptionService.handleHttpError("getTeamRecord", new WinLossRecord()))
    )
  }

  public getTeamRecordForSeason(nflId:string, season:string): Observable<WinLossRecord>{
    return this.http.get<WinLossRecord>(environment.apiRoot + `stats/team/${nflId}/winloss?season=${season}`)
    .pipe(
      catchError(this.exceptionService.handleHttpError("getTeamRecordSeason", new WinLossRecord()))
    )
  }

  public getTeamRecordAgainstOpponent(nflId:string, opponent:string): Observable<WinLossRecord>{
    return this.http.get<WinLossRecord>(environment.apiRoot + `stats/team/${nflId}/winloss?opponent=${opponent}`)
    .pipe(
      
      catchError(this.exceptionService.handleHttpError("getTeamRecordSeason", new WinLossRecord()))
    )
  }

  public getTeamRecordsPerOpponent(nflId:string): Observable<WinLossRecordOpponent[]>{
    return this.http.get<WinLossRecordOpponent[]>(environment.apiRoot + `stats/team/${nflId}/winloss/opponents`)
    .pipe(
      catchError(this.exceptionService.handleHttpError("getTeamRecordMap", []))
    )
  }


  public getTeamRecordMapForSeason(nflId:string, season:string): Observable<Map<String,WinLossRecord>>{
    return this.http.get<Map<String,WinLossRecord>>(environment.apiRoot + `stats/team/${nflId}/winloss/map?=${season}`)
    .pipe(
      catchError(this.exceptionService.handleHttpError("getTeamRecordMap", new Map()))
    )
  }

}
