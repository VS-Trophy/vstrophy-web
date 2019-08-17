import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import { PlayerPerformance } from '../../players/player-performance';

@Component({
  selector: 'vst-roster-table',
  templateUrl: './roster-table.component.html',
  styleUrls: ['./roster-table.component.css']
})
export class RosterTableComponent implements OnInit {
  @ViewChild(MatTable, { static: false }) table: MatTable<PlayerPerformance[]>;
  constructor() { }
  columnsToDisplay = ['spot','name', 'points'];
  dataSource = new MatTableDataSource();


  ngOnInit() {
  }

  @Input('roster')
  set setRoster(value: PlayerPerformance[]) {
    if (value) {
      console.info(value)
      this.dataSource.data = value
      this.table.renderRows()
    }
  }
}
