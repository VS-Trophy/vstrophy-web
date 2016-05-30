System.register(['angular2/core', '../../services/teams/teams.service', '../../model/team/team', 'angular2/router'], function(exports_1, context_1) {
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
    var core_1, teams_service_1, team_1, router_1;
    var TeamDetailComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (teams_service_1_1) {
                teams_service_1 = teams_service_1_1;
            },
            function (team_1_1) {
                team_1 = team_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }],
        execute: function() {
            TeamDetailComponent = (function () {
                function TeamDetailComponent(_teamsService, _routeParams) {
                    this._teamsService = _teamsService;
                    this._routeParams = _routeParams;
                }
                TeamDetailComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this._teamsService.getTeam(+this._routeParams.get('id'))
                        .subscribe(function (t) { _this._team = new team_1.Team(t); });
                };
                TeamDetailComponent = __decorate([
                    core_1.Component({
                        selector: 'vst-team-detail',
                        templateUrl: 'app/components/team-detail/team-detail.component.html',
                        styleUrls: ['app/components/team-detail/team-detail.component.css'],
                        directives: [router_1.ROUTER_DIRECTIVES]
                    }), 
                    __metadata('design:paramtypes', [teams_service_1.TeamsService, router_1.RouteParams])
                ], TeamDetailComponent);
                return TeamDetailComponent;
            }());
            exports_1("TeamDetailComponent", TeamDetailComponent);
        }
    }
});
//# sourceMappingURL=team-detail.component.js.map