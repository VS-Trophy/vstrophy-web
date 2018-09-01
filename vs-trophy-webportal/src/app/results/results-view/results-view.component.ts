import { Component, OnInit } from '@angular/core';
import { Match } from '../../matches/match';
import { MatchesService } from '../../matches/matches.service';
import { WeekPointer } from '../../season/week-pointer';
import { VSTrophyTeam } from '../../teams/vstrophyteam';


@Component({
  selector: 'vst-results-view',
  templateUrl: './results-view.component.html',
  styleUrls: ['./results-view.component.css']
})
export class ResultsViewComponent implements OnInit {

  matches: Match[]

  private weekPointer: WeekPointer;
  private team1: VSTrophyTeam;
  private team2: VSTrophyTeam;

  constructor(private matchesService: MatchesService) { }

  ngOnInit() {
  }

  getMatches(): void {
    if (this.weekPointer.season != null && this.weekPointer.week != null) {
      this.matchesService.getMatchesForWeek(this.weekPointer, this.team1, this.team2).subscribe(matches => this.matches = matches)
    }
  }

  onWeekSelected(weekPointer: WeekPointer) {
    this.weekPointer = weekPointer;
    this.getMatches();
  }

  onTeam1Selected(team: VSTrophyTeam){
    this.team1 = team;
    this.getMatches();
  }

  onTeam2Selected(team: VSTrophyTeam){
    this.team2 = team;
    this.getMatches();
  }

}
