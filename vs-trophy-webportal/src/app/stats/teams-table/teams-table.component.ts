import { Component, OnInit, ViewChild } from '@angular/core';
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
  displayedColumns: string[] = ['teamName','total','matches', 'average', 'max', 'min','ratio','wins','losses'];
  @ViewChild(MatSort) sort: MatSort;
  dataSource = new MatTableDataSource();

  ngOnInit() {
    this.dataSource.sort = this.sort;
    this.statsService.getAllTeamPointStats().pipe(
      tap(stats => stats.forEach(stat => this.teamService.getTeamById(stat.team).subscribe(team => {console.info("got team "  + team + " for " + stat.team);stat.teamName = team.name})))
    ).subscribe(
      data => this.dataSource.data = data
    )

  }

}
