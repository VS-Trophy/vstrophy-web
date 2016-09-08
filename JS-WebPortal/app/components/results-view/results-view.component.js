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
var matches_service_1 = require('../../services/matches/matches.service');
var weeks_service_1 = require('../../services/weeks/weeks.service');
var ResultsViewComponent = (function () {
    function ResultsViewComponent(_matchesService, _weekService) {
        this._matchesService = _matchesService;
        this._weekService = _weekService;
        this.radioModel = 'Middle';
        this.checkModel = { left: false, middle: true, right: false };
    }
    ResultsViewComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._weekService.getSeasons().then(function (list) { return _this.seasons = list; });
    };
    ResultsViewComponent.prototype.onWeekSelect = function (weekNumber) {
        var _this = this;
        console.log("ONWeek");
        this._matchesService.getMatches(this._season, weekNumber).then(function (matches) { return _this._matches = matches; });
    };
    ResultsViewComponent.prototype.onSeasonSelect = function (season) {
        var _this = this;
        this._season = season;
        this._weekService.getWeeks(season).then(function (list) { _this.weeks = list; });
    };
    ResultsViewComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-results',
            templateUrl: 'results-view.component.html',
        }), 
        __metadata('design:paramtypes', [matches_service_1.MatchesService, weeks_service_1.WeeksService])
    ], ResultsViewComponent);
    return ResultsViewComponent;
}());
exports.ResultsViewComponent = ResultsViewComponent;
//# sourceMappingURL=results-view.component.js.map