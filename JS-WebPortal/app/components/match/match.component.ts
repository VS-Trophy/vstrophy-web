import {Component,Input} from 'angular2/core';
import {Match} from '../../model/match/match';
@Component({
    selector: 'vst-match',
    templateUrl: 'app/components/match/match.component.html',
    styleUrls: ['app/components/match/match.component.css']
})

export class MatchComponent {
    @Input() match:Match;
}