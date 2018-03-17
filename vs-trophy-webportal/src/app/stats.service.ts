import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class StatsService {

  constructor(private http: HttpClient) { }

  private topMatchesUrl = 'http://localhost:8529/_db/vs-trophy/stats/performances';

  getTopMatches(): Observable<string>{
    return this.http.get<string>(this.topMatchesUrl);
  }

}
