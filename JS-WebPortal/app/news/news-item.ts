export class NewsItem {
  id: Number;
  title: String;
  author: String;
  timestamp: Date;
  text: String;
  public getShortText(){
      return this.text.slice(0,100);
  }
}