export class Team{
 id:number;
 name:string;
 colors:string;
 city:string;
 stadium:string;
 fans:string;
 logo:string;
 constructor(jsonObject){
     this.id = jsonObject.id;
     this.name = jsonObject.name;
     this.colors = jsonObject.colors;
     this.city = jsonObject.city;
     this.stadium = jsonObject.stadium;
     this.fans = jsonObject.fans;
     this.logo = jsonObject.logo;
 }
 
 getLogoUri():string{
     return "data:image/png;base64," + this.logo;
 }
}