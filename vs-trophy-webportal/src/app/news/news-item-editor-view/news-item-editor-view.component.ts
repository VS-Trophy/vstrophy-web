import { Component, OnInit } from '@angular/core';
import { NewsItem } from '../news-item';
import { NewsItemService } from '../news-item.service';

@Component({
  selector: 'vst-news-item-editor-view',
  templateUrl: './news-item-editor-view.component.html',
  styleUrls: ['./news-item-editor-view.component.css']
})
export class NewsItemEditorViewComponent implements OnInit {

  selectedItem: NewsItem;

  items: NewsItem[];

  constructor(private newsItemService: NewsItemService) {
  }

  ngOnInit() {
    this.newsItemService.getAllNewsItems().subscribe(items => {this.items = items;});
  }

  onSelect(selected: NewsItem){
    console.info()
    this.selectedItem = selected;
  }

  onSave(modifiedNewsItem: NewsItem){
    this.newsItemService.updateNewsItem(modifiedNewsItem);
  }

}
