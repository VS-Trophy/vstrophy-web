import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';
import {NewsComponent} from './news/news.component';
import {NewsService} from './news/news.service';
import {ResultsComponent} from './results/results.component'
import {HistoryComponent} from './history/history.component'
import {TeamsComponent} from './teams/teams.component'
import {Configuration} from './configuration/configuration'
import { RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS } from 'angular2/router';
import {HTTP_PROVIDERS}    from 'angular2/http';

@Component({
    selector: 'my-app',
    directives: [NavigationComponent, NewsComponent, ROUTER_DIRECTIVES],
    providers: [NewsService, Configuration, ROUTER_PROVIDERS,HTTP_PROVIDERS],
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