import {Component, Input,Output, OnInit,EventEmitter} from '@angular/core';
import {NewsItem} from '../../model/news-item/news-item';
@Component({
    selector: 'vst-news-list',
    templateUrl: 'news-list.component.html',
    styles: ['news-list.component.css']
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