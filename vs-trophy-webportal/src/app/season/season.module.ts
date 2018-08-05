import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SeasonsService } from './seasons.service';
import { SeasonSelectorComponent } from './season-selector/season-selector.component';
import { WeekSelectorComponent } from './week-selector/week-selector.component';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  imports: [
    CommonModule,
    //Angular Material
    MatFormFieldModule,
    MatSelectModule,
    //Flex Layout
    FlexLayoutModule
  ],
  providers: [
    SeasonsService
  ],
  declarations: [SeasonSelectorComponent, WeekSelectorComponent],
  exports: [SeasonSelectorComponent, WeekSelectorComponent]
})
export class SeasonModule { }
