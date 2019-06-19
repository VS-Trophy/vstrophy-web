import { Component, OnInit, Input } from '@angular/core';
import { TeamsService } from '../../teams/teams.service';
import { WinLossRecord } from '../../stats/win-loss-record';
import { StatsService } from '../../stats/stats.service';
import { WinLossRecordOpponent } from '../../stats/win-loss-record-opponent';
import { SeasonsService } from '../../season/seasons.service';
import { WinLossRecordSeason } from '../../stats/win-loss-record-opponent.1';
import { Observable, merge } from 'rxjs';
import { toArray } from 'rxjs/operators';
import { PointStats } from '../../stats/point-stats';

@Component({
  selector: 'vst-team-stats',
  templateUrl: './team-stats.component.html',
  styleUrls: ['./team-stats.component.css']
})
export class TeamStatsComponent implements OnInit {

  constructor(private statsService: StatsService, private teamsService: TeamsService, private seasonService: SeasonsService) {
  }


  overallRecord: WinLossRecord

  recordsPerSeason: WinLossRecordSeason[]

  recordsPerOpponent: WinLossRecordOpponent[]

  pointStats: PointStats


  @Input('nflId')
  set nflId(nflId: string) {
    this.overallRecord = null;
    this.recordsPerSeason = [];
    this.recordsPerOpponent = null;
    //Get the overall record
    this.statsService.getTeamRecord(nflId).subscribe(record => { this.overallRecord = record })
    //Get the record per opponent
    this.statsService.getTeamRecordsPerOpponent(nflId).subscribe(
      records => {
        //We got the records, now we need all teams
        this.teamsService.getAllTeams().subscribe(teams => {
          //Replace the team id with the team name
          records.forEach(record => {
            const oppId = record.opponent;
            record.opponentTeam = teams.find(t => t._key === oppId);
          })
          //now we can set the records
          this.recordsPerOpponent = records;
        });
      })

    //Get the record per season
    this.seasonService.getSeasonNumbers()
      .subscribe(seasonNumbers => {
        var observables: Observable<WinLossRecordSeason>[] =
        seasonNumbers
          .map(season => this.statsService.getTeamRecordForSeason(nflId, season + ""))
          const mergedObservable = merge(...observables);
          mergedObservable
          .pipe(toArray())
          .subscribe(records => { records.sort((a, b) => b.season - a.season); this.recordsPerSeason = records; });
      });

      //get the point stats

      this.statsService.getTeamPointStats(nflId).subscribe(pointstats => {this.pointStats = pointstats; console.log(pointstats)});

   
   
  }



  ngOnInit() {
  }

}
