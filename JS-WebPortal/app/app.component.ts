import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';
import {NewsComponent} from './news/news.component';

@Component({
    selector: 'my-app',
    directives: [NavigationComponent, NewsComponent],
    template:  '<div><vst-navigation></vst-navigation></div><div><vst-news></vst-news></div>'
})
export class AppComponent { }