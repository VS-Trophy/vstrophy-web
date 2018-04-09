import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WinLossRecordComponent } from './win-loss-record/win-loss-record.component';
import { StatsService } from './stats.service';
import { TeamStatsComponent } from './team-stats/team-stats.component';

@NgModule({
  imports: [
    CommonModule
  ],
  providers : [StatsService],
  exports : [TeamStatsComponent,WinLossRecordComponent],
  declarations: [WinLossRecordComponent, TeamStatsComponent]
})
export class StatsModule { }
