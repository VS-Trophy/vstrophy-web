import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {TeamsService} from '../../services/teams/teams.service';
import {Team} from '../../model/team/team';
import {ROUTER_DIRECTIVES } from '@angular/router';

@Component({
    moduleId: module.id,
    selector: 'vst-teams',
    templateUrl: 'teams-view.component.html',
    styleUrls: ['teams-view.component.css'],
    directives: [ROUTER_DIRECTIVES]
})

export class TeamsViewComponent {
private _teams : Team[];

    constructor(private _teamsService: TeamsService) { }
    ngOnInit() {
        this._teamsService.getTeams()
            .map(array => array.map(t => new Team(t)))
            .subscribe(teams => {this._teams = teams});
    }
    
}
