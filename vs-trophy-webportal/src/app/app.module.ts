import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http'


import { AppComponent } from './app.component';
import { TopmatchesComponent } from './topmatches/topmatches.component';
import { StatsService } from './services/stats.service';


@NgModule({
  declarations: [
    AppComponent,
    TopmatchesComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule
  ],
  providers: [StatsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
