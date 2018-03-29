import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SeasonsService } from './seasons.service';
import { SeasonSelectorComponent } from './season-selector/season-selector.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    NgbModule
  ],
  providers:[
    SeasonsService
  ],
  declarations: [SeasonSelectorComponent],
  exports:[SeasonSelectorComponent]
})
export class SeasonModule { }
