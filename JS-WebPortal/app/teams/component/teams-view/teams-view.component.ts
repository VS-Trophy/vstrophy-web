import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {TeamsService} from '../../service/teams.service';
import {Team} from '../../model/Team';
@Component({
    selector: 'vst-teams',
    templateUrl: 'app/teams/component/teams-view/teams-view.component.html'
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
