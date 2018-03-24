import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatchesModule } from '../matches/matches.module';
import { ResultsViewComponent } from './results-view/results-view.component';

@NgModule({
  imports: [
    CommonModule,
    MatchesModule
  ],
  declarations: [ResultsViewComponent],
  exports: [
    ResultsViewComponent
  ]
  
  
})
export class ResultsModule { }
