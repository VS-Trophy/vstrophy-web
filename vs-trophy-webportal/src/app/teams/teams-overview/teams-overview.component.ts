import { Component, OnInit } from '@angular/core';
import { TeamsService } from '../teams.service';
import { VSTrophyTeam } from '../vstrophyteam';
import { Router } from '@angular/router';

@Component({
  selector: 'vst-teams-overview',
  templateUrl: './teams-overview.component.html',
  styleUrls: ['./teams-overview.component.css']
})
export class TeamsOverviewComponent implements OnInit {

  constructor(private teamsService: TeamsService, private router: Router) { }
  whiteStarTeams: VSTrophyTeam[]
  redStarTeams: VSTrophyTeam[]

  ngOnInit() {
    this.loadAllTeams()
  }

  private loadAllTeams() {
    this.whiteStarTeams = []
    this.redStarTeams = []
    this.teamsService.getAllTeams().subscribe(teams => teams.forEach(team => this.addTeam(team)));
  }

  private addTeam(team: VSTrophyTeam){
    console.info("Adding team" + team.name)
    if(team.division == "White Star"){
      this.whiteStarTeams.push(team);
    } else if(team.division == "Red Star"){
      this.redStarTeams.push(team);
    }
  }

  cardClicked(team: VSTrophyTeam) {
    this.router.navigate(['teams/' + team._key])
  }

}
