import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';
import {NewsViewComponent} from './news/component/news-view/news-view.component';
import {NewsManagerComponent} from './news/component/news-manager/news-manager.component';
import {NewsService} from './news/service/news.service';
import {TeamsService} from './teams/service/teams.service';
import {ResultsComponent} from './results/results.component'
import {HistoryComponent} from './history/history.component'
import {TeamsViewComponent} from './teams/component/teams-view/teams-view.component'
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