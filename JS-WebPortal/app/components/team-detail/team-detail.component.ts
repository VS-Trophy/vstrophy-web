import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {TeamsService} from '../../services/teams/teams.service';
import {Team} from '../../model/team/team';
import {ActivatedRoute,ROUTER_DIRECTIVES } from '@angular/router';

@Component({
    selector: 'vst-team-detail',
    templateUrl: 'app/components/team-detail/team-detail.component.html',
    styleUrls: ['app/components/team-detail/team-detail.component.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class TeamDetailComponent {
    private _team: Team;

    constructor(private _teamsService: TeamsService, private _route:ActivatedRoute) { }

    ngOnInit() {
        this._route.params.subscribe(params =>  this._teamsService.getTeam(+params['id'])
            .subscribe(t => {this._team = new Team(t)}))
    }

}