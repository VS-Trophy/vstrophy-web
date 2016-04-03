import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';
import {NewsComponent} from './news/news.component';
import {NewsService} from './news/news.service';
import {ResultsComponent} from './results/results.component'
import {HistoryComponent} from './history/history.component'
import {TeamsComponent} from './teams/teams.component'
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router';

@Component({
    selector: 'my-app',
    directives: [NavigationComponent, NewsComponent, ROUTER_DIRECTIVES],
    providers: [NewsService, ROUTER_PROVIDERS],
    templateUrl: 'app/app.component.html'
})

@RouteConfig(
    [
        {
            path: '/news',
            name: 'News',
            component: NewsComponent,
            useAsDefault: true
        },
        {
            path: '/teams',
            name: 'Teams',
            component: TeamsComponent,
        },
        {
            path: '/results',
            name: 'Results',
            component: ResultsComponent,
        },
        {
            path: '/history',
            name: 'History',
            component: HistoryComponent,
        },
    ]
)
export class AppComponent { }