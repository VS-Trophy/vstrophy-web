import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatchesModule } from '../matches/matches.module';
import { ResultsViewComponent } from './results-view/results-view.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SeasonModule } from '../season/season.module';

@NgModule({
  imports: [
    CommonModule,
    MatchesModule,
    SeasonModule
  ],
  declarations: [ResultsViewComponent],
  exports: [
    ResultsViewComponent
  ]
  
  
})
export class ResultsModule { }
