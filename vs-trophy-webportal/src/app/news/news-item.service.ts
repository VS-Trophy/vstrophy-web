import { Injectable } from '@angular/core';
import { NewsItem } from './news-item';
import { Observable,from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NewsItemService {

  constructor() { }

  getAllNewsItems(): Observable<NewsItem[]> {
    const items: NewsItem[] =[
      {
        author: "ich",
        content: "ihms content",
        publicationDate: (new Date()).getDate(),
        title: "Der Artikul"
      }, {
        author: "der andru",
        content: "ihms content isch hie bizi anersch",
        publicationDate: (new Date()).getDate(),
        title: "Der aner Artikl"
      }
    ];

    return from(new Promise(resolve => resolve(items)));
  }

  updateNewsItem(modifiedItem: NewsItem){
    console.info("Modifying news item...");
  }
}
