import { Component, OnInit, OnDestroy, Directive as  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VSTrophyTeam } from '../vstrophyteam';
import { TeamsService } from '../teams.service';
import { StatsService } from '../../stats/stats.service';

@()
@Directive()
@Component({
  selector: 'vst-team-info',
  templateUrl: './team-info.component.html',
  styleUrls: ['./team-info.component.css']
})
export class TeamInfoComponent implements OnInit,OnDestroy {

  constructor(private route: ActivatedRoute, private teamsService: TeamsService, private statsService: StatsService) { }
  team: VSTrophyTeam
  private sub: any
  
  ngOnInit() {
    this.sub = this.route.params.subscribe(params => 
      this.setNflId(params['teamId'])
   );
  }

  private setNflId(nflId: string){
    this.teamsService.getTeamById(nflId).subscribe(team => {this.team = team})

  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

}
