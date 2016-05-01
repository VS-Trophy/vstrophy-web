System.register(['angular2/core', '../../service/teams.service', '../../model/Team'], function(exports_1, context_1) {
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
    var core_1, teams_service_1, Team_1;
    var TeamsViewComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (teams_service_1_1) {
                teams_service_1 = teams_service_1_1;
            },
            function (Team_1_1) {
                Team_1 = Team_1_1;
            }],
        execute: function() {
            TeamsViewComponent = (function () {
                function TeamsViewComponent(_teamsService) {
                    this._teamsService = _teamsService;
                }
                TeamsViewComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this._teamsService.getTeams()
                        .map(function (array) { return array.map(function (t) { return new Team_1.Team(t); }); })
                        .subscribe(function (teams) { _this._teams = teams; });
                };
                TeamsViewComponent = __decorate([
                    core_1.Component({
                        selector: 'vst-teams',
                        templateUrl: 'app/teams/component/teams-view/teams-view.component.html',
                        styleUrls: ['app/teams/component/teams-view/teams-view.component.css']
                    }), 
                    __metadata('design:paramtypes', [teams_service_1.TeamsService])
                ], TeamsViewComponent);
                return TeamsViewComponent;
            }());
            exports_1("TeamsViewComponent", TeamsViewComponent);
        }
    }
});
//# sourceMappingURL=teams-view.component.js.map