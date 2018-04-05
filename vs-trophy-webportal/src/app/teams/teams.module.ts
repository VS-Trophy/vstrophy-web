import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TeamsService } from './teams.service';
import { TeamsOverviewComponent } from './teams-overview/teams-overview.component';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    TeamsService
  ],
  declarations: [TeamsOverviewComponent]
})
export class TeamsModule { }
