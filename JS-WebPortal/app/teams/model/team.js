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
                }
                Team.prototype.getLogoUri = function () {
                    return "data:image/png;base64," + this.logo;
                };
                return Team;
            }());
            exports_1("Team", Team);
        }
    }
});
//# sourceMappingURL=team.js.map