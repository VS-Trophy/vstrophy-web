import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { PlayerPerformance } from '../../players/player-performance';

@Component({
  selector: 'vst-defense-roster-table',
  templateUrl: './defense-roster-table.component.html',
  styleUrls: ['./defense-roster-table.component.css']
})
export class DefenseRosterTableComponent implements OnInit {
  @ViewChild(MatTable, { static: false }) table: MatTable<PlayerPerformance[]>;
  constructor() { }
  defenseColumnsToDisplay = ['spot','name', 'points'];
  dataSource = new MatTableDataSource();


  ngOnInit() {
  }

  @Input('roster')
  set setRoster(value: PlayerPerformance[]) {
    if (value && value.length >0) {

      this.dataSource.data = value
      this.table.renderRows()
    }
  }
}
