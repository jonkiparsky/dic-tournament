# Introduction #

A tournament should be able to report interesting data about the particular games played. Gathering and reporting this data is an interesting challenge, given that we want to represent a broad range of games, and we want to leave the final decisions on this up to the users of the platform.

# Data To Gather Per Game #
  * dentities of Players (contestants - programmers writing Players - should be given a naming convention, based perhaps on DIC identity, game name, and an index number if multiple submissions are allowed)
  * inal status for each player: win, lose, or draw
  * oves taken per player


### Data to gather per Match ###
(a match will be a set of games played by one tuple of Players)
  * Identities of players
  * Number of games won

### Data to gather per tournament ###
  * Overall winner
  * derived stats of various sorts



# Ideas, questions, discussion, and decisions #



## Idea: gather data per player? ##

jpk: Looking at the data we want to gather, it seems to me the level of granularity we want might be easiest to get if we track at least some of the statistics against each player, not against each match.