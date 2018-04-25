import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SeasonsService } from './seasons.service';
import { SeasonSelectorComponent } from './season-selector/season-selector.component';
import { WeekSelectorComponent } from './week-selector/week-selector.component';
import { MatSelectModule } from '@angular/material/select';

@NgModule({
  imports: [
    CommonModule,
    MatSelectModule
  ],
  providers:[
    SeasonsService
  ],
  declarations: [SeasonSelectorComponent, WeekSelectorComponent],
  exports:[SeasonSelectorComponent, WeekSelectorComponent]
})
export class SeasonModule { }
