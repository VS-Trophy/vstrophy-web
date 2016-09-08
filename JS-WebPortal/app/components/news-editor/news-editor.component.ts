import {Component, Input,Output,EventEmitter} from '@angular/core';
import {NewsItem} from '../../model/news-item/news-item';

@Component({
    moduleId: module.id,
    selector: 'vst-news-editor',
    templateUrl: 'news-editor.component.html',
    styleUrls: ['news-editor.component.css']
})

export class NewsEditorComponent {
    private _newsItem: NewsItem;
    private _editor: QuillStatic;

    @Output()
    saveNewsItem = new EventEmitter<NewsItem>();
    
    @Output()
    cancel = new EventEmitter<void>();
    
    @Input()
    set newsItem(newsItem: NewsItem) {
        if (!this._editor) {
            var configs = {
                theme: 'snow',
                modules:{
                    toolbar:{
                        container: '#toolbar',
                    }
                }
            };
            this._editor = new Quill('#editor', configs);
          
        }
        this._newsItem = newsItem;
        this._editor.setContents("");
        this._editor.pasteHTML(newsItem.text);

    }

    get newsItem() {
        return this._newsItem;
    }

    onSaveNewsItem() {
        this._newsItem.text = this._editor.getHTML();
        this.saveNewsItem.emit(this._newsItem);
    }
    
    onCancel(){
        this.cancel.emit(null);
    }
}