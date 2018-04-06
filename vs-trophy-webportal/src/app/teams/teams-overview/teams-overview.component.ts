import { Component, OnInit } from '@angular/core';
import { TeamsService } from '../teams.service';
import { VSTrophyTeam } from '../vstrophyteam';

@Component({
  selector: 'vst-teams-overview',
  templateUrl: './teams-overview.component.html',
  styleUrls: ['./teams-overview.component.css']
})
export class TeamsOverviewComponent implements OnInit {

  constructor(private teamsService:TeamsService) { }

  teams: VSTrophyTeam[]

  ngOnInit() {
    this.loadAllTeams()
  }

  private loadAllTeams(){
    this.teamsService.getAllTeams().subscribe(teams => this.teams = teams)
  }

}
