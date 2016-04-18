import {Component, Input,Output, OnInit,EventEmitter} from 'angular2/core';
import {NewsItem} from '../../news-item';
@Component({
    selector: 'vst-news-list',
    templateUrl: 'app/news/news-manager/news-list/news-list.component.html'
})

export class NewsListComponent implements OnInit {

    ngOnInit() {
        if (this.newsItems) {
            this.selectedItem = this.newsItems[0];
        }
    }
    @Input()
    newsItems: NewsItem[];

    @Input()
    selectedItem: NewsItem;
    
    @Output()
    selectedItemChange = new EventEmitter<NewsItem>();
    
    onSelectNewsItem(newsItem:NewsItem){
        this.selectedItemChange.emit(newsItem);
    }
}