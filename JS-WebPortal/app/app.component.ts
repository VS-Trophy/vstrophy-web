import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';
import {NewsComponent} from './news/news.component';
import {NewsService} from './news/news.service';
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
        }
    ]
)
export class AppComponent {}