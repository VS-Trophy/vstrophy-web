import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SeasonsService } from './seasons.service';
import { SeasonSelectorComponent } from './season-selector/season-selector.component';

@NgModule({
  imports: [
    CommonModule
  ],
  providers:[
    SeasonsService
  ],
  declarations: [SeasonSelectorComponent],
  exports:[SeasonSelectorComponent]
})
export class SeasonModule { }
