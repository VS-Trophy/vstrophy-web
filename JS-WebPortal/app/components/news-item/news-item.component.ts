import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {NewsService} from '../../services/news/news.service';
import {NewsItem} from '../../model/news-item/news-item';
import {ActivatedRoute,ROUTER_DIRECTIVES } from '@angular/router';

@Component({
    moduleId: module.id,
    selector: 'vst-news-item',
    templateUrl: 'news-item.component.html',
    styleUrls: ['news-item.component.css'],
    directives: [ROUTER_DIRECTIVES]
})
export class NewsItemComponent implements OnInit {
    private _newsItem: NewsItem;
    constructor(private _newsService: NewsService, private route: ActivatedRoute) { }
    ngOnInit() {
                this.route.params.subscribe(params =>
                this._newsService.getNewsItem(+params['id'])
                .then(newsItem => this._newsItem = newsItem) 
                )
    }

}