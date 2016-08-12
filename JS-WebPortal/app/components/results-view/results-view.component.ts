import {Component, OnInit} from '@angular/core';
import {CORE_DIRECTIVES, NgModel} from '@angular/common';
import { FORM_DIRECTIVES } from '@angular/forms';
import {MatchesService} from '../../services/matches/matches.service';
import {MatchComponent} from '../match/match.component';
import {Match} from '../../model/match/match';
import {DROPDOWN_DIRECTIVES} from 'ng2-bootstrap/ng2-bootstrap';
import { BUTTON_DIRECTIVES } from 'ng2-bootstrap/ng2-bootstrap';
import { ButtonRadioDirective, ButtonCheckboxDirective } from 'ng2-bootstrap/ng2-bootstrap';

@Component({
    moduleId: module.id,
    selector: 'vst-results',
    templateUrl: 'results-view.component.html',
    directives: [ButtonRadioDirective, ButtonCheckboxDirective, FORM_DIRECTIVES, CORE_DIRECTIVES, BUTTON_DIRECTIVES, DROPDOWN_DIRECTIVES, MatchComponent]
})
export class ResultsViewComponent implements OnInit {
    constructor(private _matchesService: MatchesService) { }
    private _matches: Match[];
    public week: string = '1';
    public radioModel: string = 'Middle';
    public checkModel: any = { left: false, middle: true, right: false };
    ngOnInit() {
        this._matchesService.getMatches(2015, 1)
            .then(matches => this._matches = matches);
    }

       get singleModel() {
        return this.week;
    }

    set singleModel(value) {
        this.week = value;
    }

}