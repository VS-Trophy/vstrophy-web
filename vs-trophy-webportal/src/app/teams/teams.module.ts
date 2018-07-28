import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamsService } from './teams.service';
import { TeamsOverviewComponent } from './teams-overview/teams-overview.component';
import { TeamInfoComponent } from './team-info/team-info.component';
import { AppRoutingModule } from '../app-routing.module';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatGridListModule } from '@angular/material/grid-list';
import { TeamStatsComponent } from './team-stats/team-stats.component';
import { StatsModule } from '../stats/stats.module';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    MatCardModule,
    FlexLayoutModule,
    MatGridListModule,
    StatsModule
  ],
  providers: [
    TeamsService
  ],
  declarations: [TeamsOverviewComponent, TeamInfoComponent,TeamStatsComponent ]
})
export class TeamsModule { }
