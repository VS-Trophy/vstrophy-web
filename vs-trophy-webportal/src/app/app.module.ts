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
import {MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';

import { NewsModule } from './news/news.module';
import { FormsModule } from '@angular/forms';


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
  providers: [
    //Locale and date format for angular material components
    {provide: MAT_DATE_LOCALE, useValue: 'de-CH'},
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
