import { PlayerPerformance } from '../players/player-performance';

export class Match {
    matchId: string;
    firstTeamName: string;
    firstTeamId: string;
    firstTeamLogoPath: string;
    firstTeamPoints: number;
    secondTeamName: string;
    secondTeamId: string;
    secondTeamLogoPath: string;
    secondTeamPoints: number;
    season: number;
    week: number;
    margin: number;
    totalScore: number;
    firstTeamRoster: PlayerPerformance[];
    secondTeamRoster: PlayerPerformance[];
}
