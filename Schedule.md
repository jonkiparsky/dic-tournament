# Introduction #

A rough list of things that need to be done. This should become an ordered list of tasks. It would be nice to have two views on this: one showing dependencies, one showing a sort of "release timetable" (by features, not by calendar)

# Release 0.1 #
**Expected functionality:**
  * Functional Tournament/Match/Game/Player interaction
  * Minimal results returns - enough to know that we're getting signal

**Coding standards/style:**
  * All exceptions handled
  * Tidy code. 80 column wrap, 4-space tabs, generally pretty

**Non-functional**
  * API stable through some set of changes **Decision point:** we have to decide how far out we want to lock down the API. I suggest until we've added logging, JUnit, and game data. (we can make an exception for adding calls to handle game data)
  * complete and correct javadoc for all tourney classes. For hints, see http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html
  * Games able to inform tournament class if they need decisions made by the tournament runner. (ie, score weightings in an IPD tournament, or ?? rankings and handicaps in chess ??



## Future releases ##
  * split Tournament UI off to separate class, to allow for CLI or GUI interaction. Write this to allow HumanPlayers to piggyback off of this.
  * user-facing documentation (ie, how to write games, how to write players)
  * statistics  (rough framework)
  * presentable statistics report
  * statistics: architecture
  * logging - should we plan on wiring in some logging?
  * unit testing for submitted Game and Player classes/packages
  * Allow for the game to limit the amount of time an implementation has to give a Move. (For example, in Chess maybe you have 30 seconds to determine your next Move)
  * Have our .jar file be a stand alone application that can search a directory on the user's machine for other .jar files or compiled class files which extend Game and Player. This would allow users to write their own implementations and drop them in a folder and play. I expect this is a _much_ later release.

Every N revisions:
  * review and re-finalize interfaces and bottom-level abstract classes

# Timeline To Do #
  * Make decision on player contract **done**
  * Make decision on statistics architecture
    * Are we collecting data only through annotateMove() now?
  * Statistics Framework
    * annotateMove() in Game
  * Game mechanics **done**
    * Game communication with Player **done**
      * updateEachPlayer() and Game control flow **done**
    * Ensure Move security
  * Load players with proper IDs
  * administrivia: user interface for Tournament:
    * allow choice of games
    * allow choice of
      * "test" - human vs choice of player
      * "all" - play all players against each other, SKIPPING human
      * play selected players (free choice)
    * self-play, or no
    * total permutation or elimination tournament
    * (not all options need to be implemented immediately)
    * (in fact, very little of this needs to be implemented immediately)

# Milestones #
  1. (1.0 ?) Be able to run Tournament and receive a screen report() from the Game's DataReader. (This implies we have a working Tournament where are parts are functional and doing their job, though at a very basic level.)