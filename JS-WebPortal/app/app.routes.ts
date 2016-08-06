import { provideRouter, RouterConfig } from '@angular/router';

//Components to route to
import {ResultsViewComponent} from './components/results-view/results-view.component'
import {HistoryViewComponent} from './components/history-view/history-view.component'
import {TeamsViewComponent} from './components/teams-view/teams-view.component'
import {TeamDetailComponent} from './components/team-detail/team-detail.component'

import {NewsViewComponent} from './components/news-view/news-view.component';
import {NewsItemComponent} from './components/news-item/news-item.component';
import {NewsManagerComponent} from './components/news-manager/news-manager.component';
const routes: RouterConfig = [
    {
        path: '**',
        component: NewsViewComponent
    },
    {
        path: 'news',
        component: NewsViewComponent
    },
    {
        path: 'news/:id',
        component: NewsItemComponent
    },
    {
        path: 'teams/:id',
        component: TeamDetailComponent
    },
    {
        path: 'newsEditor',
        component: NewsManagerComponent
    },
    {
        path: 'teams',
        component: TeamsViewComponent
    },
    {
        path: 'results',
        component: ResultsViewComponent
    },
    {
        path: 'history',
        component: HistoryViewComponent
    }
];

export const appRouterProviders = [
    provideRouter(routes)
];