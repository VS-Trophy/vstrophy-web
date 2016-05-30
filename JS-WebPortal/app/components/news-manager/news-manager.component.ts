import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {NewsService} from '../../services/news/news.service';
import {NewsItem} from '../../model/news-item/news-item';
import {NewsEditorComponent} from '../news-editor/news-editor.component';
import {NewsListComponent} from '../news-list/news-list.component';
import {NgClass} from 'angular2/common';


@Component({
    selector: 'vst-news-manager',
    directives: [NewsEditorComponent, NewsListComponent],
    templateUrl: 'app/components/news-manager/news-manager.component.html',
    styleUrls: ['app/components/news-manager/news-manager.component.css']
})
export class NewsManagerComponent {

    private _newsItems: NewsItem[];

    selectedItem: NewsItem;

    private _showMenu: boolean;

    private _showList: boolean;

    constructor(private _newsService: NewsService) { this._showMenu = true; this._showList = false; }

    ngOnInit() {
        this.updateNewsItems();
    }

    openNewItemInEditor() {
        this._showMenu = false;
        var newsItem: NewsItem = this.createNewsItem();
        this.selectedItem = newsItem;
    }

    updateNewsItems() {
        this._newsService.getNewsItems()
            .subscribe(newsItems => { this.setupNewsItemList(newsItems);this.selectedItem = this._newsItems[0]; });
    }

    onCancel() {
        this._showList = false;
        this._showMenu = true;
    }

    showExistingItems() {
        this._showMenu = false;
        this._showList = true;
        this.selectedItem = this._newsItems[0];
    }

    setupNewsItemList(databaseList: NewsItem[]) {
        this._newsItems = databaseList;
        console.log(this._newsItems.length);
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

    onSaveNewsItem(newsItem: NewsItem) {
        console.log("Saving " + newsItem);
        this._newsService.saveNewsItem(newsItem).subscribe(o => {
            this.updateNewsItems();
            this.showExistingItems()
        });

    }

    updateSelectedNewsItem(newsItem: NewsItem) {
        this.selectedItem = newsItem;
        if (this._newsItems.find(newsItem => newsItem.id == this.selectedItem.id) == undefined) {
            this._newsItems.push(this.selectedItem);
        }
    }





}