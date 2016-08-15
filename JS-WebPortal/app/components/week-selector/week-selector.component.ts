import {Component, Input, Output, EventEmitter} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {BUTTON_DIRECTIVES, DROPDOWN_DIRECTIVES, ButtonRadioDirective} from 'ng2-bootstrap/ng2-bootstrap';
import {FORM_DIRECTIVES} from '@angular/forms';
import {Week} from '../../model/week/week';

@Component({
    moduleId: module.id,
    selector: 'vst-week-selector',
    templateUrl: 'week-selector.component.html',
    styleUrls: ['week-selector.component.css'],
    directives: [DROPDOWN_DIRECTIVES, BUTTON_DIRECTIVES, CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class WeekSelectorComponent {

    private _seasonModel: number
    private _seasonList: number[]
    @Input()
    set seasonList(seasonArray:Array<number>){
        if(seasonArray!=undefined){
        this._seasonList = seasonArray;
        this.onSeasonSelect(this._seasonList[this._seasonList.length - 1]);
       }
    }   
    
    @Input()
    weekList: Week[];
    @Output()
    week = new EventEmitter<Number>();
    @Output()
    season = new EventEmitter<Number>();

    set _weekModel(weekNumber: number) {
        console.log("Week clicked")
        this.week.emit(weekNumber);
    }



    onSeasonSelect(season:number) {
        this._seasonModel = season;
        this.season.emit(season);
    }

}