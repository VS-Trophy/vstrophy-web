import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ExceptionService } from '../core/exception.service';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { map, tap } from 'rxjs/operators';
import { Season } from './season';

@Injectable()
export class SeasonsService {
  private seasonsPath = 'seasons'
  constructor(private http: HttpClient, private exceptionService: ExceptionService) { }

  getSeasonNumbers(): Observable<number[]>{
    return this.http.get<number[]>(environment.apiRoot + this.seasonsPath + "/list")
  }


  getWeekNumbers(season: number): Observable<number[]>{
    return this.http.get<Season[]>(environment.apiRoot + this.seasonsPath + "/" + season)
    .pipe(
      map(seasons => seasons[0].weeks)
    )
  }
}
