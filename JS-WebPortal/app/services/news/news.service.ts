import {Injectable} from '@angular/core';
import {NewsItem} from '../../model/news-item/news-item';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Configuration} from '../../configuration/configuration';

@Injectable()
export class NewsService {
    constructor(private http: Http, private conf: Configuration) { }

    getNewsItems(): Promise<NewsItem[]> {
        return this.http.get(this.conf.newsItemUrl)
            .toPromise()
            .then(res => res.json() as NewsItem[])
            .catch(this.handleError);
    }

    getNewsItem(id: number): Promise<NewsItem> {
        return this.http.get(this.conf.newsItemUrl + "/" + id)
            .toPromise()
            .then(res => res.json() as NewsItem)
            .catch(this.handleError);
    }

    saveNewsItem(newsItem: NewsItem): Promise<NewsItem> {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.conf.newsItemUrl, JSON.stringify(newsItem), options)
            .toPromise()
            .then(res => res.json() as NewsItem)
            .catch(this.handleError);
    }

    private handleError(error: Response) {
        // in a real world app, we may send the error to some remote logging infrastructure
        // instead of just logging it to the console
        console.error(error);
    }

}