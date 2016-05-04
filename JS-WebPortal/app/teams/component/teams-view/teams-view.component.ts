import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {TeamsService} from '../../service/teams.service';
import {Team} from '../../model/Team';
import {ROUTER_DIRECTIVES } from 'angular2/router';

@Component({
    selector: 'vst-teams',
    templateUrl: 'app/teams/component/teams-view/teams-view.component.html',
    styleUrls: ['app/teams/component/teams-view/teams-view.component.css'],
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
