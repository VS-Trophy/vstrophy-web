import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { TopmatchesComponent } from './topmatches/topmatches.component';
import { StatsService } from './stats.service';


@NgModule({
  declarations: [
    AppComponent,
    TopmatchesComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [StatsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
