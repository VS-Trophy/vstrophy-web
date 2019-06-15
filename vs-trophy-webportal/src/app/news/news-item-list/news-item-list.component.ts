import { Component, OnInit, Input, Output,EventEmitter, ViewChild } from '@angular/core';
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

  @ViewChild("selectionList")
  selectionList: MatSelectionList

  @Input()
  newsItems: NewsItem[];

  @Output()
  selectEvent: EventEmitter<NewsItem> = new EventEmitter();

  onSelection(selectionChange: MatSelectionListChange){
    this.selectEvent.emit(selectionChange.option.value)
  }



  ngOnInit() {
    //This is necessary, so the list is single select only!
    this.selectionList.selectedOptions = new SelectionModel<MatListOption>(false);
  }

}
