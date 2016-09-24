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
var configuration_1 = require('../../configuration/configuration');
var WeeksService = (function () {
    function WeeksService(http, conf) {
        this.http = http;
        this.conf = conf;
    }
    WeeksService.prototype.getWeeks = function (season) {
        console.log("Weeks requested");
        return this.http.get(this.conf.weekUrl + '/' + season)
            .toPromise()
            .then(function (res) { return res.json(); })
            .catch(this.handleError);
    };
    WeeksService.prototype.getSeasons = function () {
        return Promise.resolve(this.conf.getSeasons());
    };
    WeeksService.prototype.handleError = function (error) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
    };
    WeeksService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http, configuration_1.Configuration])
    ], WeeksService);
    return WeeksService;
}());
exports.WeeksService = WeeksService;
//# sourceMappingURL=weeks.service.js.map