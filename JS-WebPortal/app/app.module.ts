import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';
import { ButtonsModule } from 'ng2-bootstrap/ng2-bootstrap';
import { AppComponent }   from './app.component';
import {NavigationComponent} from './components/navigation/navigation.component';
import {appRoutingProviders, routing} from './app.routes';
import {ResultsViewComponent} from './components/results-view/results-view.component';
import {HistoryViewComponent} from './components/history-view/history-view.component';
import {TeamsViewComponent} from './components/teams-view/teams-view.component';
import {TeamDetailComponent} from './components/team-detail/team-detail.component';
import {NewsViewComponent} from './components/news-view/news-view.component';
import {NewsItemComponent} from './components/news-item/news-item.component';
import {NewsManagerComponent} from './components/news-manager/news-manager.component';
import {NewsListComponent} from './components/news-list/news-list.component';
import {NewsEditorComponent} from './components/news-editor/news-editor.component';
import {WeekSelectorComponent} from './components/week-selector/week-selector.component';
import {MatchComponent} from './components/match/match.component';
import { FormsModule }   from '@angular/forms';
import {EditorModule, SharedModule} from 'primeng/primeng';
//TODO: SPLIT THIS UP!!!!!
@NgModule({
  imports: [
    BrowserModule,
    // Router
    routing,
    HttpModule,
    RouterModule,
    FormsModule,
    ButtonsModule,
    EditorModule,
     SharedModule

  ],
  providers: [appRoutingProviders],
  declarations: [
    AppComponent,
    NewsItemComponent,
    NavigationComponent,
    NewsViewComponent,
    NewsManagerComponent,
    HistoryViewComponent,
    TeamDetailComponent,
    NewsListComponent,
    NewsEditorComponent,
    TeamsViewComponent,
    ResultsViewComponent,
    WeekSelectorComponent,
    MatchComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }