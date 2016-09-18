import {Component, OnInit} from '@angular/core';
import {MatchesService} from '../../services/matches/matches.service';
import {MatchComponent} from '../match/match.component';
import {Match} from '../../model/match/match';
import {WeekSelectorComponent} from '../week-selector/week-selector.component';
import {WeeksService} from '../../services/weeks/weeks.service';
import {Week} from '../../model/Week/Week';

@Component({
    moduleId: module.id,
    selector: 'vst-results',
    templateUrl: 'results-view.component.html',
})
export class ResultsViewComponent implements OnInit {
    constructor(private _matchesService: MatchesService, private _weekService: WeeksService) {
        this.weeks = new Array<Week>();
    }
    private _matches: Match[];
    private _season: number;
    private weeks: Week[];
    private seasons: number[];

    private _radioModel: number;
    ngOnInit() {
        this._weekService.getSeasons().then(list => this.seasons = list);

    }

    onWeekSelect(weekNumber: number) {
        this._matchesService.getMatches(this._season, weekNumber).then(matches => this._matches = matches);
    }

    set radioModel(model: number) {
        this._radioModel = model;
        if (model != undefined) {
            this.onWeekSelect(model);
        }
    }

    get radioModel() {
        return this._radioModel;
    }

    onSeasonSelect(season) {
        if (season != undefined) {
            this._season = season;
            this._weekService.getWeeks(season).then(list => {
                this.weeks = list;
                this.onWeekSelect(this.weeks[this.weeks.length - 1].number);
            });
        }
    }

}