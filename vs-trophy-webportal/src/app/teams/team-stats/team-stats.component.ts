import { Component, OnInit, Input } from '@angular/core';
import { TeamsService } from '../../teams/teams.service';
import { WinLossRecord } from '../../stats/win-loss-record';
import { StatsService } from '../../stats/stats.service';
import { WinLossRecordOpponent } from '../../stats/win-loss-record-opponent';

@Component({
  selector: 'vst-team-stats',
  templateUrl: './team-stats.component.html',
  styleUrls: ['./team-stats.component.css']
})
export class TeamStatsComponent implements OnInit {

  constructor(private statsService: StatsService, private teamsService: TeamsService) { 
  }


  overallRecord: WinLossRecord

  recordsPerSeason: Map<String, WinLossRecord>

  recordsPerOpponent: WinLossRecordOpponent[]


  @Input('nflId')
  set nflId(nflId: string) {
    this.overallRecord = null;
    this.recordsPerSeason = null;
    this.recordsPerOpponent = null;
    this.statsService.getTeamRecord(nflId).subscribe(record => { this.overallRecord = record })
    this.statsService.getTeamRecordsPerOpponent(nflId).subscribe(
      records => {
        //We got the records, now we need all teams
        this.teamsService.getAllTeams().subscribe(teams => {
          //Replace the team id with the team name
          records.forEach(record => {
            const oppId = record.opponent;
            record.opponentTeam = teams.find(t => t.nflId === oppId);
          })
          //now we can set the records
          this.recordsPerOpponent = records;
        });
      })
  }

  ngOnInit() {
  }

}
