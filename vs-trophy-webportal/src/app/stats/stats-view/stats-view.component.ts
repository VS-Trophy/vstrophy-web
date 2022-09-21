import { Component, OnInit, Directive as  } from '@angular/core';

@()
@Directive()
@Component({
  selector: 'vst-stats-view',
  templateUrl: './stats-view.component.html',
  styleUrls: ['./stats-view.component.css']
})
export class StatsViewComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
