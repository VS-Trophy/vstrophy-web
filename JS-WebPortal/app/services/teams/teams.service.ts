import {Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable}     from 'rxjs/Observable';
import {Team} from '../../model/team/team';
import {Configuration} from '../../configuration/configuration';
import 'rxjs/Rx';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class TeamsService {
    private _teamCache: Map<number, Team>
    constructor(private http: Http, private conf: Configuration) {
        this._teamCache = new Map<number, Team>();
    }

    getTeams(): Promise<Team[]> {
        if (this._teamCache.size != 0) {
            return Promise.resolve(this.getTeamsFromCache());
        }
        return this.http.get(this.conf.teamUrl)
            .toPromise()
            .then(res => {
                let array: any[] = res.json();
                this._teamCache.clear();
                array.forEach(obj => this.putInCache(obj));
                return this.getTeamsFromCache();
            }
            )
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

    private putInCache(teamObj: any) {
        let team: Team = new Team(teamObj);
        this._teamCache.set(team.id, team);
    }

    getTeam(id: number): Promise<Team> {
    /*    if (this._teamCache.has(id)) {
           return Promise.resolve(this.getTeamFromCache(id));
        }*/
        return this.http.get(this.conf.teamUrl + "/" + id)
            .toPromise()
            .then(res => new Team(<Team>res.json()))
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }
}