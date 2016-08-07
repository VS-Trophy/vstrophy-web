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
var news_service_1 = require('../../services/news/news.service');
var router_1 = require('@angular/router');
var NewsViewComponent = (function () {
    function NewsViewComponent(_newsService) {
        this._newsService = _newsService;
    }
    NewsViewComponent.prototype.ngOnInit = function () {
        var _this = this;
        this._newsService.getNewsItems()
            .then(function (newsItems) { return _this._newsItems = newsItems; });
    };
    NewsViewComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-news',
            templateUrl: 'news-view.component.html',
            styleUrls: ['news-view.component.css'],
            directives: [router_1.ROUTER_DIRECTIVES]
        }), 
        __metadata('design:paramtypes', [news_service_1.NewsService])
    ], NewsViewComponent);
    return NewsViewComponent;
}());
exports.NewsViewComponent = NewsViewComponent;
//# sourceMappingURL=news-view.component.js.map