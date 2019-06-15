import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { StatsService } from '../stats.service';
import { PointStats } from '../point-stats';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { TeamsService } from '../../teams/teams.service';
import { tap } from 'rxjs/operators';
import { WeekPointer } from '../../season/week-pointer';

@Component({
  selector: 'vst-teams-table',
  templateUrl: './teams-table.component.html',
  styleUrls: ['./teams-table.component.css']
})
export class TeamsTableComponent implements OnInit {

  constructor(private statsService: StatsService, private teamService: TeamsService) { }
  displayedColumns: string[] = ['teamName', 'total', 'matches', 'average', 'max', 'min', 'ratio', 'wins', 'losses'];
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  dataSource = new MatTableDataSource();
  weekPointer: WeekPointer;
  private firstWeekEvent: boolean;

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.firstWeekEvent = true;
  }

  onWeekChange(weekPointer: WeekPointer) {
    if (this.firstWeekEvent) {
      this.firstWeekEvent = false;
      weekPointer.season = -1;
      weekPointer.week = -1;
      this.weekPointer = weekPointer;
    } else {
      if (weekPointer != null) {
        this.statsService.getAllTeamPointStats(weekPointer.season, weekPointer.week).pipe(
          tap(stats =>
            stats
              .forEach(
                stat =>
                  this.teamService.getTeamById(stat.team)
                    .subscribe(team => { stat.teamName = team.name; stat.teamLogoPath = team.logoPath; })))
        ).subscribe(data => this.dataSource.data = data);
      }
    }
  }

}
