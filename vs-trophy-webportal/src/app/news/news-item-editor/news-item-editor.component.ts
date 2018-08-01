import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NewsItemService } from '../news-item.service';
import { NewsItem } from '../news-item';


@Component({
  selector: 'vst-news-item-editor',
  templateUrl: './news-item-editor.component.html',
  styleUrls: ['./news-item-editor.component.css']
})
export class NewsItemEditorComponent implements OnInit {

  constructor(private newsItemService: NewsItemService) { }

  @Input()
  newsItem: NewsItem;

  @Output()
  saveEvent: EventEmitter<NewsItem> = new EventEmitter();

  ngOnInit() {
  
  }
  
  onSave(){
    this.saveEvent.emit(this.newsItem);
  }
}
