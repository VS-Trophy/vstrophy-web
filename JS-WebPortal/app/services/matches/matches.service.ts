import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable}     from 'rxjs/Observable';
import {Match} from '../../model/match/match'
import {Configuration} from '../../configuration/configuration';
import 'rxjs/Rx';

@Injectable()
export class MatchesService {
    constructor(private http: Http, private conf: Configuration) { }
    getMatches(season: number, week: number): Promise<Match[]> {
        return this.http.get(this.conf.matchUrl + "?year=" + season + "&week=" + week)
            .toPromise()
            .then(res => <Match[]>res.json())
            .catch(this.handleError);
    }


    private handleError(error: Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

}