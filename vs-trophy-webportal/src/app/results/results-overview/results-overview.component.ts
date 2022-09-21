import { Component, OnInit, Directive as  } from '@angular/core';

@()
@Directive()
@Component({
  selector: 'vst-results-overview',
  templateUrl: './results-overview.component.html',
  styleUrls: ['./results-overview.component.css']
})
export class ResultsOverviewComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
