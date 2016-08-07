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
var http_1 = require('@angular/http');
var Observable_1 = require('rxjs/Observable');
var team_1 = require('../../model/team/team');
var configuration_1 = require('../../configuration/configuration');
require('rxjs/Rx');
require('rxjs/add/operator/toPromise');
var TeamsService = (function () {
    function TeamsService(http, conf) {
        this.http = http;
        this.conf = conf;
        this._teamCache = new Map();
    }
    TeamsService.prototype.getTeams = function () {
        var _this = this;
        if (this._teamCache.size != 0) {
            return Promise.resolve(this.getTeamsFromCache());
        }
        return this.http.get(this.conf.teamUrl)
            .toPromise()
            .then(function (res) {
            var array = res.json();
            _this._teamCache.clear();
            array.forEach(function (obj) { return _this.putInCache(obj); });
            return _this.getTeamsFromCache();
        })
            .catch(this.handleError);
    };
    TeamsService.prototype.getTeamsFromCache = function () {
        var teams = new Array();
        this._teamCache.forEach(function (value) { return teams.push(value); });
        return teams;
    };
    TeamsService.prototype.getTeamFromCache = function (id) {
        return this._teamCache.get(id);
    };
    TeamsService.prototype.fillCache = function (teams) {
        var _this = this;
        this._teamCache.clear();
        teams.forEach(function (t) { return _this._teamCache.set(t.id, t); });
    };
    TeamsService.prototype.putInCache = function (teamObj) {
        var team = new team_1.Team(teamObj);
        this._teamCache.set(team.id, team);
    };
    TeamsService.prototype.getTeam = function (id) {
        /*    if (this._teamCache.has(id)) {
               return Promise.resolve(this.getTeamFromCache(id));
            }*/
        return this.http.get(this.conf.teamUrl + "/" + id)
            .toPromise()
            .then(function (res) { return new team_1.Team(res.json()); })
            .catch(this.handleError);
    };
    TeamsService.prototype.handleError = function (error) {
        console.error(error);
        return Observable_1.Observable.throw(error.json().error || 'Server error');
    };
    TeamsService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, configuration_1.Configuration])
    ], TeamsService);
    return TeamsService;
}());
exports.TeamsService = TeamsService;
//# sourceMappingURL=teams.service.js.map