import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http'


import { AppComponent } from './app.component';
import { StatsService } from './services/stats.service';
import { AppRoutingModule } from './/app-routing.module';
import { HistoryModule } from './history/history.module';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    HistoryModule
  ],
  providers: [StatsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
