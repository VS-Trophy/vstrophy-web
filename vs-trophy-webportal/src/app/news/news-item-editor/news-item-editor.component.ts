import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NewsItemService } from '../news-item.service';
import { NewsItem } from '../news-item';
import { AngularEditorConfig } from '@kolkov/angular-editor';


@Component({
  selector: 'vst-news-item-editor',
  templateUrl: './news-item-editor.component.html',
  styleUrls: ['./news-item-editor.component.css']
})
export class NewsItemEditorComponent implements OnInit {
  editorConfig: AngularEditorConfig = {
    editable: true,
    spellcheck: false,
    height: '25rem',
    minHeight: '5rem',
    translate: 'no',
    uploadUrl: 'v1/images', // if needed
    customClasses: [ // optional
      {
        name: "quote",
        class: "quote",
      },
      {
        name: 'redText',
        class: 'redText'
      },
      {
        name: "titleText",
        class: "titleText",
        tag: "h1",
      },
    ]
  };
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
