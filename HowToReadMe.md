# How to Use the API to Write Game and Player Implementations #

Tournament is a framework for defining Games which can be played by machine players, and for independently defining Players for those Games, and placing those Players into competition with each other.

# Overview of Tournament #

There are two entities which can be called a "game". One is a sequence of moves committed by a set of Players, conforming to agreed-upon rules, leading to a final result. The other is the set of rules under which those rules are made, broadly taken to include the definition of the playing surface and materials, sequence of turns, etc.

The Game class, along with associated classes (Tourney, Move, Player) are the means by which Games in the latter sense (sets of rules) are specified under Tournament, and the overall Tournament mechanism is the means by which games in the former sense (sets of moves) are executed.

The Tournament class allows a user to select a Game and create a Tournament of various available Players. Those Players are organized (largely automatically) into suitable Matches, where a Match is a sequence of Games played by one set of Players. Sufficient Games should be played in a Match to eliminate the vagaries of chance, where chance is an element in a game.

The result of running a Game, Match, or Tournament is a Result object, which collects and collates the results of the Games played, and ideally, organizes the data thus created to reveal the interesting facts about the games played. What facts are considered "interesting" depends upon the designer's whim.



# Writing a Game Implementation #

The Tournament framework is designed facilitate designing a Game (in the sense of a set of rules) by addressing as far as possible all of the ordinary mechanics of running a game, leaving only the elements specific to individual games to be written by a game designer. This may cause some confusion at first, since much of what we expect to have to write is in fact taken care of for you. Review the Game life cycle below, and the javadoc for the TurnBasedGame and SimultaneousGame classes, and any confusion should be assuaged.

An implementation of a complete game consists of, at minimum, a Game, a Move, a Player. a GameResult, and a DataReader. Each of these plays a specific role in the Tournament framework.

  * Game defines the sequence of play and what moves are allowable at any given point.
  * Move encapsulates a Player's turn at play. Moves are also used to convey the current status of play to other Players, and to record the sequence of play. Move can and should be decorated by the Game class with information about the game situation appropriate for each of these destinations.
  * Player, from the perpective of the Game designer, is a superclass for Players of this particular game. The Player for a particular Game should have abstract methods for any queries which will be directed at a Player by the Game or by the data analysis package after the game.
  * GameResult is at minimum a collection of Moves committed in the game. Ideally initial analysis will be performed at this level, but game analysis is still at a minimal state, so no more will be said about this now.
  * A DataReader is a game-specific class for analysis of a Tournament after it has been played out.

## Game ##

Sequence of play is defined within the lifecycle of a Game by means of a set of hook methods. For most games, filling in the appropriate methods with some simple code will suffice to correctly progress through the Players provided and evaluate their moves. Games with straightforward turn-taking or simultaneous moves will be transparently executed correctly. Games in which play passes irregularly from player to player will require more ingenuity from the designer or further development of the Tournament framework.
Scoring and victory can be largely decided by the Results classes. Unless a game's score affects play - for example, if score is part of the termination conditions - the Game class can be completely ignorant of the score.


The life cycle of a Game proceeds as follows:

**init()** is called once per Game, at the start of the play() method. It should be used for any setup required. If a game requires an initial configuration unique to each instance (ie, dealing a deck of cards, random draw of Scrabble tiles, etc) this should be performed in init().

  1. **prePoll** is called at the start of each iteration of the game loop.
  1. **poll** - solicits moves from players. Poll should not require modification.
  1. **process** - This method will almost certainly need to be modified. Any work required after a turn (one player of a turn-based game or all players of a simultaneous game) must be performed here, or in methods called from this method. The two important tasks here are decorating the submitted Move(s) for distribution to other Players and storage in the GameResult, and updating the Game's conception of the state of play. For example, a Chess implementation would need to take note of whether a move played has affected the Player's ability to castle in future, and a Battleship implementation would need to keep track of hits scored in order to know when the game ends.
  1. **updateEachPlayer** by default passes the recently submitted Move (or Moves, in a simultaneous game) to each other player, with the submitting Player's ID appended to it. It may be necessary to modify this, but the designer should consider overriding the moveForPlayer method to provide a dispatch table to direct customized Moves at each Player rather than overriding updateEachPlayer.
  1. **postUpdate** If any action is required after the updates are provided to the Players, it can be written in this method.
  1. **record** commits the Move or Moves gathered in this turn to the GameResult object for this Game. This method should not require modification by the game designer. Instead, the Move or Moves submitted in this turn should be prepared for entry in the game record.


These are the methods in the main life cycle. There are some other methods that the designer should be aware of. These are called by lifecycle methods, but are not directly part of the life cycle.

  * **isLegal** is perhaps the most important method in the game. If this method judges that a Move is an illegal one at the current state of play, the current Match is aborted and the Player who commits it is ejected from the Tournament. It is obviously important to execute this correctly, as an illegal Move can corrupt the game state in ways that cannot be dependably detected in post-game analysis.
  * **keepGoing** should be set to false when the game reaches a terminal state - win, lose, or draw.
  * **abort** exits the game irregularly. Should not be needed. Please inform the framework maintainers if you feel the need to use this, as it likely indicates a failure on our part.

Correct implementation of the lifecycle and keepGoing and isLegal methods should ensure that Games are played correctly from start to finish. Note that we do not care at this point whether any Player has scored points or defeated its opponent. We are only interested in knowing that a sequence of legal moves has been committed which leads from a legal initial configuration to a legal termination. Evaluation of the results is handled elsewhere. Our only attention to those considerations at this stage is to ensure that any information needed for future analysis is attached to the Moves submitted.


### A Note on Game State ###
One thing to remember is that the Player interface provides an update() method to keep the Player's state updated. Our API specifies that each Player must maintain its own state of the Game. This makes cheating or trying to alter the Game's state impossible. It also ensures that the Player must have some knowledge about the rules of the Game, so it is more likely they can provide a legal move. However, this puts the added responsibility on the Game to update each Player with Moves, even if it is the Move they just submitted. Game's should also maintain their own version of the state. Players who fall out of sync will start to submit invalid moves and will be dealt with by Game's security measures.


## Beginning to Write Your Game ##
The first thing you should do when writing your Game is to determine the game format and find any pre-written classes that further refine the rules of Game to the specific format. The API provides a couple pre-written common formats. If you find one that suits your game, this means less work for you.

The Game class is just a place where the actual game progresses. There is no restriction on how many classes you can use. You can write as many helper classes as you want to aid you. In fact it is recommended that you do so. Just place all the files in the same package. With that in mind, if you wrote a game program, but didn't write it for this implementation, you should be able to re-use many of the classes, and only have to fiddle with fitting it into our Game class.

## The Required Classes ##
There are a few API classes you will need extend though in order to have a functional game.

  * **Move** - You must extend Move if you want the Player to be able to tell you anything. Your subclass should provide all the necessary methods to hold the data needed for a move in your Game. Player implementations of you Game will be using this, so try to make it user-friendly.
  * **GameResult** - You must extend GameResult if you want to record any useful statistics. Since different Games will have very specific statistics, it is up to you to design a result class that will be able to aggregate the necessary statistics. Fortunately, you will be the one interacting with the statistics (see the next item).
  * **DataReader** - The API has no knowledge of what type of statistics you keep track of in your Game. Therefore, it is up to you to design a DataReader for your Game that will be able to handle the Results from your GameResult class and give a coherent report.
  * **Player** - Although creating Player implementations for your game is not required, we recommend that you do. If you create a partial implementation that has all of the necessities needed to play your Game, then you will make it easier for people writing Player implementations for your Game.

The API specifies that each Game should have two default Player implementations. One should be an artificial intelligence that generates legal moves, and the other should be an interactive implementations so that humans can play. While our API is not designed for human play, you can imagine how useful it will be to have one for testing purposes. You will probably need to write these to test your Game anyway.

# Writing a Player Implementation #
If you would like to write a Player implementation for a Game, you first need to be familiar with the interface. Have a look at the javadoc for the Player class. Once you are familiar with the interface, you need to see if the Game you are writing this for has any base Player implementations that you can extend to make your life easier. If it does, then you are in luck. Otherwise, you'll have to write the necessary code yourself.

## The Player interface ##
The Player interface is simple. You need to provide a legal Move object for the Game you are playing whenever getMove() is called. The rest of the interface stems from that.

So how do you know what the proper move to make is when getMove() is called? You aren't given any information as to the state of the Game. Well, that's where the other interface method comes into play. Player objects are responsible for maintaining their own version of the Game state. The Game will be responsible for calling your `update(Move)` method to keep your state up to date. Your implementation of update() should change your state based on the move passed to it. If you are lucky, the Game you are writing your implementation for may have provided a base Player class that handles the state for you.

**Note**: Do not try to change the state of your game from within the getMove() method. Only ever change it from update, and never call update yourself. Game will send you your own move through update. If you attempt to change it yourself in getMove(), your state could fall out of sync and you may start producing illegal moves and be disqualified.

The last method of the interface is the init method. This method is called at the start of each new game that your artificial intelligence will play. Use this method to create a fresh game state and reinitialize the necessary variables. Game will be responsible for calling this method once before each new Game is played.

## Required Classes ##
You do not need to write any other classes unless you want to write some to help with your artificial intelligence decision making. You do need to use or be familiar with a couple classes other than Player though.

The obvious one is Move. Specifically, your game's implementation of Move. You will need to instantiate a new Move object with your intentions in order to send it back to Game. You should familiarize yourself with this class. Again, if you are lucky, the base Player for your game may provide a user-friendly way of generating the proper Move.

The other classes you will need to be familiar with are the Game's classes that it uses to maintain the state. For example, if your writing a chess player, you may need to use the game's Board and Piece classes to help you represent the board.

The rest falls onto your shoulders. That's the fun part. Once you understand all of the classes you need to know, writing the decision making is the fun part.