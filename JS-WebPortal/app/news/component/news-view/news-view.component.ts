import {Component} from 'angular2/core';
import {OnInit} from 'angular2/core';
import {NewsService} from '../../service/news.service';
import {NewsItem} from '../../model/news-item';


@Component({
    selector: 'vst-news',
    templateUrl: 'app/news/component/news-view/news-view.component.html',
    styleUrls: ['app/news/component/news-view/news-view.component.css']
})
export class NewsViewComponent implements OnInit {
    private _newsItems: NewsItem[];
    constructor(private _newsService: NewsService) { }
    ngOnInit() {
        this._newsService.getNewsItems()
    .subscribe(newsItems => this._newsItems = newsItems);    
    }

}