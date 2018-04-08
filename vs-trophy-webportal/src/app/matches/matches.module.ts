import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Match } from './match';
import { MatchesService } from './matches.service';
import { MatchComponent } from './match/match.component';
import { TeamsModule } from '../teams/teams.module';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule
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
