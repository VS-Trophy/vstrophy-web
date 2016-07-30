System.register(['angular2/core', '../../model/match/match', '../../model/team/team', '../../services/teams/teams.service'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, match_1, team_1, teams_service_1;
    var MatchComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (match_1_1) {
                match_1 = match_1_1;
            },
            function (team_1_1) {
                team_1 = team_1_1;
            },
            function (teams_service_1_1) {
                teams_service_1 = teams_service_1_1;
            }],
        execute: function() {
            MatchComponent = (function () {
                function MatchComponent(_teamsService) {
                    this._teamsService = _teamsService;
                }
                MatchComponent.prototype.ngOnInit = function () {
                    var _this = this;
                    this._teamsService.getTeam(this.match.firstTeamId)
                        .subscribe(function (t) { _this._firstTeam = new team_1.Team(t); });
                    this._teamsService.getTeam(this.match.secondTeamId)
                        .subscribe(function (t) { _this._secondTeam = new team_1.Team(t); });
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', match_1.Match)
                ], MatchComponent.prototype, "match", void 0);
                MatchComponent = __decorate([
                    core_1.Component({
                        selector: 'vst-match',
                        templateUrl: 'app/components/match/match.component.html',
                        styleUrls: ['app/components/match/match.component.css']
                    }), 
                    __metadata('design:paramtypes', [teams_service_1.TeamsService])
                ], MatchComponent);
                return MatchComponent;
            }());
            exports_1("MatchComponent", MatchComponent);
        }
    }
});
//# sourceMappingURL=match.component.js.map