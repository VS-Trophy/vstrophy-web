import { Component, OnInit, Output, EventEmitter, Input, Directive as  } from '@angular/core';
import { SeasonsService } from '../seasons.service';
import { WeekPointer } from '../week-pointer';

@()
@Directive()
@Component({
  selector: 'vst-week-selector',
  templateUrl: './week-selector.component.html',
  styleUrls: ['./week-selector.component.css']
})
export class WeekSelectorComponent implements OnInit {

  constructor(private seasonsService: SeasonsService) { }
  private defaultWeekNumbers: number[];
  weekNumbers: number[];

  selectedWeekNumber: number;
  selectedSeasonNumber: number;
  @Output() selectedWeekPointerChange = new EventEmitter<WeekPointer>();
  @Input('selectedWeekPointer')
  set setSelectedWeekPointer(weekPointer: WeekPointer) {
    if (weekPointer != null) {
      this.selectedWeekNumber = weekPointer.week;
      this.selectedSeasonNumber = weekPointer.season;
    }
  }

  ngOnInit() {
    this.defaultWeekNumbers = [-1];
    this.defaultWeekNumbers.push(...Array.from({ length: 17 }, (v, k) => k + 1));
    this.weekNumbers = [-1];
  }

  onSeasonSelected(season: number) {
    this.selectedSeasonNumber = season;
    if (season !== -1) {
      this.weekNumbers = [-1];
      this.seasonsService.getWeekNumbers(season).subscribe(weeks => {
        this.weekNumbers =[-1]
        this.weekNumbers.push(...weeks);
        if (!this.weekNumbers.includes(this.selectedWeekNumber)) {
          this.setWeek(this.weekNumbers[this.weekNumbers.length - 1]);
        }
        this.emitEvent();
      });
    } else {
      this.weekNumbers = this.defaultWeekNumbers;
      this.emitEvent();
    }
  }

  getValueName(season: number) {
    if (season === -1) {
      return 'Alle';
    }
    return season;
  }

  emitEvent() {
    this.selectedWeekPointerChange.emit(new WeekPointer(this.selectedSeasonNumber, this.selectedWeekNumber));
  }

  setWeek(week: number) {
    this.selectedWeekNumber = week;
    this.emitEvent();
  }



}
