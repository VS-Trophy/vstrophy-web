import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WinLossRecordComponent } from './win-loss-record/win-loss-record.component';
import { StatsService } from './stats.service';
import { SeasonModule } from '../season/season.module';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';


@NgModule({
  imports: [
    CommonModule,
    SeasonModule,
    MatCardModule,
    FlexLayoutModule
  ],
  providers : [StatsService],
  exports : [WinLossRecordComponent],
  declarations: [WinLossRecordComponent]
})
export class StatsModule { }
