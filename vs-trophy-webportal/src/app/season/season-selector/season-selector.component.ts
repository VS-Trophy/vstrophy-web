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

  selectedSeason: number

  ngOnInit() {

    this.loadSeasonNumbers()
  }

  onSelect(season: number): void {
    console.log("Updating season " + season)
    this.selectedSeason = season;
  }

  private loadSeasonNumbers(){
    console.log("loading season numbers")
    this.seasonsService.getSeasonNumbers().subscribe(numbers => {this.seasonNumbers = numbers;this.selectedSeason = numbers[0]})
  }

}
