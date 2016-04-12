System.register(['angular2/core', '../news/news.service', '../news/news-item'], function(exports_1, context_1) {
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
    var core_1, news_service_1, news_item_1;
    var NewsEditorComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (news_service_1_1) {
                news_service_1 = news_service_1_1;
            },
            function (news_item_1_1) {
                news_item_1 = news_item_1_1;
            }],
        execute: function() {
            NewsEditorComponent = (function () {
                function NewsEditorComponent(_newsService) {
                    this._newsService = _newsService;
                }
                NewsEditorComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this.initializeQuillEditor();
                    this._newsService.getNewsItems()
                        .subscribe(function (newsItems) { return _this._newsItems = newsItems; });
                    this.selectedItem = new news_item_1.NewsItem();
                };
                NewsEditorComponent.prototype.onSelectNewsItem = function (newsItem) {
                    var _this = this;
                    this._newsService.getNewsItem(newsItem.id)
                        .subscribe(function (newsItem) { return _this.updateSelectedNewsItem(newsItem); });
                };
                NewsEditorComponent.prototype.updateSelectedNewsItem = function (newsItem) {
                    this.selectedItem = newsItem;
                    this._editor.setHTML(""); //necessary because otherwhise quill is taking forever to load!
                    this._editor.setHTML(newsItem.text);
                };
                NewsEditorComponent.prototype.onSaveNewsItem = function () {
                    this.selectedItem.text = this._editor.getHTML();
                    this._newsService.saveNewsItem(this.selectedItem).subscribe(function (value) { return console.log(value); });
                };
                NewsEditorComponent.prototype.initializeQuillEditor = function () {
                    var configs = {
                        theme: 'snow'
                    };
                    this._editor = new Quill('#editor', configs);
                    this._editor.addModule('toolbar', {
                        container: '#toolbar'
                    });
                };
                NewsEditorComponent = __decorate([
                    core_1.Component({
                        selector: 'vst-news-editor',
                        styles: ['fullHeight'],
                        templateUrl: 'app/news_editor/news_editor.component.html'
                    }), 
                    __metadata('design:paramtypes', [news_service_1.NewsService])
                ], NewsEditorComponent);
                return NewsEditorComponent;
            }());
            exports_1("NewsEditorComponent", NewsEditorComponent);
        }
    }
});
//# sourceMappingURL=news_editor.component.js.map