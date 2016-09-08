import {Component} from '@angular/core';
import {OnInit} from '@angular/core';
import {NewsService} from '../../services/news/news.service';
import {NewsItem} from '../../model/news-item/news-item';

@Component({
    moduleId: module.id,
    selector: 'vst-news',
    templateUrl: 'news-view.component.html',
    styleUrls: ['news-view.component.css'],
})
export class NewsViewComponent implements OnInit {
    private _newsItems: NewsItem[];
    constructor(private _newsService: NewsService) { }
    ngOnInit() {
        this._newsService.getNewsItems()
    .then(newsItems => this._newsItems = newsItems);    
    }

}