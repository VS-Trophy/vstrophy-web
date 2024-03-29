import { Injectable } from '@angular/core';
import { NewsItem } from './news-item';
import { Observable,from } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { catchError, map } from 'rxjs/operators';
import { ExceptionService } from '../core/exception.service';

@Injectable({
  providedIn: 'root'
})
export class NewsItemService {

  constructor(private http: HttpClient, private exceptionService: ExceptionService) { }

  getAllNewsItems(): Observable<NewsItem[]> {
    return this.http.get<NewsItem[]>(environment.apiRoot + `newsItems/`)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getNewsItems", []))
      );
  }

  getNewsItem(newsItemId: string): Observable<NewsItem> {
    return this.http.get<NewsItem[]>(environment.apiRoot + `newsItems/` + newsItemId)
      .pipe(
        map(newsItems => newsItems[0])
      );
  }

  getAllNewsItemsPaged(count: number, offset: number): Observable<NewsItem[]> {
    return this.http.get<NewsItem[]>(environment.apiRoot + `newsItems/?count=${count}&offset=${offset}`)
      .pipe(
        catchError(this.exceptionService.handleHttpError("getNewsItems", []))
      );
  }

  updateNewsItem(modifiedItem: NewsItem):Observable<NewsItem>{
   return this.http.post<NewsItem>(environment.apiRoot+`newsItems/`,modifiedItem)
   .pipe(
    catchError(this.exceptionService.handleHttpError("updateNewsItem", null))
   );
  }
}
