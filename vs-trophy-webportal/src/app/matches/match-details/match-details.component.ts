import { Component, OnInit, ViewChild} from '@angular/core';
import { MatchesService } from '../matches.service';
import { ActivatedRoute } from '@angular/router';
import { Match } from '../match';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import { PlayerPerformance } from '../../players/player-performance';
import { RosterPartition } from '../roster-partition';
import { OffenseRosterTableComponent } from '../offense-roster-table/offense-roster-table.component';


@Component({
  selector: 'vst-match-details',
  templateUrl: './match-details.component.html',
  styleUrls: ['./match-details.component.css']
})
export class MatchDetailsComponent implements OnInit {

  constructor(private route: ActivatedRoute, private matchesService: MatchesService) { }


  match: Match;
  team1ActiveRoster: RosterPartition;
  team1Bench: RosterPartition;
  team2ActiveRoster: RosterPartition;
  team2Bench: RosterPartition;

  ngOnInit() {
    this.route.params.subscribe(params =>
      this.matchesService.getMatchDetails((params['matchId']))
        .subscribe(match => this.setMatch(match)))
  }

  setMatch(match: Match) {
    //this is could be done better (parallel, more encapsulated...)
    let tempActiveRoster = new RosterPartition();
    let tempBench = new RosterPartition();
    this.splitRoster(match.firstTeamRoster, tempActiveRoster, tempBench)
    this.team1ActiveRoster = tempActiveRoster;
    this.team1Bench = tempBench;
    console.info(this.team1ActiveRoster)
    tempActiveRoster = new RosterPartition();
    tempBench = new RosterPartition();
    this.splitRoster(match.secondTeamRoster, tempActiveRoster, tempBench)
    this.team2ActiveRoster = tempActiveRoster;
    this.team2Bench = tempBench;
    this.match = match;
  }

  hasElements(perfs:PlayerPerformance[]): boolean{
    console.info(perfs!=null && perfs.length > 0)
    console.info(perfs)
    return perfs!=null && perfs.length > 0;
  }

  //Splits all the performances and places them inside active and bench partitions
  splitRoster(performances: PlayerPerformance[], activeRoster: RosterPartition, benchRoster: RosterPartition) {
    performances.forEach(perf => {
      if (this.isBench(perf)) {
        //Bench
        if (this.isDefense(perf)) {
          benchRoster.defense.push(perf)
        } else if (this.isKicker(perf)) {
          benchRoster.kicker.push(perf)
        } else {
          benchRoster.offense.push(perf)
        }
      } else {
        //Active
        if (this.isDefense(perf)) {
          activeRoster.defense.push(perf)
        } else if (this.isKicker(perf)) {
          activeRoster.kicker.push(perf)
        } else {
          activeRoster.offense.push(perf)
        }
      }
    }
    )
  }

  isBench(performance: PlayerPerformance): boolean {
    return performance.spot.includes("bn");
  }

  isDefense(performance: PlayerPerformance): boolean {
    return performance.performance != null && performance.performance["FantasyPosition"] == "DST"
  }

  isKicker(performance: PlayerPerformance): boolean {
    return performance.performance != null && performance.performance["FantasyPosition"] == "K"
  }

}
