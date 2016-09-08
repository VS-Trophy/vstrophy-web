import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import { AppComponent }   from './app.component';
import {appRoutingProviders} from './app.routes';

@NgModule({
  imports: [
    BrowserModule,
    // Router
    RouterModule.forRoot(appRoutingProviders),
    HttpModule,
    RouterModule
  ],
  providers: [],
  declarations: [AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }