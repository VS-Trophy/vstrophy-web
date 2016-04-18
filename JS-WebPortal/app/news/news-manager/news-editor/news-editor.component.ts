import {Component, Input} from 'angular2/core';
import {NewsItem} from '../../news-item';

@Component({
    selector: 'vst-news-editor',
    templateUrl: 'app/news/news-manager/news-editor/news-editor.component.html'
})

export class NewsEditorComponent {
    private _newsItem: NewsItem;
    private _editor: QuillStatic;


    @Input()
    set newsItem(newsItem: NewsItem) {
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

    }

    get newsItem() {
        return this._newsItem;
    }

    onSaveNewsItem() {

    }
}