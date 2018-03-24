import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Match } from './match';
import { MatchesService } from './shared/matches.service';

@NgModule({
  imports: [
    CommonModule
  ],
  providers: [
    MatchesService
  ],
  exports:[

  ]
})
export class MatchesModule { }
