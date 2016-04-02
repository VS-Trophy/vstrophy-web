import {Component} from 'angular2/core';
import {NavigationComponent} from './navigation/navigation.component';

@Component({
    selector: 'my-app',
    directives: [NavigationComponent],
    template:  '<vst-navigation></vst-navigation>'
})
export class AppComponent { }