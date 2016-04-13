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

    constructor(private _newsService: NewsService) { }

    ngOnInit() {
        
        this._newsService.getNewsItems()
            .subscribe(newsItems => this._newsItems = newsItems);
    }

    onSelectNewsItem(newsItem: NewsItem) {
        this._newsService.getNewsItem(newsItem.id)
            .subscribe(newsItem => this.updateSelectedNewsItem(newsItem));
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
            newsItem => this.updateSelectedNewsItem(newsItem));
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