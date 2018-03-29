import { Component, OnInit } from '@angular/core';
import { Match } from '../../matches/match';
import { MatchesService } from '../../matches/matches.service';


@Component({
  selector: 'vst-results-view',
  templateUrl: './results-view.component.html',
  styleUrls: ['./results-view.component.css']
})
export class ResultsViewComponent implements OnInit {

  matches : Match[]

  constructor(private matchesService: MatchesService) { }

  ngOnInit() {
  }

  getMatches(season: number): void{
    this.matchesService.getMatchesForWeek(season,1).subscribe(matches => {this.matches = matches;console.log("Matches received!")})
  }

  onSeasonSelected(selectedSeason: number){
    this.getMatches(selectedSeason)
    this.matches = []
  }

}
