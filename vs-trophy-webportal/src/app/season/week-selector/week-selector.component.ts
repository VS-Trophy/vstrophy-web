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
  @Output() selectedWeekChange= new EventEmitter<WeekPointer>();

  ngOnInit() {
  }

  onSeasonSelected(season: number){
    this.selectedSeason = season
    this.seasonsService.getWeekNumbers(season).subscribe(weeks => {this.weekNumbers = weeks; this.setWeek(weeks[0])})
  }

  setWeek(week:number){
    this.selectedWeek = week
    this.selectedWeekChange.emit(new WeekPointer(this.selectedSeason,this.selectedWeek))
  }



}
