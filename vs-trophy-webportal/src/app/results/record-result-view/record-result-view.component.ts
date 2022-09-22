import { Component, OnInit} from '@angular/core';
import { MatchesService } from '../../matches/matches.service';
import { Match } from '../../matches/match';


@Component({
  selector: 'vst-record-result-view',
  templateUrl: './record-result-view.component.html',
  styleUrls: ['./record-result-view.component.css']
})
export class RecordResultViewComponent implements OnInit {

  constructor(private matchesService: MatchesService) { }

  closestMatchesEver: Match[]
  mostDecisiveMatchesEver: Match[]
  topScoringMatchesEver: Match[]
  leastScoringMatchesEver: Match[]

  ngOnInit() {
    this.matchesService.getClosestMatches(null,null,null).subscribe(matches => this.closestMatchesEver = matches)
    this.matchesService.getMostDecisiveMatches(null,null,null).subscribe(matches => this.mostDecisiveMatchesEver = matches)
    this.matchesService.getTopScoringMatches(null,null,null).subscribe(matches => this.topScoringMatchesEver = matches)
    this.matchesService.getLeastScoringMatches(null,null,null).subscribe(matches => this.leastScoringMatchesEver = matches)
  }

}
