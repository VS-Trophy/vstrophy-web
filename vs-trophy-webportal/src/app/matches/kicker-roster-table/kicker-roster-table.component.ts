import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { PlayerPerformance } from '../../players/player-performance';

@Component({
  selector: 'vst-kicker-roster-table',
  templateUrl: './kicker-roster-table.component.html',
  styleUrls: ['./kicker-roster-table.component.css']
})
export class KickerRosterTableComponent implements OnInit {
  @ViewChild(MatTable, { static: false }) table: MatTable<PlayerPerformance[]>;
  constructor() { }
  kickerColumnsToDisplay = ['spot','name', 'extrapoints', 'fieldgoals','long','points', ];
  dataSource = new MatTableDataSource();


  ngOnInit() {
  }

  @Input('roster')
  set setRoster(value: PlayerPerformance[]) {
    this.dataSource.data = value
    if (value && value.length > 0 && this.table != null) {
      this.table.renderRows()
    }
  }
}
