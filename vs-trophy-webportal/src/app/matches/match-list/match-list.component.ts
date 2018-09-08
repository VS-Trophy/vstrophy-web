import { Component, OnInit, Input } from '@angular/core';
import { Match } from '../match';

@Component({
  selector: 'vst-match-list',
  templateUrl: './match-list.component.html',
  styleUrls: ['./match-list.component.css']
})
export class MatchListComponent implements OnInit {

  @Input()
  matches: Match[]

  @Input()
  title: string

  constructor() { }

  ngOnInit() {
  }

}
