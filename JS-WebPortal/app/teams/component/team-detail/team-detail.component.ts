import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {TeamsService} from '../../service/teams.service';
import {Team} from '../../model/Team';
import {RouteParams,ROUTER_DIRECTIVES } from 'angular2/router';

@Component({
    selector: 'vst-team-detail',
    templateUrl: 'app/teams/component/team-detail/team-detail.component.html',
    styleUrls: ['app/teams/component/team-detail/team-detail.component.css'],
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