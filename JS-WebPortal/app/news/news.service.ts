import {Injectable} from 'angular2/core';
import {NewsItem} from './news-item';
import {Http, Response} from 'angular2/http';
import {Observable}     from 'rxjs/Observable';
import {Configuration} from '../configuration/configuration';
import 'rxjs/Rx';

@Injectable()
export class NewsService {
     constructor (private http: Http, private conf: Configuration) {}

    getNewsItems() {
        return this.http.get(this.conf.newsItemUrl)
            .map(res => <NewsItem[]>res.json())
            .catch(this.handleError);
    }
    private handleError(error: Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
        return Observable.throw(error.json().error || 'Server error');
    }

}