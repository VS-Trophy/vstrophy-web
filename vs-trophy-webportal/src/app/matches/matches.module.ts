import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Match } from './match';
import { MatchesService } from './matches.service';
import { MatchComponent } from './match/match.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { AppRoutingModule } from '../app-routing.module';
import {MatCardModule} from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    MatCardModule,
    FlexLayoutModule,
    MatProgressBarModule
  ],
  providers: [
    MatchesService
  ],
  exports:[
    MatchComponent
  ],
  declarations: [MatchComponent]
})
export class MatchesModule { }
