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

  weekNumbers: number[]
  selectedWeek: number
  selectedSeason: number
  @Output() selectedWeekChange = new EventEmitter<WeekPointer>();

  ngOnInit() {
    this.weekNumbers = [-1];
  }

  onSeasonSelected(season: number) {
    this.selectedSeason = season
    if (season != -1) {
      this.seasonsService.getWeekNumbers(season).subscribe(weeks => { this.weekNumbers.push(...weeks); this.setWeek(-1) })
    }
  }
  
  getValueName(season: number){
    if(season == -1){
      return "Alle";
    }
    return season;
  }

  setWeek(week: number) {
    this.selectedWeek = week
    this.selectedWeekChange.emit(new WeekPointer(this.selectedSeason, this.selectedWeek))
  }



}
