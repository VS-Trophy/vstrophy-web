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
var Configuration = (function () {
    function Configuration() {
        this.firstSeason = 2012;
        this.lastSeason = 2016;
        this.baseURL = "http://rest.vstrophy.ch/";
        this.newsItemUrl = this.baseURL + "newsitem";
        this.teamUrl = this.baseURL + "team";
        this.matchUrl = this.baseURL + "match";
        this.weekUrl = this.baseURL + "week";
    }
    //We could get this out of the database. But this works as well ;)
    Configuration.prototype.getSeasons = function () {
        var seasons = [];
        for (var i = this.firstSeason; i <= this.lastSeason; i++) {
            seasons.push(i);
        }
        return seasons;
    };
    Configuration = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [])
    ], Configuration);
    return Configuration;
}());
exports.Configuration = Configuration;
//# sourceMappingURL=configuration.js.map