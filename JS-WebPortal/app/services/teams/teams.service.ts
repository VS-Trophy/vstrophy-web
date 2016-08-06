import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable}     from 'rxjs/Observable';
import {Team} from '../../model/team/team';
import {Configuration} from '../../configuration/configuration';
import 'rxjs/Rx';

@Injectable()
export class TeamsService {
    private _teamCache: Map<number, Team>
    constructor(private http: Http, private conf: Configuration) {
        this._teamCache = new Map<number, Team>();
    }




    getTeams(): Observable<Team[]> {
        if (this._teamCache.size != 0) {
            return Observable.create((subscriber) => {
                subscriber.next(this.getTeamsFromCache());
                subscriber.complete();
            });
        }
        return this.http.get(this.conf.teamUrl)
            .map(res => {
                let teams = <Team[]>res.json();
                this.fillCache(teams);
                return teams;
            })
            .catch(this.handleError);
    }

    private getTeamsFromCache(): Team[] {
        let teams: Team[] = new Array<Team>();
        this._teamCache.forEach(value => teams.push(value));
        return teams;
    }

    private getTeamFromCache(id: number) {
        return this._teamCache.get(id);
    }

    private fillCache(teams: Team[]) {
        this._teamCache.clear();
        teams.forEach(t => this._teamCache.set(t.id, t));
    }

    private putInCache(team: Team) {
        this._teamCache.set(team.id, team);
    }

    getTeam(id: number): Observable<Team> {
        if (this._teamCache.has(id)) {
            return Observable.create((subscriber) => {
                subscriber.next(this.getTeamFromCache(id));
                subscriber.complete();
            });
        }
        return this.http.get(this.conf.teamUrl + "/" + id)
            .map(res => <Team>res.json())
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}