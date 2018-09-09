module.exports.currentWeek = function(){
    var week = 0;
    var date = new Date(2018,8, 3, 7, 0, 0, 0);
    var now = new Date()
   
   while (date.getTime() < now.getTime() && week < 17) {
      date = new Date(date.getTime() + 1000 * 60 * 60 * 24 *7)
        ++week;
    }
    return week;
  }
  module.exports.currentSeason = function(){
    return 2018
  }