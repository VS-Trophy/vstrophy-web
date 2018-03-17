import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { TopmatchesComponent } from './topmatches/topmatches.component';


@NgModule({
  declarations: [
    AppComponent,
    TopmatchesComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
