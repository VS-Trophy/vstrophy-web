import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WinLossRecordComponent } from './win-loss-record/win-loss-record.component';
import { StatsService } from './stats.service';
import { SeasonModule } from '../season/season.module';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { SimpleStatComponent } from './simple-stat/simple-stat.component';


@NgModule({
  imports: [
    CommonModule,
    SeasonModule,
    MatCardModule,
    FlexLayoutModule,
    MatProgressBarModule
  ],
  providers : [StatsService],
  exports : [WinLossRecordComponent, SimpleStatComponent],
  declarations: [WinLossRecordComponent, SimpleStatComponent]
})
export class StatsModule { }
