# VS-Trophy Golem

## About
This Golem scrapes NLF.com and fantasydata.com to get all the statistics etc into the database. The different tasks of the Golem are called *Shems*.

## Shems
The following shems can be called:

### nflfantasy_matches_shem
This shem scrapes nfl.com for matches. Creates or updates seasons, weeks, matches and rosters. This should probably run first

### nfl_player_birthday_shem
This shem scrapes the players birthdays from nfl.com. Updates players. This information is needed to map nfl.com ids to fantasydata.com ids. This should run before fd_id_shem

### fd_id_shem
This shem sets the fantasydata.com id of each player. Players with the same name need to have their birthday set, so they can be identified

### fd_player_shem
This shem scrapes the player performances from fantasydata.com. Creates or updates players and player performances.

## How to use

Run 

``` 
scrapy crawl nflfantasy_matches_shem
``` 
to call the matches shem. This shem creates or updates seasons, weeks, matches and rosters. 


Run 
``` 
scrapy crawl fd_player_shem
```
to call the players shem. Creates or updates players and player performances.

Some shems have a parameter *complete*. If this parameter is *True* all the seasons and weeks will be parsed and re-imported. All existing data should be updated. If *complete* is *False* only the latest week of the latest season will be parsed and imported. *Complete* is *False* by default.


Calling
``` 
scrapy crawl fd_player_shem -a complete=True
```
will reimport all the player performance data while
``` 
scrapy crawl fd_player_shem -a complete=False
or
scrapy crawl fd_player_shem
```

will only import the latest week