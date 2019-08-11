# -*- coding= utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in=
# https=//doc.scrapy.org/en/latest/topics/items.html

import scrapy


class WeekItem(scrapy.Item):
    season = scrapy.Field()
    week = scrapy.Field()
    pass


class MatchItemVST(scrapy.Item):
    season = scrapy.Field()
    week = scrapy.Field()
    roster1 = scrapy.Field()
    roster2 = scrapy.Field()


class RosterItemVST(scrapy.Item):
    team_key = scrapy.Field()
    points = scrapy.Field()
    spots = scrapy.Field()


class PlayerItemVST(scrapy.Item):
    player_key = scrapy.Field()
    player_name = scrapy.Field()


class PlayerUpdateItemVST(scrapy.Item):
    player_key = scrapy.Field()
    player_birthday = scrapy.Field()
    player_fd_id = scrapy.Field()


class RosterSpotItemVST(scrapy.Item):
    spot_name = scrapy.Field()
    player = scrapy.Field()


class PlayerPerformanceItemVST(scrapy.Item):
    PlayerID = scrapy.Field()
    Name = scrapy.Field()
    Season = scrapy.Field()
    Played = scrapy.Field()
    Started = scrapy.Field()
    Week = scrapy.Field()
    Opponent = scrapy.Field()
    TeamIsHome = scrapy.Field()
    Result = scrapy.Field()
    HomeScore = scrapy.Field()
    AwayScore = scrapy.Field()
    GameDate = scrapy.Field()
    PassingCompletions = scrapy.Field()
    PassingAttempts = scrapy.Field()
    PassingCompletionPercentage = scrapy.Field()
    PassingYards = scrapy.Field()
    PassingYardsPerAttempt = scrapy.Field()
    PassingTouchdowns = scrapy.Field()
    PassingInterceptions = scrapy.Field()
    PassingRating = scrapy.Field()
    RushingAttempts = scrapy.Field()
    RushingYards = scrapy.Field()
    RushingYardsPerAttempt = scrapy.Field()
    RushingTouchdowns = scrapy.Field()
    Receptions = scrapy.Field()
    ReceivingTargets = scrapy.Field()
    ReceivingYards = scrapy.Field()
    ReceptionPercentage = scrapy.Field()
    ReceivingTouchdowns = scrapy.Field()
    ReceivingLong = scrapy.Field()
    ReceivingYardsPerTarget = scrapy.Field()
    ReceivingYardsPerReception = scrapy.Field()
    Fumbles = scrapy.Field()
    FumblesLost = scrapy.Field()
    FieldGoalsMade = scrapy.Field()
    FieldGoalsAttempted = scrapy.Field()
    FieldGoalPercentage = scrapy.Field()
    FieldGoalsLongestMade = scrapy.Field()
    ExtraPointsMade = scrapy.Field()
    ExtraPointsAttempted = scrapy.Field()
    TacklesForLoss = scrapy.Field()
    Sacks = scrapy.Field()
    QuarterbackHits = scrapy.Field()
    Interceptions = scrapy.Field()
    FumblesRecovered = scrapy.Field()
    Safeties = scrapy.Field()
    DefensiveTouchdowns = scrapy.Field()
    SpecialTeamsTouchdowns = scrapy.Field()
    SoloTackles = scrapy.Field()
    AssistedTackles = scrapy.Field()
    SackYards = scrapy.Field()
    PassesDefended = scrapy.Field()
    FumblesForced = scrapy.Field()
    FantasyPoints = scrapy.Field()
    PointsAllowedByDefenseSpecialTeams = scrapy.Field()
    TotalTackles = scrapy.Field()
    FantasyPosition = scrapy.Field()
    Position = scrapy.Field()
    Team = scrapy.Field()
