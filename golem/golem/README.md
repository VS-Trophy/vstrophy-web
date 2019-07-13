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