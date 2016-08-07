import {Component,OnInit} from '@angular/core';
import {MatchesService} from '../../services/matches/matches.service';
import {MatchComponent} from '../match/match.component';
import {Match} from '../../model/match/match';

@Component({
    moduleId: module.id,
    selector: 'vst-results',
    templateUrl: 'results-view.component.html',
    directives: [MatchComponent]
})
export class ResultsViewComponent implements OnInit{
    constructor(private _matchesService: MatchesService) { }
     private _matches: Match[];
     ngOnInit() {
        this._matchesService.getMatches(2015,1)
    .subscribe(matches => this._matches = matches);    
    }
    
}