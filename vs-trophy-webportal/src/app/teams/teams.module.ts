import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamsService } from './teams.service';
import { TeamsOverviewComponent } from './teams-overview/teams-overview.component';
import { TeamInfoComponent } from './team-info/team-info.component';
import { AppRoutingModule } from '../app-routing.module';
import { StatsModule } from '../stats/stats.module';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatGridListModule } from '@angular/material/grid-list';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    StatsModule,
    MatCardModule,
    FlexLayoutModule,
    MatGridListModule
  ],
  providers: [
    TeamsService
  ],
  declarations: [TeamsOverviewComponent, TeamInfoComponent]
})
export class TeamsModule { }
