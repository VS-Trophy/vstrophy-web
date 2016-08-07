export class Team{
 id:number;
 name:string;
 colors:string;
 city:string;
 stadium:string;
 fans:string;
 logo:string;
 foundedIn:Date;
 joinedIn:Date;
 division:string
 uniform:string;
 officials:Object[];
 constructor(jsonObject){
     console.info("consturctor")
     console.info(jsonObject);
     this.id = jsonObject.id;
     this.name = jsonObject.name;
     this.colors = jsonObject.colors;
     this.city = jsonObject.city;
     this.stadium = jsonObject.stadium;
     this.fans = jsonObject.fans;
     this.logo = jsonObject.logo;
     this.foundedIn = new Date(jsonObject.foundedIn);
     this.joinedIn = new Date(jsonObject.joinedIn);
     this.division = jsonObject.division.name;
     this.uniform = jsonObject.uniformPicture;
     this.officials = jsonObject.officials;
     console.info(this)
 }

 getFoundedInYear():number{
     return this.foundedIn.getFullYear();
 }
 
  getJoinedInYear():number{
     return this.joinedIn.getFullYear();
 }
 
 getLogoUri():string{
     return "data:image/png;base64," + this.logo;
 }
 
  getUniformUri():string{
     return "data:image/png;base64," + this.uniform;
 }
}