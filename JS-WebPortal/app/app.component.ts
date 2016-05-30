import {Component} from 'angular2/core';
import {NavigationComponent} from './components/navigation/navigation.component';
import {NewsViewComponent} from './components/news-view/news-view.component';
import {NewsItemComponent} from './components/news-item/news-item.component';
import {NewsManagerComponent} from './components/news-manager/news-manager.component';
import {NewsService} from './services/news/news.service';
import {TeamsService} from './services/teams/teams.service';
import {ResultsViewComponent} from './components/results-view/results-view.component'
import {HistoryViewComponent} from './components/history-view/history-view.component'
import {TeamsViewComponent} from './components/teams-view/teams-view.component'
import {TeamDetailComponent} from './components/team-detail/team-detail.component'
import {Configuration} from './configuration/configuration'
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router';
import {HTTP_PROVIDERS}    from 'angular2/http';

@Component({
    selector: 'my-app',
    directives: [NavigationComponent, ROUTER_DIRECTIVES],
    providers: [NewsService,TeamsService, Configuration, ROUTER_PROVIDERS,HTTP_PROVIDERS],
    templateUrl: 'app/app.component.html'
})

@RouteConfig(
    [
        {
            path: '/news',
            name: 'News',
            component: NewsViewComponent,
            useAsDefault: true
        },
        {
            path: '/news/:id',
            name: 'NewsItem',
            component: NewsItemComponent,
        },
        {
            path: '/teams/:id',
            name: 'TeamDetail',
            component: TeamDetailComponent,
        },
        {
            path: '/newsEditor',
            name: 'News Editor',
            component: NewsManagerComponent,
        },
        {
            path: '/teams',
            name: 'Teams',
            component: TeamsViewComponent,
        },
        {
            path: '/results',
            name: 'Results',
            component: ResultsViewComponent,
        },
        {
            path: '/history',
            name: 'History',
            component: HistoryViewComponent,
        },
    ]
)
export class AppComponent { }