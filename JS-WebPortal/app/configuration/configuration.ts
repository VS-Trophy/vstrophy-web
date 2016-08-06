import {Injectable} from '@angular/core';

@Injectable()
export class Configuration{
   baseURL: string;
   newsItemUrl: string;
   teamUrl: string;
   matchUrl: string;
   constructor(){
       var host: string = "vstrophy.ch";
       this.baseURL="http://" + host + "/rest-api/";
       this.newsItemUrl = this.baseURL + "newsitem";
       this.teamUrl = this.baseURL + "team";
       this.matchUrl = this.baseURL + "match"
   } 
}