import { Component, OnInit } from '@angular/core';
import { MatchesService } from '../../matches/shared/matches.service';
import { Match } from '../../matches/match';

@Component({
  selector: 'vst-history-view',
  templateUrl: './history-view.component.html',
  styleUrls: ['./history-view.component.css']
})
export class HistoryViewComponent implements OnInit {

  matches: Match[];
  constructor(private matchesService: MatchesService) { }

  ngOnInit() {
    this.getMatches();
  }

  getMatches():void{
    this.matchesService
    .getAllMatches()
    .subscribe(matches => this.matches = matches);
  }

}
