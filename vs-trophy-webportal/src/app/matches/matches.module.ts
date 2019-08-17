import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Match } from './match';
import { MatchesService } from './matches.service';
import { MatchComponent } from './match/match.component';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { AppRoutingModule } from '../app-routing.module';
import {MatCardModule} from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatchListComponent } from './match-list/match-list.component';
import {MatBadgeModule} from '@angular/material/badge';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatchDetailsComponent } from './match-details/match-details.component';
import { MatTableModule } from '@angular/material/table';
import { RosterTableComponent } from './roster-table/roster-table.component';

@NgModule({
  imports: [
    CommonModule,
    AppRoutingModule,
    MatCardModule,
    FlexLayoutModule,
    MatProgressBarModule,
    MatTableModule,
    MatBadgeModule
  ],
  providers: [
    MatchesService
  ],
  exports:[
    MatchComponent,MatchListComponent
  ],
  declarations: [MatchComponent, MatchListComponent, MatchDetailsComponent, RosterTableComponent]
})
export class MatchesModule { }
