import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {NewsService} from '../../services/news/news.service';
import {NewsItem} from '../../model/news-item/news-item';
import {ROUTER_DIRECTIVES } from '@angular/router';

@Component({
    moduleId: module.id,
    selector: 'vst-news',
    templateUrl: 'news-view.component.html',
    styleUrls: ['news-view.component.css'],
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