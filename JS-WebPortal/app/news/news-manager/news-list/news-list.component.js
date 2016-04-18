System.register(['angular2/core', '../../news-item'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, news_item_1;
    var NewsListComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (news_item_1_1) {
                news_item_1 = news_item_1_1;
            }],
        execute: function() {
            NewsListComponent = (function () {
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
                        selector: 'vst-news-list',
                        templateUrl: 'app/news/news-manager/news-list/news-list.component.html'
                    }), 
                    __metadata('design:paramtypes', [])
                ], NewsListComponent);
                return NewsListComponent;
            }());
            exports_1("NewsListComponent", NewsListComponent);
        }
    }
});
//# sourceMappingURL=news-list.component.js.map