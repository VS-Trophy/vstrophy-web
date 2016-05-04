import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {NewsService} from '../../service/news.service';
import {NewsItem} from '../../model/news-item';
import {RouteParams,ROUTER_DIRECTIVES } from 'angular2/router';

@Component({
    selector: 'vst-news-item',
    templateUrl: 'app/news/component/news-item/news-item.component.html',
    styleUrls: ['app/news/component/news-item/news-item.component.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class NewsItemComponent implements OnInit {
    private _newsItem: NewsItem;
    constructor(private _newsService: NewsService, private _routeParams:RouteParams) { }
    ngOnInit() {
        console.log(+this._routeParams.get('id'));
        this._newsService.getNewsItem(+this._routeParams.get('id'))
    .subscribe(newsItem => this._newsItem = newsItem);    
    }

}