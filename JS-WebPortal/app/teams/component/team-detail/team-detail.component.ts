import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {TeamsService} from '../../service/teams.service';
import {Team} from '../../model/Team';
@Component({
    selector: 'vst-team-detail',
    templateUrl: 'app/teams/component/team-detail/team-detail.component.html',
    styleUrls: ['app/teams/component/team-detail/team-detail.component.css']
})
export class TeamDetailComponent {
private _team : Team;

    constructor(private _teamsService: TeamsService) { }
    ngOnInit() {
    }
    
}