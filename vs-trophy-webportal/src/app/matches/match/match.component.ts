import { Component, OnInit, Input} from '@angular/core';
import { Match } from '../match';

const WINNER: string = "winner";
const LOSER: string = "loser";


@Component({
  selector: 'vst-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent implements OnInit {

  @Input()
  match: Match;

  @Input()
  badgeValue: string;

  getProgressBarValue(): number{
    if(this.match != null){
      const totalPoints = 
      this.match.firstTeamPoints 
      + this.match.secondTeamPoints;
      return this.match.firstTeamPoints * 100 / totalPoints
    }
    return 0;
  }

  constructor() { }

  ngOnInit() {
  }

  getFirstTeamClass():string{
    if(this.match.firstTeamPoints >= this.match.secondTeamPoints){
      return WINNER;
    } else {
      return LOSER;
    }
  }
  getSecondTeamClass():string{
    if(this.match.secondTeamPoints >= this.match.firstTeamPoints){
      return WINNER;
    } else {
      return LOSER;
    }
  }
  


}
