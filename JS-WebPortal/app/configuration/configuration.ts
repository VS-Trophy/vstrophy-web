import {Injectable} from '@angular/core';

@Injectable()
export class Configuration {
    baseURL: string;
    newsItemUrl: string;
    teamUrl: string;
    matchUrl: string;
    weekUrl: string;
    firstSeason: number = 2012;
    lastSeason: number = 2015;
    constructor() {
        var host: string = "vstrophy.ch";
        this.baseURL = "http://" + host + "/rest-api/";
        this.newsItemUrl = this.baseURL + "newsitem";
        this.teamUrl = this.baseURL + "team";
        this.matchUrl = this.baseURL + "match";
        this.weekUrl = this.baseURL + "week";
    }

    //We could get this out of the database. But this works as well ;)
    getSeasons(): number[] {
        var seasons = [];
        for (var i = this.firstSeason; i <= this.lastSeason; i++) {
            seasons.push(i);
        }
        return seasons;
    }
}