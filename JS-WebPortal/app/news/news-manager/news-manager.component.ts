import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {NewsService} from '../news.service';
import {NewsItem} from '../news-item';
import {NewsEditorComponent} from './news-editor/news-editor.component';
import {NewsListComponent} from './news-list/news-list.component';
import {NgClass} from 'angular2/common';


@Component({
    selector: 'vst-news-manager',
    directives: [NewsEditorComponent, NewsListComponent],
    templateUrl: 'app/news/news-manager/news-manager.component.html'
})
export class NewsManagerComponent {

    private _newsItems: NewsItem[];

    selectedItem: NewsItem;

    constructor(private _newsService: NewsService) { }

    ngOnInit() {
        this._newsService.getNewsItems()
            .subscribe(newsItems => { this.setupNewsItemList(newsItems) });
    }

    setupNewsItemList(databaseList: NewsItem[]) {
        var newItem = this.createNewsItem();
        this._newsItems = [];
        this._newsItems.push(newItem);
        this.selectedItem = newItem;
        this._newsItems = this._newsItems.concat(databaseList);

    }

    createNewsItem(): NewsItem {
        var item = new NewsItem();
        item.author = "Autor";
        item.publicationDate = new Date();
        item.title = "Neuer Eintrag";
        item.text = "Neuer Text";
        return item;
    }

    onSelectNewsItem(newsItem: NewsItem) {
        if (newsItem.id) {
            //update the selected news item from 
            //the database in case something has changed
            this._newsService.getNewsItem(newsItem.id)
                .subscribe(newsItem => this.updateSelectedNewsItem(newsItem));
        } else {
            this.updateSelectedNewsItem(newsItem);
        }
    }
    
    onSaveNewsItem(newsItem:NewsItem){
        console.log("Saving " + newsItem);
        this._newsService.saveNewsItem(newsItem).subscribe(value => console.log(value));

    }

    updateSelectedNewsItem(newsItem: NewsItem) {
        this.selectedItem = newsItem;
        if (this._newsItems.find(newsItem => newsItem.id == this.selectedItem.id) == undefined) {
            this._newsItems.push(this.selectedItem);
        }
    }





}