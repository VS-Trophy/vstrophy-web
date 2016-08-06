"use strict";
var router_1 = require('@angular/router');
//Components to route to
var results_view_component_1 = require('./components/results-view/results-view.component');
var history_view_component_1 = require('./components/history-view/history-view.component');
var teams_view_component_1 = require('./components/teams-view/teams-view.component');
var team_detail_component_1 = require('./components/team-detail/team-detail.component');
var news_view_component_1 = require('./components/news-view/news-view.component');
var news_item_component_1 = require('./components/news-item/news-item.component');
var news_manager_component_1 = require('./components/news-manager/news-manager.component');
var routes = [
    {
        path: 'news',
        component: news_view_component_1.NewsViewComponent
    },
    {
        path: 'news/:id',
        component: news_item_component_1.NewsItemComponent
    },
    {
        path: 'teams/:id',
        component: team_detail_component_1.TeamDetailComponent
    },
    {
        path: 'newsEditor',
        component: news_manager_component_1.NewsManagerComponent
    },
    {
        path: 'teams',
        component: teams_view_component_1.TeamsViewComponent
    },
    {
        path: 'results',
        component: results_view_component_1.ResultsViewComponent
    },
    {
        path: 'history',
        component: history_view_component_1.HistoryViewComponent
    },
    {
        path: '**',
        component: news_view_component_1.NewsViewComponent
    },
];
exports.appRouterProviders = [
    router_1.provideRouter(routes)
];
//# sourceMappingURL=app.routes.js.map