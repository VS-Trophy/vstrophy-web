import {Component} from '@angular/core';

import {NewsService} from './services/news/news.service';
import {TeamsService} from './services/teams/teams.service';
import {MatchesService} from './services/matches/matches.service';
import {NavigationComponent} from './components/navigation/navigation.component';
import {Configuration} from './configuration/configuration'
import { ROUTER_DIRECTIVES } from '@angular/router';
import {HTTP_PROVIDERS}    from '@angular/http';


@Component({
    selector: 'my-app',
    directives: [NavigationComponent],
    providers: [NewsService,TeamsService,MatchesService, Configuration,HTTP_PROVIDERS],
    templateUrl: 'app/app.component.html'
})



export class AppComponent { }