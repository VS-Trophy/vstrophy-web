import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WinLossRecordComponent } from './win-loss-record/win-loss-record.component';
import { StatsService } from './stats.service';

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [StatsService],
  exports : [WinLossRecordComponent],
  declarations: [WinLossRecordComponent]
})
export class StatsModule { }
