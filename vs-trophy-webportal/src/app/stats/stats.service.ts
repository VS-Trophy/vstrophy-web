import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { WinLossRecord } from './win-loss-record';
import { Observable } from 'rxjs/Observable';
import { environment } from '../../environments/environment';
import { ExceptionService } from '../core/exception.service';
import { catchError, tap } from 'rxjs/operators';

@Injectable()
export class StatsService {

  

  constructor(private exceptionService: ExceptionService, private http:HttpClient) { }

  public getTeamRecord(nflId:String): Observable<WinLossRecord>{
    return this.http.get<WinLossRecord>(environment.apiRoot + `stats/team/${nflId}/winloss`)
    .pipe(
      catchError(this.exceptionService.handleHttpError("getTeamRecord", new WinLossRecord()))
    )
  }

}
