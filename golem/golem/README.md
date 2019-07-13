# VS-Trophy Golem

## About
This Golem scrapes NLF.com to get all the statistics etc into the database. The different tasks of the Golem are called *Shems*.

## How to use

Run 

``` 
scrapy crawl golem_matches_shem
``` 
to call the matches shem. This shem creates or updates seasons, weeks, matches and rosters. 


Run 
``` 
scrapy crawl golem_players_shem
```
to call the players shem. Creates or updates players and player performances.

All the shems have a parameter *complete*. If this parameter is *True* all the seasons and weeks will be parsed and re-import. All existing data should be updated. If *complete* is *False* only the latest week of the latest season will be parsed and imported. *Complete* is *False* by default.


Calling
``` 
scrapy crawl golem_players_shem -a complete=True
```
will reimport all the player data while
``` 
scrapy crawl golem_players_shem -a complete=False
or
scrapy crawl golem_players_shem
```

will only import the latest week