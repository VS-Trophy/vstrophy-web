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
var router_1 = require('@angular/router');
var TeamDetailComponent = (function () {
    function TeamDetailComponent(_teamsService, _route) {
        this._teamsService = _teamsService;
        this._route = _route;
    }
    TeamDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._route.params.subscribe(function (params) { return _this._teamsService.getTeam(+params['id'])
            .then(function (t) { _this._team = t; }); });
    };
    TeamDetailComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-team-detail',
            templateUrl: 'team-detail.component.html',
            styleUrls: ['team-detail.component.css'],
        }), 
        __metadata('design:paramtypes', [teams_service_1.TeamsService, router_1.ActivatedRoute])
    ], TeamDetailComponent);
    return TeamDetailComponent;
}());
exports.TeamDetailComponent = TeamDetailComponent;
//# sourceMappingURL=team-detail.component.js.map