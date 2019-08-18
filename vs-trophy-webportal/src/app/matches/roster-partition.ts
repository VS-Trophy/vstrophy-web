import { PlayerPerformance } from '../players/player-performance';

export class RosterPartition {
    offense: PlayerPerformance[] = [];
    defense: PlayerPerformance[] = [];
    kicker: PlayerPerformance[] = [];
}
