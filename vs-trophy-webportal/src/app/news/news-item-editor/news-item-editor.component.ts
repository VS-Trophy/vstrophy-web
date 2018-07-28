import { Component, OnInit } from '@angular/core';
import { NewsItemService } from '../news-item.service';

@Component({
  selector: 'vst-news-item-editor',
  templateUrl: './news-item-editor.component.html',
  styleUrls: ['./news-item-editor.component.css']
})
export class NewsItemEditorComponent implements OnInit {

  constructor(private newsItemService: NewsItemService) { }

  ngOnInit() {
  }

}
