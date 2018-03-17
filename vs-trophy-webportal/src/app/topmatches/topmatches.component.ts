import { Component, OnInit } from '@angular/core';
import {StatsService} from '../stats.service';

@Component({
  selector: 'vst-topmatches',
  templateUrl: './topmatches.component.html',
  styleUrls: ['./topmatches.component.css']
})
export class TopmatchesComponent implements OnInit {

  constructor(private statsService: StatsService) { }

  topMatches: string;

  ngOnInit() {
    this.getTopMatches();
  }

  getTopMatches(): void{
    this.statsService.getTopMatches().subscribe(topMatches => this.topMatches = topMatches);
  }

}
