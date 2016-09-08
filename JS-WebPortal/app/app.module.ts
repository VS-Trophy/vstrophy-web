import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import { AppComponent }   from './app.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {appRoutingProviders,routing} from './app.routes';
import {ResultsViewComponent} from './components/results-view/results-view.component';
import {HistoryViewComponent} from './components/history-view/history-view.component';
import {TeamsViewComponent} from './components/teams-view/teams-view.component';
import {TeamDetailComponent} from './components/team-detail/team-detail.component';
import {NewsViewComponent} from './components/news-view/news-view.component';
import {NewsItemComponent} from './components/news-item/news-item.component';
import {NewsManagerComponent} from './components/news-manager/news-manager.component';

@NgModule({
  imports: [
    BrowserModule,
    // Router
   routing,
    HttpModule,
    RouterModule
  ],
  providers: [appRoutingProviders],
  declarations: [
    AppComponent,
    NavigationComponent,
    NewsViewComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }