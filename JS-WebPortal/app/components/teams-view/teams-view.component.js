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
var teams_service_1 = require('../../services/teams/teams.service');
var team_1 = require('../../model/team/team');
var router_1 = require('@angular/router');
var TeamsViewComponent = (function () {
    function TeamsViewComponent(_teamsService) {
        this._teamsService = _teamsService;
    }
    TeamsViewComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._teamsService.getTeams()
            .map(function (array) { return array.map(function (t) { return new team_1.Team(t); }); })
            .subscribe(function (teams) { _this._teams = teams; });
    };
    TeamsViewComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-teams',
            templateUrl: 'teams-view.component.html',
            styleUrls: ['teams-view.component.css'],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [teams_service_1.TeamsService])
    ], TeamsViewComponent);
    return TeamsViewComponent;
}());
exports.TeamsViewComponent = TeamsViewComponent;
//# sourceMappingURL=teams-view.component.js.map