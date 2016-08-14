import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES, NgModel} from '@angular/common';
import { FORM_DIRECTIVES } from '@angular/forms';
import {MatchesService} from '../../services/matches/matches.service';
import {MatchComponent} from '../match/match.component';
import {Match} from '../../model/match/match';
import {DROPDOWN_DIRECTIVES} from 'ng2-bootstrap/ng2-bootstrap';
import {WeekSelectorComponent} from '../week-selector/week-selector.component';
import {WeeksService} from '../../services/weeks/weeks.service';
import {Week} from '../../model/Week/Week';

@Component({
    moduleId: module.id,
    selector: 'vst-results',
    templateUrl: 'results-view.component.html',
    directives: [WeekSelectorComponent, FORM_DIRECTIVES, CORE_DIRECTIVES,DROPDOWN_DIRECTIVES, MatchComponent]
})
export class ResultsViewComponent implements OnInit {
    constructor(private _matchesService: MatchesService, private _weekService: WeeksService) { }
    private _matches: Match[];
    public weeks: Week[];

    public radioModel: string = 'Middle';
    public checkModel: any = { left: false, middle: true, right: false };
    ngOnInit() {
        this._weekService.getWeeks(2015).then(list => this.weeks = list);

        this._matchesService.getMatches(2015, 1)
            .then(matches => this._matches = matches);
    }

}