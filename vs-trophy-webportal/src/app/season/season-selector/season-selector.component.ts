import { Component, OnInit } from '@angular/core';
import { SeasonsService } from '../seasons.service';

@Component({
  selector: 'vst-season-selector',
  templateUrl: './season-selector.component.html',
  styleUrls: ['./season-selector.component.css']
})
export class SeasonSelectorComponent implements OnInit {

  constructor(private seasonsService: SeasonsService) { }

  seasonNumbers: number[]

  ngOnInit() {
    this.loadSeasonNumbers()
  }

  private loadSeasonNumbers(){
    console.log("loading season numbers")
    this.seasonsService.getSeasonNumbers().subscribe(numbers => {this.seasonNumbers = numbers; console.log(numbers);})
  }

}
