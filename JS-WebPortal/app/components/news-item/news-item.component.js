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
var NewsItemComponent = (function () {
    function NewsItemComponent(_newsService, route) {
        this._newsService = _newsService;
        this.route = route;
    }
    NewsItemComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.subscribe(function (params) {
            return _this._newsService.getNewsItem(+params['id'])
                .then(function (newsItem) { return _this._newsItem = newsItem; });
        });
    };
    NewsItemComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-news-item',
            templateUrl: 'news-item.component.html',
            styleUrls: ['news-item.component.css'],
        }), 
        __metadata('design:paramtypes', [news_service_1.NewsService, router_1.ActivatedRoute])
    ], NewsItemComponent);
    return NewsItemComponent;
}());
exports.NewsItemComponent = NewsItemComponent;
//# sourceMappingURL=news-item.component.js.map