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

  isDisabled: boolean;

  @Input()
  caption: string;

  @Input("disabled")
  set setDisabled(disabled: boolean) {

    if (disabled) {
      this.selectedTeam = null;
    }
    this.isDisabled = disabled;
  }

  @Output()
  selectedEvent: EventEmitter<VSTrophyTeam> = new EventEmitter<VSTrophyTeam>()

  ngOnInit() {
    const nullTeam: VSTrophyTeam = new VSTrophyTeam();
    nullTeam._key = "";
    nullTeam.name = "Alle";
    this.teams = [nullTeam];
    this.teamsService.getAllTeams().subscribe(teams => this.teams.push(...teams))
  }

  onSelect(team: VSTrophyTeam) {
    if (team.name == "Alle") {
      this.selectedEvent.emit(null);
    } else {
      this.selectedEvent.emit(team);
    }

  }

}
