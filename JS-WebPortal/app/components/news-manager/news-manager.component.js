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
var news_item_1 = require('../../model/news-item/news-item');
var NewsManagerComponent = (function () {
    function NewsManagerComponent(_newsService) {
        this._newsService = _newsService;
        this._showMenu = true;
        this._showList = false;
    }
    NewsManagerComponent.prototype.ngOnInit = function () {
        this.updateNewsItems();
    };
    NewsManagerComponent.prototype.openNewItemInEditor = function () {
        this._showMenu = false;
        var newsItem = this.createNewsItem();
        this.selectedItem = newsItem;
    };
    NewsManagerComponent.prototype.updateNewsItems = function () {
        var _this = this;
        this._newsService.getNewsItems()
            .then(function (newsItems) { _this.setupNewsItemList(newsItems); _this.selectedItem = _this._newsItems[0]; });
    };
    NewsManagerComponent.prototype.onCancel = function () {
        this._showList = false;
        this._showMenu = true;
    };
    NewsManagerComponent.prototype.showExistingItems = function () {
        this._showMenu = false;
        this._showList = true;
        this.selectedItem = this._newsItems[0];
    };
    NewsManagerComponent.prototype.setupNewsItemList = function (databaseList) {
        this._newsItems = databaseList;
        console.log(this._newsItems.length);
    };
    NewsManagerComponent.prototype.createNewsItem = function () {
        var item = new news_item_1.NewsItem();
        item.author = "Autor";
        item.publicationDate = new Date();
        item.title = "Neuer Eintrag";
        item.text = "Neuer Text";
        return item;
    };
    NewsManagerComponent.prototype.onSelectNewsItem = function (newsItem) {
        var _this = this;
        if (newsItem.id) {
            //update the selected news item from 
            //the database in case something has changed
            this._newsService.getNewsItem(newsItem.id)
                .then(function (newsItem) { return _this.updateSelectedNewsItem(newsItem); });
        }
        else {
            this.updateSelectedNewsItem(newsItem);
        }
    };
    NewsManagerComponent.prototype.onSaveNewsItem = function (newsItem) {
        var _this = this;
        console.log("Saving " + newsItem);
        this._newsService.saveNewsItem(newsItem).then(function (o) {
            _this.updateNewsItems();
            _this.showExistingItems();
        });
    };
    NewsManagerComponent.prototype.updateSelectedNewsItem = function (newsItem) {
        var _this = this;
        this.selectedItem = newsItem;
        if (this._newsItems.find(function (newsItem) { return newsItem.id == _this.selectedItem.id; }) == undefined) {
            this._newsItems.push(this.selectedItem);
        }
    };
    NewsManagerComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vst-news-manager',
            templateUrl: 'news-manager.component.html',
            styleUrls: ['news-manager.component.css']
        }), 
        __metadata('design:paramtypes', [news_service_1.NewsService])
    ], NewsManagerComponent);
    return NewsManagerComponent;
}());
exports.NewsManagerComponent = NewsManagerComponent;
//# sourceMappingURL=news-manager.component.js.map