import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { SeasonsService } from '../seasons.service';
import { WeekPointer } from '../week-pointer';

@Component({
  selector: 'vst-week-selector',
  templateUrl: './week-selector.component.html',
  styleUrls: ['./week-selector.component.css']
})
export class WeekSelectorComponent implements OnInit {

  constructor(private seasonsService: SeasonsService) { }
  private defaultWeekNumbers: number[]
  weekNumbers: number[]
  selectedWeek: number
  selectedSeason: number
  @Output() selectedWeekChange = new EventEmitter<WeekPointer>();

  ngOnInit() {
    this.defaultWeekNumbers = [-1];
    this.defaultWeekNumbers.push(...Array.from({ length: 17 }, (v, k) => k + 1));
    this.weekNumbers = [-1];
  }

  onSeasonSelected(season: number) {
    this.selectedSeason = season
    if (season != -1) {
      this.weekNumbers = [-1];
      this.seasonsService.getWeekNumbers(season).subscribe(weeks => {
        this.weekNumbers.push(...weeks);
        if (!this.weekNumbers.includes(this.selectedWeek)) {
          this.setWeek(-1)
        }
        this.emitEvent();
      })
    } else {
      this.weekNumbers = this.defaultWeekNumbers;
      this.emitEvent();
    }
  }

  getValueName(season: number) {
    if (season == -1) {
      return "Alle";
    }
    return season;
  }

  emitEvent() {
    this.selectedWeekChange.emit(new WeekPointer(this.selectedSeason, this.selectedWeek));
  }

  setWeek(week: number) {
    this.selectedWeek = week
    this.emitEvent();
  }



}
