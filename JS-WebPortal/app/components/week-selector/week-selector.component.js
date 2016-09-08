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
var WeekSelectorComponent = (function () {
    function WeekSelectorComponent() {
        this.week = new core_1.EventEmitter();
        this.season = new core_1.EventEmitter();
    }
    Object.defineProperty(WeekSelectorComponent.prototype, "seasonList", {
        set: function (seasonArray) {
            if (seasonArray != undefined) {
                this._seasonList = seasonArray;
                this.onSeasonSelect(this._seasonList[this._seasonList.length - 1]);
            }
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(WeekSelectorComponent.prototype, "_weekModel", {
        set: function (weekNumber) {
            console.log("Week clicked");
            this.week.emit(weekNumber);
        },
        enumerable: true,
        configurable: true
    });
    WeekSelectorComponent.prototype.onSeasonSelect = function (season) {
        this._seasonModel = season;
        this.season.emit(season);
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Array), 
        __metadata('design:paramtypes', [Array])
    ], WeekSelectorComponent.prototype, "seasonList", null);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Array)
    ], WeekSelectorComponent.prototype, "weekList", void 0);
    __decorate([
        core_1.Output(), 
        __metadata('design:type', Object)
    ], WeekSelectorComponent.prototype, "week", void 0);
    __decorate([
        core_1.Output(), 
        __metadata('design:type', Object)
    ], WeekSelectorComponent.prototype, "season", void 0);
    WeekSelectorComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-week-selector',
            templateUrl: 'week-selector.component.html',
            styleUrls: ['week-selector.component.css'],
        }), 
        __metadata('design:paramtypes', [])
    ], WeekSelectorComponent);
    return WeekSelectorComponent;
}());
exports.WeekSelectorComponent = WeekSelectorComponent;
//# sourceMappingURL=week-selector.component.js.map