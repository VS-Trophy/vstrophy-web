import {Component} from 'angular2/core';
import {ROUTER_DIRECTIVES } from 'angular2/router';



@Component({
    selector: 'vst-navigation',
    templateUrl: 'app/navigation/navigation.html',
    styleUrls: ['app/navigation/navigation.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class NavigationComponent {
}