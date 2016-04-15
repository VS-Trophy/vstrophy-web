import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {NewsService} from '../news/news.service';
import {NewsItem} from '../news/news-item';
import {NgClass} from 'angular2/common';
import * as quill from 'Quill';


@Component({
    selector: 'vst-news-editor',
    styles: ['fullHeight'],
    templateUrl: 'app/news_editor/news_editor.component.html'
})
export class NewsEditorComponent {

    private _newsItems: NewsItem[];
    private _editor: QuillStatic;

    selectedItem: NewsItem;
    message: String;

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
        this.initializeQuillEditor();
    }

    updateSelectedNewsItem(newsItem: NewsItem) {
        this.selectedItem = newsItem;
        if (this._newsItems.find(newsItem => newsItem.id == this.selectedItem.id) == undefined) {
            this._newsItems.push(this.selectedItem);
        }
        this._editor.setHTML(""); //necessary because otherwhise quill is taking forever to load!
        this._editor.setHTML(newsItem.text);
    }

    onSaveNewsItem() {
        this.selectedItem.text = this._editor.getHTML();
        this._newsService.saveNewsItem(this.selectedItem).subscribe(
            newsItem => {this.updateSelectedNewsItem(newsItem);this.message = "Gespeichert!"});
            
    }

    private initializeQuillEditor() {
        var configs = {
            theme: 'snow'
        };
        this._editor = new Quill('#editor', configs);
        this._editor.addModule('toolbar', {
            container: '#toolbar'
        });
    }

}