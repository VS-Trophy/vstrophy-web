import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {NewsService} from '../../service/news.service';
import {NewsItem} from '../../model/news-item';
import {ROUTER_DIRECTIVES } from 'angular2/router';

@Component({
    selector: 'vst-news',
    templateUrl: 'app/news/component/news-view/news-view.component.html',
    styleUrls: ['app/news/component/news-view/news-view.component.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class NewsViewComponent implements OnInit {
    private _newsItems: NewsItem[];
    constructor(private _newsService: NewsService) { }
    ngOnInit() {
        this._newsService.getNewsItems()
    .subscribe(newsItems => this._newsItems = newsItems);    
    }

}