import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { PlayerPerformance } from '../../players/player-performance';

@Component({
  selector: 'vst-offense-roster-table',
  templateUrl: './offense-roster-table.component.html',
  styleUrls: ['./offense-roster-table.component.css']
})
export class OffenseRosterTableComponent implements OnInit {
  @ViewChild("table", { static: true }) table: MatTable<PlayerPerformance[]>;
  constructor() { }
  offenseColumnsToDisplay = ['spot','name', 'points'];
  dataSource = new MatTableDataSource();


  ngOnInit() {
  }

  @Input('roster')
  set setRoster(value: PlayerPerformance[]) {
    if (value && value.length > 0) {
      this.dataSource.data = value
      this.table.renderRows()
    }
  }
}
