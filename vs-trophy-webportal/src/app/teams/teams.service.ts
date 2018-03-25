import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from '../../environments/environment';
import { VSTrophyTeam } from './vstrophyteam';

@Injectable()
export class TeamsService {
  private teamsPath = 'teams'
  private teamMap = new Map<String,VSTrophyTeam>();

  constructor(private http: HttpClient) {
    this.loadAllTeams()
  }

  public getTeamById(nflId: string): VSTrophyTeam{
    var team =  this.teamMap.get(nflId);
    if(team == null){
      console.warn("Could not load team with nflId " +nflId )
    }
    return team
  }
  
  private loadAllTeams(): void{
    console.info("Loading all teams")
    this.http.get<VSTrophyTeam[]>(environment.apiRoot + this.teamsPath).subscribe(teams => teams.forEach(team => this.addTeamToTeamMap(team)))
  }

  private addTeamToTeamMap(team: VSTrophyTeam): void{
    this.teamMap.set(team.nflId,team);
  }
}
