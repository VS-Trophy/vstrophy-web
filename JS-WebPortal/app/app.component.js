System.register(['angular2/core', './navigation/navigation.component', './news/component/news-view/news-view.component', './news/component/news-item/news-item.component', './news/component/news-manager/news-manager.component', './news/service/news.service', './teams/service/teams.service', './results/results.component', './history/history.component', './teams/component/teams-view/teams-view.component', './configuration/configuration', 'angular2/router', 'angular2/http'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, navigation_component_1, news_view_component_1, news_item_component_1, news_manager_component_1, news_service_1, teams_service_1, results_component_1, history_component_1, teams_view_component_1, configuration_1, router_1, http_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (navigation_component_1_1) {
                navigation_component_1 = navigation_component_1_1;
            },
            function (news_view_component_1_1) {
                news_view_component_1 = news_view_component_1_1;
            },
            function (news_item_component_1_1) {
                news_item_component_1 = news_item_component_1_1;
            },
            function (news_manager_component_1_1) {
                news_manager_component_1 = news_manager_component_1_1;
            },
            function (news_service_1_1) {
                news_service_1 = news_service_1_1;
            },
            function (teams_service_1_1) {
                teams_service_1 = teams_service_1_1;
            },
            function (results_component_1_1) {
                results_component_1 = results_component_1_1;
            },
            function (history_component_1_1) {
                history_component_1 = history_component_1_1;
            },
            function (teams_view_component_1_1) {
                teams_view_component_1 = teams_view_component_1_1;
            },
            function (configuration_1_1) {
                configuration_1 = configuration_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent() {
                }
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        directives: [navigation_component_1.NavigationComponent, router_1.ROUTER_DIRECTIVES],
                        providers: [news_service_1.NewsService, teams_service_1.TeamsService, configuration_1.Configuration, router_1.ROUTER_PROVIDERS, http_1.HTTP_PROVIDERS],
                        templateUrl: 'app/app.component.html'
                    }),
                    router_1.RouteConfig([
                        {
                            path: '/news',
                            name: 'News',
                            component: news_view_component_1.NewsViewComponent,
                            useAsDefault: true
                        },
                        {
                            path: '/news/:id',
                            name: 'NewsItem',
                            component: news_item_component_1.NewsItemComponent,
                        },
                        {
                            path: '/newsEditor',
                            name: 'News Editor',
                            component: news_manager_component_1.NewsManagerComponent,
                        },
                        {
                            path: '/teams',
                            name: 'Teams',
                            component: teams_view_component_1.TeamsViewComponent,
                        },
                        {
                            path: '/results',
                            name: 'Results',
                            component: results_component_1.ResultsComponent,
                        },
                        {
                            path: '/history',
                            name: 'History',
                            component: history_component_1.HistoryComponent,
                        },
                    ]), 
                    __metadata('design:paramtypes', [])
                ], AppComponent);
                return AppComponent;
            }());
            exports_1("AppComponent", AppComponent);
        }
    }
});
//# sourceMappingURL=app.component.js.map