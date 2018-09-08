import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatchesModule } from '../matches/matches.module';
import { ResultsViewComponent } from './results-view/results-view.component';
import { SeasonModule } from '../season/season.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { TeamsModule } from '../teams/teams.module';
import { ResultsOverviewComponent } from './results-overview/results-overview.component';
import {MatTabsModule} from '@angular/material/tabs';

@NgModule({
  imports: [
    CommonModule,
    MatchesModule,
    SeasonModule,
    TeamsModule,
    FlexLayoutModule,
    MatTabsModule
  ],
  declarations: [ResultsViewComponent, ResultsOverviewComponent],
  exports: [
    ResultsOverviewComponent
  ]


})
export class ResultsModule { }
