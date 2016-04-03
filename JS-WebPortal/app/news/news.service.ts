import {Injectable} from 'angular2/core';
import {NewsItem} from './news-item';



@Injectable()
export class NewsService {
    private _newsItems : NewsItem[] = [
        {id:1,title:"First",author:"Fabi",text:"Hie steit der text",timestamp: new Date(2016,2,14,12,34)},
        {id:2,title:"Second",author:"Chris",text:"Hie steit der aner text",timestamp: new Date(2016,2,15,12,34)},
    ];
    
   getNewsItems() : Promise<NewsItem[]>{
       return Promise.resolve(this._newsItems);
   }
}