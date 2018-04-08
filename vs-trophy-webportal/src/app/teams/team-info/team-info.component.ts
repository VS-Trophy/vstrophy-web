import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VSTrophyTeam } from '../vstrophyteam';
import { TeamsService } from '../teams.service';

@Component({
  selector: 'vst-team-info',
  templateUrl: './team-info.component.html',
  styleUrls: ['./team-info.component.css']
})
export class TeamInfoComponent implements OnInit,OnDestroy {

  constructor(private route: ActivatedRoute, private teamsService: TeamsService) { }
  team: VSTrophyTeam
  private sub: any

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.teamsService.getTeamById(params['teamId']).subscribe(team => this.team = team)
   });
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

}
