import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WinLossRecordComponent } from './win-loss-record/win-loss-record.component';
import { StatsService } from './stats.service';
import { SeasonModule } from '../season/season.module';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { SimpleStatComponent } from './simple-stat/simple-stat.component';
import { StatsViewComponent } from './stats-view/stats-view.component';
import { TeamsTableComponent } from './teams-table/teams-table.component';
import { MatTabsModule } from '@angular/material';
import {MatTableModule} from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';


@NgModule({
  imports: [
    CommonModule,
    SeasonModule,
    MatCardModule,
    FlexLayoutModule,
    MatProgressBarModule,
    MatTabsModule,
    MatTableModule,
    MatSortModule
  ],
  providers : [StatsService],
  exports : [WinLossRecordComponent, SimpleStatComponent, StatsViewComponent],
  declarations: [WinLossRecordComponent, SimpleStatComponent, StatsViewComponent, TeamsTableComponent]
})
export class StatsModule { }
