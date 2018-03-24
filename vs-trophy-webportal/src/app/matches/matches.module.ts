import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Match } from './match';
import { MatchesService } from './matches.service';
import { MatchComponent } from './match/match.component';

@NgModule({
  imports: [
    CommonModule
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
