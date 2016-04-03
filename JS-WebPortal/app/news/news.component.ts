import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {NewsService} from './news.service';
import {NewsItem} from './news-item';

@Component({
    selector: 'vst-news',
    templateUrl: 'app/news/news.component.html'
})
export class NewsComponent implements OnInit {
    private _newsItems: NewsItem[];
    constructor(private _newsService: NewsService) { }
    ngOnInit() {
        this._newsService.getNewsItems().then(newsItems => this._newsItems = newsItems);
    }

}