import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class StatsService {

  constructor() { }

  getTopMatches(): Observable<string>{
    return of("Stats via observable");
  }

}
