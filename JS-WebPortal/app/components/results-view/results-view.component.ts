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
    private _season:number;
    public weeks: Week[];
    public seasons:number[];

    public radioModel: string = 'Middle';
    public checkModel: any = { left: false, middle: true, right: false };
    ngOnInit() {
        this._weekService.getSeasons().then(list => this.seasons = list);

    }

    onWeekSelect(weekNumber:number){
        console.log("ONWeek");
     this._matchesService.getMatches(this._season,weekNumber).then(matches => this._matches = matches);
    }


    onSeasonSelect(season){
        this._season=season;
        this._weekService.getWeeks(season).then(list => {this.weeks = list});
    }

}