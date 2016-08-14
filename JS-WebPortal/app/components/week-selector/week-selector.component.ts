import {Component, Input, Output, EventEmitter} from '@angular/core';
import {CORE_DIRECTIVES} from '@angular/common';
import {BUTTON_DIRECTIVES, ButtonRadioDirective} from 'ng2-bootstrap/ng2-bootstrap';
import {FORM_DIRECTIVES} from '@angular/forms';
import {Week} from '../../model/week/week';

@Component({
    moduleId: module.id,
    selector: 'vst-week-selector',
    templateUrl: 'week-selector.component.html',
    styleUrls: ['week-selector.component.css'],
    directives: [BUTTON_DIRECTIVES, CORE_DIRECTIVES, FORM_DIRECTIVES]
})
export class WeekSelectorComponent {
    private _weekModel:number

    @Input()
    weekList: Week[];
    @Output()
    weekId = new EventEmitter<Number>();

    set weekModel(id:number){
        this.weekId.emit(id);
    } 
}