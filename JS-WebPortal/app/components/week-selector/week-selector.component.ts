import {Component, Input, Output, EventEmitter} from '@angular/core';

import {Week} from '../../model/week/week';

@Component({
    moduleId: module.id,
    selector: 'vst-week-selector',
    templateUrl: 'week-selector.component.html',
    styleUrls: ['week-selector.component.css'],
})
export class WeekSelectorComponent {

    private _seasonList: number[];
    private _weekList: number[];

    constructor() {
        this._weekList = new Array<number>();
    }

    @Input()
    set weekList(weekArray: Array<Week>) {
        this._weekList = new Array<number>();
        if (weekArray != undefined) {
            weekArray.forEach(w => this._weekList.push(w.number));
        }
    }
    @Input()
    set seasonList(seasonArray: Array<number>) {
        this._seasonList = new Array<number>();
        if (seasonArray != undefined) {
            seasonArray.forEach(n => this._seasonList.push(n));
        }
    }


    @Output()
    week = new EventEmitter<Number>();
    @Output()
    season = new EventEmitter<Number>();

    set _weekModel(weekNumber: number) {
        console.log("Week clicked")
        this.week.emit(weekNumber);
    }

    set _seasonModel(season: number) {
        console.log("Season selected");
        this.season.emit(season);
    }



    onSeasonSelect(season: number) {
        this._seasonModel = season;
        this.season.emit(season);
    }

}