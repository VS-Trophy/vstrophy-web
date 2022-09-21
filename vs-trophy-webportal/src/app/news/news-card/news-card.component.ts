import { Component, OnInit, Input, Directive as  } from '@angular/core';
import { NewsItem } from '../news-item';
import { Router } from '@angular/router';

@()
@Directive()
@Component({
  selector: 'vst-news-card',
  templateUrl: './news-card.component.html',
  styleUrls: ['./news-card.component.css']
})
export class NewsCardComponent implements OnInit {

  @Input()
  newsItem: NewsItem;
  
  constructor(private router: Router) { }

  ngOnInit() {
  }

  readMoreClicked(){
      this.router.navigate(['news/' + this.newsItem._key]);
  }

}
