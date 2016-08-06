"use strict";
var NewsItem = (function () {
    function NewsItem() {
    }
    NewsItem.prototype.getShortText = function () {
        return this.text.slice(0, 100);
    };
    return NewsItem;
}());
exports.NewsItem = NewsItem;
//# sourceMappingURL=news-item.js.map