import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {TeamsService} from '../../services/teams/teams.service';
import {Team} from '../../model/team/team';


@Component({
    moduleId: module.id,
    selector: 'vst-teams',
    templateUrl: 'teams-view.component.html',
    styleUrls: ['teams-view.component.css'],
})

export class TeamsViewComponent {
    private _teams: Team[];

    constructor(private _teamsService: TeamsService) { }
    ngOnInit() {
        this._teamsService.getTeams()
            .then(teams => {
                this._teams = teams;
            })
    }

}
