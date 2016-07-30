import {Component, Input, OnInit} from 'angular2/core';
import {Match} from '../../model/match/match';
import {Team} from '../../model/team/team';
import {TeamsService} from '../../services/teams/teams.service';
@Component({
    selector: 'vst-match',
    templateUrl: 'app/components/match/match.component.html',
    styleUrls: ['app/components/match/match.component.css']
})

export class MatchComponent implements OnInit {
    @Input() match: Match;
    private _firstTeam: Team;
    private _secondTeam: Team;
    constructor(private _teamsService: TeamsService) { }
    ngOnInit() {
        this._teamsService.getTeam(this.match.firstTeamId)
            .subscribe(t => { this._firstTeam = new Team(t) });

        this._teamsService.getTeam(this.match.secondTeamId)
            .subscribe(t => { this._secondTeam = new Team(t) });
    }
}