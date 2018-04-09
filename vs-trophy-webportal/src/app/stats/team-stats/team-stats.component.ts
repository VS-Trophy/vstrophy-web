import { Component, OnInit, Input } from '@angular/core';
import { StatsService } from '../stats.service';
import { WinLossRecord } from '../win-loss-record';

@Component({
  selector: 'vst-team-stats',
  templateUrl: './team-stats.component.html',
  styleUrls: ['./team-stats.component.css']
})
export class TeamStatsComponent implements OnInit {

  constructor(private statsService: StatsService) { }


  overallRecord: WinLossRecord

  @Input('nflId')
  set nflId(nflId: string){
    this.statsService.getTeamRecord(nflId).subscribe(record => {this.overallRecord = record})
  }

  ngOnInit() {
  }

}
