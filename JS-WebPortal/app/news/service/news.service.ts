import {Injectable} from 'angular2/core';
import {NewsItem} from '../model/news-item';
import {Http, Response,Headers, RequestOptions} from 'angular2/http';
import {Observable}     from 'rxjs/Observable';
import {Configuration} from '../../configuration/configuration';
import 'rxjs/Rx';

@Injectable()
export class NewsService {
     constructor (private http: Http, private conf: Configuration) {}

    getNewsItems():Observable<NewsItem[]>{
        return this.http.get(this.conf.newsItemUrl)
            .map(res => <NewsItem[]>res.json())
            .catch(this.handleError);
    }
    
    getNewsItem(id:number):Observable<NewsItem>{
        return this.http.get(this.conf.newsItemUrl + "/" + id)
        .map(res => <NewsItem>res.json())
        .catch(this.handleError);
    }
    
    saveNewsItem(newsItem : NewsItem):Observable<NewsItem>{
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.conf.newsItemUrl,JSON.stringify(newsItem),options)
        .map(res => <NewsItem>res.json())
        .catch(this.handleError);
    }
    
    private handleError(error: Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

}