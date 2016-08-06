System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Team;
    return {
        setters:[],
        execute: function() {
            Team = (function () {
                function Team(jsonObject) {
                    this.id = jsonObject.id;
                    this.name = jsonObject.name;
                    this.colors = jsonObject.colors;
                    this.city = jsonObject.city;
                    this.stadium = jsonObject.stadium;
                    this.fans = jsonObject.fans;
                    this.logo = jsonObject.logo;
                    this.foundedIn = new Date(jsonObject.foundedIn);
                    this.joinedIn = new Date(jsonObject.joinedIn);
                    this.division = jsonObject.division.name;
                    this.uniform = jsonObject.uniformPicture;
                    this.officials = jsonObject.officials;
                }
                Team.prototype.getFoundedInYear = function () {
                    return this.foundedIn.getFullYear();
                };
                Team.prototype.getJoinedInYear = function () {
                    return this.joinedIn.getFullYear();
                };
                Team.prototype.getLogoUri = function () {
                    return "data:image/png;base64," + this.logo;
                };
                Team.prototype.getUniformUri = function () {
                    return "data:image/png;base64," + this.uniform;
                };
                return Team;
            }());
            exports_1("Team", Team);
        }
    }
});
//# sourceMappingURL=team.js.map