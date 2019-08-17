import { Component, OnInit } from '@angular/core';
import { MatchesService } from '../matches.service';
import { ActivatedRoute } from '@angular/router';
import { Match } from '../match';

@Component({
  selector: 'vst-match-details',
  templateUrl: './match-details.component.html',
  styleUrls: ['./match-details.component.css']
})
export class MatchDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute, private matchesService: MatchesService) { }

  match: Match;

  ngOnInit() {
    this.route.params.subscribe(params =>
      this.matchesService.getMatchDetails((params['matchId']))
      .subscribe(result => this.match = result))
  }

}
