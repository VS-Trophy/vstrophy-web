import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { StatsService } from '../stats.service';
import { PointStats } from '../point-stats';
import { MatSort, MatTableDataSource } from '@angular/material';
import { TeamsService } from '../../teams/teams.service';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'vst-teams-table',
  templateUrl: './teams-table.component.html',
  styleUrls: ['./teams-table.component.css']
})
export class TeamsTableComponent implements OnInit {

  constructor(private statsService: StatsService, private teamService: TeamsService) { }
  displayedColumns: string[] = ['teamName', 'total', 'matches', 'average', 'max', 'min', 'ratio', 'wins', 'losses'];
  @ViewChild(MatSort) sort: MatSort;
  dataSource = new MatTableDataSource();

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.statsService.getAllTeamPointStats().pipe(
      tap(stats =>
        stats
          .forEach(
            stat =>
              this.teamService.getTeamById(stat.team)
              .subscribe(team => { stat.teamName = team.name; stat.teamLogoPath = team.logoPath; })))
    ).subscribe(data => this.dataSource.data = data);

  }

}
