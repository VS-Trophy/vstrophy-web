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
  offenseColumnsToDisplay = ['spot', 'name','passing','rushing','receiving', 'points'];
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
