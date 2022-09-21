import { Component, OnInit, OnDestroy, Directive as  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NewsItemService } from '../news-item.service';
import { NewsItem } from '../news-item';

@()
@Directive()
@Component({
  selector: 'vst-news-item-detail-view',
  templateUrl: './news-item-detail-view.component.html',
  styleUrls: ['./news-item-detail-view.component.css']
})
export class NewsItemDetailViewComponent implements OnInit, OnDestroy {

  constructor(private route: ActivatedRoute, private newsItemService: NewsItemService) { }

  private sub: any;

  newsItem: NewsItem;

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => 
      this.setNewsItemId(params['newsItemId'])
   );
  }

  private setNewsItemId(id: string){
    this.newsItemService.getNewsItem(id).subscribe(newsItem => this.newsItem = newsItem)
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

}
