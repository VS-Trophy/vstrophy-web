import { Component, OnInit, Input } from '@angular/core';
import { Match } from '../match';

@Component({
  selector: 'vst-match',
  templateUrl: './match.component.html',
  styleUrls: ['./match.component.css']
})
export class MatchComponent implements OnInit {

  @Input() match: Match

  constructor() { }

  ngOnInit() {
  }

}
