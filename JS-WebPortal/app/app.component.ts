import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';
import {NewsComponent} from './news/news.component';
import {NewsService} from './news/news.service';

@Component({
    selector: 'my-app',
    directives: [NavigationComponent, NewsComponent],
    providers: [NewsService],
    template:  '<div><vst-navigation></vst-navigation></div><div><vst-news></vst-news></div>'
})
export class AppComponent { }