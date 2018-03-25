import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http'

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { MatchesModule } from './matches/matches.module';
import { ResultsModule } from './results/results.module';
import { TeamsModule } from './teams/teams.module';
import { CoreModule } from './core/core.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    //From angular
    HttpClientModule,
    BrowserModule,
    //Routing
    AppRoutingModule,
    //Core
    CoreModule,
    //Features
    TeamsModule,
    MatchesModule,
    ResultsModule,
    //Third party
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
