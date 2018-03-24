import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HistoryViewComponent } from './history-view/history-view.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [
    HistoryViewComponent
  ],
  exports: [
    HistoryViewComponent
  ]
})
export class HistoryModule { }
