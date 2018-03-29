import { Component, OnInit } from '@angular/core';
import { Match } from '../../matches/match';
import { MatchesService } from '../../matches/matches.service';
import { WeekPointer } from '../../season/week-pointer';


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

  getMatches(weekPointer: WeekPointer): void{
    this.matchesService.getMatchesForWeek(weekPointer.season,weekPointer.week).subscribe(matches => this.matches = matches)
  }

    onWeekSelected(weekPointer: WeekPointer){
    this.getMatches(weekPointer)
    this.matches = []
  }

}
