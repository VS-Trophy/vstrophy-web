import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

@Injectable()
export class ExceptionService {

  constructor() { }
/**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
public handleHttpError<T> (operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {
     // log to console
    console.error("Error in operation " + operation)
    console.error(error);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}
}
