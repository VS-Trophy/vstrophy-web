import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { SeasonsService } from '../seasons.service';

@Component({
  selector: 'vst-season-selector',
  templateUrl: './season-selector.component.html',
  styleUrls: ['./season-selector.component.css']
})
export class SeasonSelectorComponent implements OnInit {

  constructor(private seasonsService: SeasonsService) { }

  @Output()
  selectedEvent = new EventEmitter<number>();

  seasonNumbers: number[];

  selectedSeason: number;

  ngOnInit() {
    this.seasonNumbers = [-1];
    this.loadSeasonNumbers();
  }

  onSelect(season: number): void {
    this.selectedEvent.emit(season);
    this.selectedSeason = season;
  }

  getValueName(season: number) {
    if (season === -1) {
      return 'Alle';
    }
    return season;
  }

  private loadSeasonNumbers() {
    this.seasonsService.getSeasonNumbers().subscribe(numbers => {this.seasonNumbers.push(...numbers); this.onSelect(numbers[0]); });
  }

}
