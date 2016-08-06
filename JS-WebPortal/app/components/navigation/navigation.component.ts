import {Component} from '@angular/core';
import {ROUTER_DIRECTIVES } from '@angular/router';



@Component({
    selector: 'vst-navigation',
    templateUrl: 'app/components/navigation/navigation.html',
    styleUrls: ['app/components/navigation/navigation.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class NavigationComponent {
}