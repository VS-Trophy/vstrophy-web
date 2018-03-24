import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HistoryViewComponent } from './history-view/history-view.component';
import { MatchesService } from '../matches/shared/matches.service';
import { MatchesModule } from '../matches/matches.module';

@NgModule({
  imports: [
    CommonModule,
    MatchesModule
  ],
  declarations: [
    HistoryViewComponent
  ],
  exports: [
    HistoryViewComponent
  ]
})
export class HistoryModule { }
