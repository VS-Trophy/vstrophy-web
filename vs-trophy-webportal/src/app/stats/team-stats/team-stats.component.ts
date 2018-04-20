import { Component, OnInit, Input } from '@angular/core';
import { StatsService } from '../stats.service';
import { WinLossRecord } from '../win-loss-record';
import { TeamsService } from '../../teams/teams.service';
import { VSTrophyTeam } from '../../teams/vstrophyteam';
import { WinLossRecordOpponent } from '../win-loss-record-opponent';

@Component({
  selector: 'vst-team-stats',
  templateUrl: './team-stats.component.html',
  styleUrls: ['./team-stats.component.css']
})
export class TeamStatsComponent implements OnInit {

  constructor(private statsService: StatsService, private teamsService: TeamsService) { }


  overallRecord: WinLossRecord

  recordsPerSeason: Map<String, WinLossRecord>

  recordsPerOpponent: WinLossRecordOpponent[] 


  @Input('nflId')
  set nflId(nflId: string) {
    this.overallRecord = null;
    this.recordsPerSeason = null;
    this.recordsPerOpponent = null;
    this.statsService.getTeamRecord(nflId).subscribe(record => { this.overallRecord = record })
    this.statsService.getTeamRecordsPerOpponent(nflId).subscribe(records => {this.recordsPerOpponent = records;})
  }

  ngOnInit() {
  }

}
