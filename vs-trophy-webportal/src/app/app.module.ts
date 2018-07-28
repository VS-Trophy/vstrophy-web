import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http'
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './/app-routing.module';
import { MatchesModule } from './matches/matches.module';
import { ResultsModule } from './results/results.module';
import { TeamsModule } from './teams/teams.module';
import { CoreModule } from './core/core.module';

import {MatSelectModule} from '@angular/material/select';
import { NewsModule } from './news/news.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    //From angular
    HttpClientModule,
    BrowserModule,
    //Material
    BrowserAnimationsModule,
    MatSelectModule,
    //Flex Layout
    FlexLayoutModule, 
    //Routing
    AppRoutingModule,
    //Core
    CoreModule,
    //Features
    TeamsModule,
    MatchesModule,
    ResultsModule,
    NewsModule
    
    //Third party

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
