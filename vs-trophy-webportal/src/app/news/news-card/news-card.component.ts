import { Component, OnInit, Input } from '@angular/core';
import { NewsItem } from '../news-item';

@Component({
  selector: 'vst-news-card',
  templateUrl: './news-card.component.html',
  styleUrls: ['./news-card.component.css']
})
export class NewsCardComponent implements OnInit {

  @Input()
  newsItem: NewsItem;
  
  constructor() { }

  ngOnInit() {
  }

}
