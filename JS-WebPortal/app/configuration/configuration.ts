import {Injectable} from 'angular2/core';

@Injectable()
export class Configuration{
   baseURL: string;
   newsItemUrl: string;
   constructor(){
       var host: string = "vstrophy.ch";
       this.baseURL="http://" + host + "/rest-api/";
       this.newsItemUrl = this.baseURL + "newsitem";
   } 
}