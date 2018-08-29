import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NewsItemService } from '../news-item.service';

@Component({
  selector: 'vst-news-item-detail-view',
  templateUrl: './news-item-detail-view.component.html',
  styleUrls: ['./news-item-detail-view.component.css']
})
export class NewsItemDetailViewComponent implements OnInit, OnDestroy {

  constructor(private route: ActivatedRoute, private newsItemService: NewsItemService) { }

  private sub: any;

  id: string;

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => 
      this.setNewsItemId(params['newsItemId'])
   );
  }

  private setNewsItemId(id: string){
  this.id = id;
  }

  ngOnDestroy(){
    this.sub.unsubscribe();
  }

}
