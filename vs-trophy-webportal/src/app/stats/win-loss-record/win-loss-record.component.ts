import { Component, OnInit, Input } from '@angular/core';
import { StatsService } from '../stats.service';
import { WinLossRecord } from '../win-loss-record';

@Component({
  selector: 'vst-win-loss-record',
  templateUrl: './win-loss-record.component.html',
  styleUrls: ['./win-loss-record.component.css']
})
export class WinLossRecordComponent implements OnInit {

@Input()
nflId: String

winlossRecord: WinLossRecord

  constructor(private statsService:StatsService) { }

  ngOnInit() {
    this.statsService.getTeamRecord(this.nflId)
    .subscribe(record => this.winlossRecord = record);
  }

}
