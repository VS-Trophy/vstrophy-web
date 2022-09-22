import { Component, OnInit, Input} from '@angular/core';


@Component({
  selector: 'vst-simple-stat',
  templateUrl: './simple-stat.component.html',
  styleUrls: ['./simple-stat.component.css']
})
export class SimpleStatComponent implements OnInit {

  constructor() { }

  @Input()
  statValue: number;

  @Input()
  statName: string;

  ngOnInit() {
  }

}
