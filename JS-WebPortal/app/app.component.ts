import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';
import {NewsComponent} from './news/news.component';
import {NewsService} from './news/news.service';

@Component({
    selector: 'my-app',
    directives: [NavigationComponent, NewsComponent],
    providers: [NewsService],
    templateUrl: 'app/app.component.html'
})
export class AppComponent { }