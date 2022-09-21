import { Component, OnInit, Input, Directive as  } from '@angular/core';
import { WinLossRecord } from '../win-loss-record';

@()
@Directive()
@Component({
  selector: 'vst-win-loss-record',
  templateUrl: './win-loss-record.component.html',
  styleUrls: ['./win-loss-record.component.css']
})
export class WinLossRecordComponent implements OnInit {

@Input()
winlossRecord: WinLossRecord

@Input()
title: string

@Input()
logoPath: string
  constructor() { }

  ngOnInit() {
  }

}
