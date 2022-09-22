import { Component, OnInit, Input, Output, EventEmitter, ViewChild} from '@angular/core';
import { NewsItem } from '../news-item';
import {  } from 'events';
import { MatSelectionList, MatListOption, MatSelectionListChange } from '@angular/material/list';
import { SelectionModel } from '@angular/cdk/collections';


@Component({
  selector: 'vst-news-item-list',
  templateUrl: './news-item-list.component.html',
  styleUrls: ['./news-item-list.component.css']
})
export class NewsItemListComponent implements OnInit {

  constructor() { }

  @ViewChild("selectionList", { static: true })
  selectionList: MatSelectionList

  @Input()
  newsItems: NewsItem[];

  @Output()
  selectEvent: EventEmitter<NewsItem> = new EventEmitter();


  onSelection(selectionChange: MatSelectionListChange){
    this.selectEvent.emit(selectionChange.options.values[0])
  }

  createNew(){
    let newNewsItem:NewsItem = {author:null,publicationDate:null,content:null,title:"Not Saved Yet",_key:null}
    this.newsItems.push(newNewsItem);
  }



  ngOnInit() {
    //This is necessary, so the list is single select only!
    this.selectionList.selectedOptions = new SelectionModel<MatListOption>(false);
  }

}
