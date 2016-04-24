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
    var NewsEditorComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (news_item_1_1) {
                news_item_1 = news_item_1_1;
            }],
        execute: function() {
            NewsEditorComponent = (function () {
                function NewsEditorComponent() {
                    this.saveNewsItem = new core_1.EventEmitter();
                }
                Object.defineProperty(NewsEditorComponent.prototype, "newsItem", {
                    get: function () {
                        return this._newsItem;
                    },
                    set: function (newsItem) {
                        if (!this._editor) {
                            var configs = {
                                theme: 'snow'
                            };
                            this._editor = new Quill('#editor', configs);
                            this._editor.addModule('toolbar', {
                                container: '#toolbar'
                            });
                        }
                        this._newsItem = newsItem;
                        this._editor.setHTML(""); //without this quill takes forever to load
                        this._editor.setHTML(newsItem.text);
                    },
                    enumerable: true,
                    configurable: true
                });
                NewsEditorComponent.prototype.onSaveNewsItem = function () {
                    this._newsItem.text = this._editor.getHTML();
                    this.saveNewsItem.emit(this._newsItem);
                };
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', Object)
                ], NewsEditorComponent.prototype, "saveNewsItem", void 0);
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', news_item_1.NewsItem), 
                    __metadata('design:paramtypes', [news_item_1.NewsItem])
                ], NewsEditorComponent.prototype, "newsItem", null);
                NewsEditorComponent = __decorate([
                    core_1.Component({
                        selector: 'vst-news-editor',
                        templateUrl: 'app/news/news-manager/news-editor/news-editor.component.html'
                    }), 
                    __metadata('design:paramtypes', [])
                ], NewsEditorComponent);
                return NewsEditorComponent;
            }());
            exports_1("NewsEditorComponent", NewsEditorComponent);
        }
    }
});
//# sourceMappingURL=news-editor.component.js.map