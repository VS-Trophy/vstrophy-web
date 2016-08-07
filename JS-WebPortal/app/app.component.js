"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var news_service_1 = require('./services/news/news.service');
var teams_service_1 = require('./services/teams/teams.service');
var matches_service_1 = require('./services/matches/matches.service');
var navigation_component_1 = require('./components/navigation/navigation.component');
var configuration_1 = require('./configuration/configuration');
var router_1 = require('@angular/router');
var http_1 = require('@angular/http');
//Views
var results_view_component_1 = require('./components/results-view/results-view.component');
var history_view_component_1 = require('./components/history-view/history-view.component');
var teams_view_component_1 = require('./components/teams-view/teams-view.component');
var team_detail_component_1 = require('./components/team-detail/team-detail.component');
var news_view_component_1 = require('./components/news-view/news-view.component');
var news_item_component_1 = require('./components/news-item/news-item.component');
var news_manager_component_1 = require('./components/news-manager/news-manager.component');
var AppComponent = (function () {
    function AppComponent() {
    }
    AppComponent = __decorate([
        core_1.Component({
            selector: 'my-app',
            directives: [navigation_component_1.NavigationComponent, router_1.ROUTER_DIRECTIVES],
            providers: [news_service_1.NewsService, teams_service_1.TeamsService, matches_service_1.MatchesService, configuration_1.Configuration, http_1.HTTP_PROVIDERS],
            templateUrl: 'app/app.component.html',
            precompile: [results_view_component_1.ResultsViewComponent, history_view_component_1.HistoryViewComponent, teams_view_component_1.TeamsViewComponent, team_detail_component_1.TeamDetailComponent, news_view_component_1.NewsViewComponent, news_item_component_1.NewsItemComponent, news_manager_component_1.NewsManagerComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppComponent);
    return AppComponent;
}());
exports.AppComponent = AppComponent;
//# sourceMappingURL=app.component.js.map