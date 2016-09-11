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
var NewsEditorComponent = (function () {
    function NewsEditorComponent() {
        this.saveNewsItem = new core_1.EventEmitter();
        this.cancel = new core_1.EventEmitter();
    }
    Object.defineProperty(NewsEditorComponent.prototype, "newsItem", {
        get: function () {
            return this._newsItem;
        },
        set: function (newsItem) {
            this._newsItem = newsItem;
        },
        enumerable: true,
        configurable: true
    });
    NewsEditorComponent.prototype.onSaveNewsItem = function () {
        this.saveNewsItem.emit(this._newsItem);
    };
    NewsEditorComponent.prototype.onCancel = function () {
        this.cancel.emit(null);
    };
    __decorate([
        core_1.Output(), 
        __metadata('design:type', Object)
    ], NewsEditorComponent.prototype, "saveNewsItem", void 0);
    __decorate([
        core_1.Output(), 
        __metadata('design:type', Object)
    ], NewsEditorComponent.prototype, "cancel", void 0);
    __decorate([
        core_1.Input(), 
        __metadata('design:type', news_item_1.NewsItem), 
        __metadata('design:paramtypes', [news_item_1.NewsItem])
    ], NewsEditorComponent.prototype, "newsItem", null);
    NewsEditorComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-news-editor',
            templateUrl: 'news-editor.component.html',
            styleUrls: ['news-editor.component.css']
        }), 
        __metadata('design:paramtypes', [])
    ], NewsEditorComponent);
    return NewsEditorComponent;
}());
exports.NewsEditorComponent = NewsEditorComponent;
//# sourceMappingURL=news-editor.component.js.map