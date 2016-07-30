import {Component,OnInit} from 'angular2/core';
import {MatchesService} from '../../services/matches/matches.service';
import {Match} from '../../model/match/match';

@Component({
    selector: 'vst-results',
    templateUrl: 'app/components/results-view/results-view.component.html'
})
export class ResultsViewComponent implements OnInit{
    constructor(private _matchesService: MatchesService) { }
     private _matches: Match[];
     ngOnInit() {
        this._matchesService.getMatches(2015,1)
    .subscribe(matches => this._matches = matches);    
    }
    
}