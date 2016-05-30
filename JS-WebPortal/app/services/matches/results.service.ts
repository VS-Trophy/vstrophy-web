import {Injectable} from 'angular2/core';
import {Http, Response, Headers, RequestOptions} from 'angular2/http';
import {Observable}     from 'rxjs/Observable';
import {Configuration} from '../../configuration/configuration';
import 'rxjs/Rx';

@Injectable()
export class MatchesService {
    constructor(private http: Http, private conf: Configuration) { }


}