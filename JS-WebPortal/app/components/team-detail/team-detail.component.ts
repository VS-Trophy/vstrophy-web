import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {TeamsService} from '../../services/teams/teams.service';
import {Team} from '../../model/team/team';
import {RouteParams,ROUTER_DIRECTIVES } from 'angular2/router';

@Component({
    selector: 'vst-team-detail',
    templateUrl: 'app/components/team-detail/team-detail.component.html',
    styleUrls: ['app/components/team-detail/team-detail.component.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class TeamDetailComponent {
    private _team: Team;

    constructor(private _teamsService: TeamsService, private _routeParams:RouteParams) { }

    ngOnInit() {
        this._teamsService.getTeam(+this._routeParams.get('id'))
            .subscribe(t => {this._team = new Team(t)});
    }

}