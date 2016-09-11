import {Component, Input,Output,EventEmitter} from '@angular/core';
import {EditorModule,SharedModule} from 'primeng/primeng';
import {NewsItem} from '../../model/news-item/news-item';

@Component({
    moduleId: module.id,
    selector: 'vst-news-editor',
    templateUrl: 'news-editor.component.html',
    styleUrls: ['news-editor.component.css']
})

export class NewsEditorComponent {
    private _newsItem: NewsItem;

    @Output()
    saveNewsItem = new EventEmitter<NewsItem>();
    
    @Output()
    cancel = new EventEmitter<void>();
    
    @Input()
    set newsItem(newsItem: NewsItem) {
        this._newsItem = newsItem;
    }

    get newsItem() {
        return this._newsItem;
    }

    onSaveNewsItem() {
        this.saveNewsItem.emit(this._newsItem);
    }
    
    onCancel(){
        this.cancel.emit(null);
    }
}