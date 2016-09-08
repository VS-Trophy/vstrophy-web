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
var platform_browser_1 = require('@angular/platform-browser');
var http_1 = require('@angular/http');
var router_1 = require('@angular/router');
var ng2_bootstrap_1 = require('ng2-bootstrap/ng2-bootstrap');
var app_component_1 = require('./app.component');
var navigation_component_1 = require('./components/navigation/navigation.component');
var app_routes_1 = require('./app.routes');
var results_view_component_1 = require('./components/results-view/results-view.component');
var history_view_component_1 = require('./components/history-view/history-view.component');
var teams_view_component_1 = require('./components/teams-view/teams-view.component');
var team_detail_component_1 = require('./components/team-detail/team-detail.component');
var news_view_component_1 = require('./components/news-view/news-view.component');
var news_item_component_1 = require('./components/news-item/news-item.component');
var news_manager_component_1 = require('./components/news-manager/news-manager.component');
var news_list_component_1 = require('./components/news-list/news-list.component');
var news_editor_component_1 = require('./components/news-editor/news-editor.component');
var week_selector_component_1 = require('./components/week-selector/week-selector.component');
var match_component_1 = require('./components/match/match.component');
var forms_1 = require('@angular/forms');
//TODO: SPLIT THIS UP!!!!!
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [
                platform_browser_1.BrowserModule,
                // Router
                app_routes_1.routing,
                http_1.HttpModule,
                router_1.RouterModule,
                forms_1.FormsModule,
                ng2_bootstrap_1.ButtonsModule
            ],
            providers: [app_routes_1.appRoutingProviders],
            declarations: [
                app_component_1.AppComponent,
                news_item_component_1.NewsItemComponent,
                navigation_component_1.NavigationComponent,
                news_view_component_1.NewsViewComponent,
                news_manager_component_1.NewsManagerComponent,
                history_view_component_1.HistoryViewComponent,
                team_detail_component_1.TeamDetailComponent,
                news_list_component_1.NewsListComponent,
                news_editor_component_1.NewsEditorComponent,
                teams_view_component_1.TeamsViewComponent,
                results_view_component_1.ResultsViewComponent,
                week_selector_component_1.WeekSelectorComponent,
                match_component_1.MatchComponent
            ],
            bootstrap: [app_component_1.AppComponent]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map