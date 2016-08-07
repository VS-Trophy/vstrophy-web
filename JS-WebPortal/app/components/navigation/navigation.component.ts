import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES } from '@angular/router';



@Component({
    moduleId: module.id,
    selector: 'vst-navigation',
    templateUrl: 'navigation.html',
    styleUrls: ['navigation.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class NavigationComponent {
}