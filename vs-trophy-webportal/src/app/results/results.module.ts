import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatchesModule } from '../matches/matches.module';
import { ResultsViewComponent } from './results-view/results-view.component';
import { SeasonModule } from '../season/season.module';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  imports: [
    CommonModule,
    MatchesModule,
    SeasonModule,
    FlexLayoutModule
  ],
  declarations: [ResultsViewComponent],
  exports: [
    ResultsViewComponent
  ]


})
export class ResultsModule { }
