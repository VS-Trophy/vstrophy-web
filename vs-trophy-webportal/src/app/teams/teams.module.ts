import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamsService } from './teams.service';
import { TeamsOverviewComponent } from './teams-overview/teams-overview.component';
import { TeamInfoComponent } from './team-info/team-info.component';
import { AppRoutingModule } from '../app-routing.module';
import { StatsModule } from '../stats/stats.module';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    StatsModule
  ],
  providers: [
    TeamsService
  ],
  declarations: [TeamsOverviewComponent, TeamInfoComponent]
})
export class TeamsModule { }
