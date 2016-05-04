import {Injectable} from 'angular2/core';
import {Http, Response, Headers, RequestOptions} from 'angular2/http';
import {Observable}     from 'rxjs/Observable';
import {Team} from '../model/team';
import {Configuration} from '../../configuration/configuration';
import 'rxjs/Rx';

@Injectable()
export class TeamsService {
    constructor(private http: Http, private conf: Configuration) { }

    getTeams(): Observable<Team[]> {
        return this.http.get(this.conf.teamUrl)
            .map(res => <Team[]>res.json())
            .catch(this.handleError);
    }
    
    getTeam(id:number): Observable<Team>{
        return this.http.get(this.conf.teamUrl + "/" + id)
        .map(res => <Team>res.json())
        .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}