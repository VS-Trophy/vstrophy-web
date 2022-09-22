import { Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import { SeasonsService } from '../seasons.service';


@Component({
  selector: 'vst-season-selector',
  templateUrl: './season-selector.component.html',
  styleUrls: ['./season-selector.component.css']
})
export class SeasonSelectorComponent implements OnInit {

  constructor(private seasonsService: SeasonsService) { }

  @Output()
  selectedSeasonChange = new EventEmitter<number>();
  @Input('selectedSeason')
  set setSelectedSeason(season: number) {
    if (this.seasonNumbers != null && this.seasonNumbers.includes(season)) {
      this.onSelect(season);
    } else {
      console.log('Could not set season ' + season + ' as it is not one of the values');
    }
  }

  seasonNumbers: number[];
  selectedSeasonNumber;



  ngOnInit() {
    this.seasonNumbers = [-1];
    this.loadSeasonNumbers();
  }

  onSelect(season: number): void {
    this.selectedSeasonChange.emit(season);
    this.selectedSeasonNumber = season;
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
