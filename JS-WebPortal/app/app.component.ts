import {Component} from '@angular/core';

import {NewsService} from './services/news/news.service';
import {TeamsService} from './services/teams/teams.service';
import {MatchesService} from './services/matches/matches.service';
import {NavigationComponent} from './components/navigation/navigation.component';
import {Configuration} from './configuration/configuration'
import { ROUTER_DIRECTIVES } from '@angular/router';
import {HTTP_PROVIDERS}    from '@angular/http';

//Views
import {ResultsViewComponent} from './components/results-view/results-view.component'
import {HistoryViewComponent} from './components/history-view/history-view.component'
import {TeamsViewComponent} from './components/teams-view/teams-view.component'
import {TeamDetailComponent} from './components/team-detail/team-detail.component'
import {NewsViewComponent} from './components/news-view/news-view.component';
import {NewsItemComponent} from './components/news-item/news-item.component';
import {NewsManagerComponent} from './components/news-manager/news-manager.component';


@Component({
    selector: 'my-app',
    directives: [NavigationComponent,ROUTER_DIRECTIVES],
    providers: [NewsService,TeamsService,MatchesService, Configuration,HTTP_PROVIDERS],
    templateUrl: 'app/app.component.html',
    precompile: [ResultsViewComponent,HistoryViewComponent,TeamsViewComponent,TeamDetailComponent,NewsViewComponent,NewsItemComponent,NewsManagerComponent]
})



export class AppComponent { }