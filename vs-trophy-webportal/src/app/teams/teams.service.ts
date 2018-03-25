import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { environment } from '../../environments/environment';
import { VSTrophyTeam } from './vstrophyteam';

@Injectable()
export class TeamsService {
  private teamsPath = 'teams'
  private teamMap = new Map<String,VSTrophyTeam>();

  constructor(private http: HttpClient) {}

  public getTeamById(nflId: string): VSTrophyTeam{
    return this.teamMap.get(nflId);
  }
  
  private loadAllTeams(): void{
    this.http.get<VSTrophyTeam[]>(environment.apiRoot + this.teamsPath).subscribe(teams => teams.forEach(this.addTeamToTeamMap))
  }

  private addTeamToTeamMap(team: VSTrophyTeam): void{
    this.teamMap.set(team.nflId,team);
  }
}
