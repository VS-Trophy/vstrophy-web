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
var news_item_1 = require('../../model/news-item/news-item');
var NewsListComponent = (function () {
    function NewsListComponent() {
        this.selectedItemChange = new core_1.EventEmitter();
    }
    NewsListComponent.prototype.ngOnInit = function () {
        if (this.newsItems) {
            this.selectedItem = this.newsItems[0];
        }
    };
    NewsListComponent.prototype.onSelectNewsItem = function (newsItem) {
        this.selectedItemChange.emit(newsItem);
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', Array)
    ], NewsListComponent.prototype, "newsItems", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', news_item_1.NewsItem)
    ], NewsListComponent.prototype, "selectedItem", void 0);
    __decorate([
        core_1.Output(), 
        __metadata('design:type', Object)
    ], NewsListComponent.prototype, "selectedItemChange", void 0);
    NewsListComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-news-list',
            templateUrl: 'news-list.component.html',
            styles: ['news-list.component.css']
        }), 
        __metadata('design:paramtypes', [])
    ], NewsListComponent);
    return NewsListComponent;
}());
exports.NewsListComponent = NewsListComponent;
//# sourceMappingURL=news-list.component.js.map