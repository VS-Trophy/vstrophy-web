export class NewsItem {
  id: number;
  title: string;
  author: string;
  timestamp: Date;
  text: string;
  public getShortText(){
      return this.text.slice(0,100);
  }
}