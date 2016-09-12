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
    private _weekList: Week[];

    private _seasonModel:number;

    constructor(){
        this._seasonModel=5;
    }

    @Input()
    set weekList(weekArray: Array<Week>) {
        if(weekArray!=undefined){
            this._weekList = weekArray;
        }
    }
    @Input()
    set seasonList(seasonArray: Array<number>) {
        if (seasonArray != undefined) {
           this._seasonList = seasonArray;
           this.onSeasonSelect(this._seasonList[this._seasonList.length -1])
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

    onSeasonSelect(season: number) {
        this._seasonModel = season;
        this.season.emit(season);
    }

}