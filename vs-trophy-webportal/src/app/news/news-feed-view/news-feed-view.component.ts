import { Component, OnInit, Directive as  } from '@angular/core';
import { NewsItem } from '../news-item';
import { NewsItemService } from '../news-item.service';

@()
@Directive()
@Component({
  selector: 'vst-news-feed-view',
  templateUrl: './news-feed-view.component.html',
  styleUrls: ['./news-feed-view.component.css']
})
export class NewsFeedViewComponent implements OnInit {


  newsItems: NewsItem[] = [];

  constructor(private newsItemService: NewsItemService) { }

  ngOnInit() {
    this.newsItemService.getAllNewsItems().subscribe(items => this.newsItems = items);
  }

}
