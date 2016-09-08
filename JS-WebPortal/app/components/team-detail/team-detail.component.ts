import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {TeamsService} from '../../services/teams/teams.service';
import {Team} from '../../model/team/team';
import {ActivatedRoute} from '@angular/router';


@Component({
    moduleId: module.id,
    selector: 'vst-team-detail',
    templateUrl: 'team-detail.component.html',
    styleUrls: ['team-detail.component.css'],
})
export class TeamDetailComponent {
    private _team: Team;

    constructor(private _teamsService: TeamsService, private _route:ActivatedRoute) { }

    ngOnInit() {
        this._route.params.subscribe(params =>  this._teamsService.getTeam(+params['id'])
            .then(t => {this._team = t}))
    }

}