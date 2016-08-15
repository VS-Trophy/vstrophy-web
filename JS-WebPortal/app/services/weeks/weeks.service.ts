import {Injectable} from '@angular/core';
import {Week} from '../../model/week/week';
import {Http, Response}  from '@angular/http';
import {Configuration} from '../../configuration/configuration';

@Injectable()
export class WeeksService {
    constructor(private http: Http, private conf: Configuration) { }
    getWeeks(season: number): Promise<Week[]> {
        return this.http.get(this.conf.weekUrl + '/' + season)
            .toPromise()
            .then(res => res.json() as Week[])
            .catch(this.handleError);
    }


    getSeasons(): Promise<number[]> {
        return Promise.resolve(this.conf.getSeasons());
    }

    private handleError(error: Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
    }
}