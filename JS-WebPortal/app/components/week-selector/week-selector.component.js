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
var common_1 = require('@angular/common');
var ng2_bootstrap_1 = require('ng2-bootstrap/ng2-bootstrap');
var forms_1 = require('@angular/forms');
var WeekSelectorComponent = (function () {
    function WeekSelectorComponent() {
        this.weekId = new core_1.EventEmitter();
    }
    Object.defineProperty(WeekSelectorComponent.prototype, "weekModel", {
        set: function (id) {
            this.weekId.emit(id);
        },
        enumerable: true,
        configurable: true
    });
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Array)
    ], WeekSelectorComponent.prototype, "weekList", void 0);
    __decorate([
        core_1.Output(), 
        __metadata('design:type', Object)
    ], WeekSelectorComponent.prototype, "weekId", void 0);
    WeekSelectorComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-week-selector',
            templateUrl: 'week-selector.component.html',
            styleUrls: ['week-selector.component.css'],
            directives: [ng2_bootstrap_1.BUTTON_DIRECTIVES, common_1.CORE_DIRECTIVES, forms_1.FORM_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [])
    ], WeekSelectorComponent);
    return WeekSelectorComponent;
}());
exports.WeekSelectorComponent = WeekSelectorComponent;
//# sourceMappingURL=week-selector.component.js.map