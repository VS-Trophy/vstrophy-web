import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { VSTrophyTeam } from '../vstrophyteam';
import { TeamsService } from '../teams.service';

@Component({
  selector: 'vst-team-selector',
  templateUrl: './team-selector.component.html',
  styleUrls: ['./team-selector.component.css']
})
export class TeamSelectorComponent implements OnInit {

  constructor(private teamsService: TeamsService) { }

  teams: VSTrophyTeam[];

  selectedTeam: VSTrophyTeam;

  @Input()
  caption: string;

  @Input()
  disabled: boolean;

  @Output()
  selectedEvent: EventEmitter<VSTrophyTeam> = new EventEmitter<VSTrophyTeam>()

  ngOnInit() {
    this.teamsService.getAllTeams().subscribe(teams => this.teams = teams)
  }

  onSelect(team: VSTrophyTeam){
    this.selectedEvent.emit(team);
  }

}
