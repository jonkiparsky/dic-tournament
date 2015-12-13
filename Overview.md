## Introduction ##

The terms here will suggest classes, but this is not a literal summary of class responsibilities or a spec.

This is a provisional document and should be edited into shape.


## Overview ##
  1. A tournament is a set of matches between players, such that each combination of players plays a match. Players must play against themselves, order is not significant.
  1. A match is a set of games between or among a particular set of players.
  1. A game is a set of moves conforming to a set of rules such, beginning from an initial state and continuing to a termination according to the rules.
  1. A state (or "board") is either the initial state of the game, or the consequence of some move. In pure games of strategy, the state is a derived object, since it is always predictable from the rules of the game and the list of moves leading to it. In games with an element of randomness, the state is not purely derived from the list of moves.
    * This is a decision point, which I do not mean to impose as final.
  1. A move is a legal transformation of the state of the game. "Legal" means conforming all rules of the game.
  1. A Player is an object which can serve as a source of moves. This can include alorithmic players, random move generators, or human players.
  1. A game aggregates, minimally, a list of moves and states. This is sufficient to recreate any needed information. Games may, optionally, aggregate move-by-move statistics
  1. A match aggregates, at minimum, a list of games. This list of games is sufficient to produce any required derived statistics. Matches may, optionally, aggregate game-by-game statistics. Matches may also provide pass-through access to any statistics gathered by games.
  1. Tournaments aggregate a list of Matches. Tournaments may optionally provide match-by-match statistics, and access to statistics gathered by matches.
  1. ? A player aggregates a list of matches in which that player has participated.


## Discussion ##

(edit at will above this line, discuss at will below it)

rs: Are you saying a Game itself is the object to hold on to the Moves played? Are we passing Game to Match?

jpk: yes, that's my thought. A game is a playing through of a set of rules by a set of players. Game, in the sense of "Are you going to the game?", not "Baseball is my favorite game".





---

begin rs:

What if the system looked like this:

  1. Player interacts with the state to come up with a specified Move.
  1. The state then generates a Move based on the player's decision, then executes it.
    * Note: The Move should be generated such that if it were passed back into the state, it would produce the exact same results each time. That means based on your idea about randomness, the Move may also need to contain the seed, or the pre-obtained random result.
  1. The state signs the Player's name to the Move and verifies that it's legal (which the interface the player uses can do BEFORE generating a Move so that we know all instantiated Moves are legal) all under the hood. Once a Move has all the data it needs filled in the Move can be passed to the Game, which will keep a record of it and proceed to the next turn. Moves include:
    * turn the move was taken
    * who made the Move
    * whether the Move requires the player to take another turn after this
    * whether the Move eliminates another player
    * how long did the player take to make their move? (interesting one! for humans, it would show how long they needed to think, but for AI it would show their efficiency)
  1. The Game can then pass back a set of Moves and the Match will record a set of Games.

We can then write a StatisticManager or something that has all the required methods to derive stats from the Moves. For now, if that doesn't take too long that can suffice, otherwise we can have the StatisticManager compile the stats into some form.

This would also allow for some form of undo/redo mechanic for human implementations and for replays to be saved.

How does that sound?

(end rs)

---


jpk:

Some good ideas there, but I worry a bit about over-complicating it. For now can we say that a minimal move is the move itself and the identity of the Player making it, and allow as how the Game might tack on some other information later?

Also with the statistics - since I know that storing the lists of Moves, Games, and Matches will in principle allow total recreation of the games - everything that the games know and the players know will be stored and organized - I want to stay agnostic on when we'll calculate particular details.
It may become obvious that certain details should be collected at certain points, but if we try to lock that stuff in now, we'll maybe lock out other good ideas that will become more obvious as we build it.


As for your model of the move, I'm not sure, I'll have to internalize it. My immediate impression is: a Player should be able to generate a legal move without verifying it. If they can't, they should lose the game. An AI that can generate a wrong move is either so stupid it should be drummed out of the game, or so smart that we should worry about whether it's legal to turn it off.

I have to wonder what the interaction is between player and state. **Maybe we should just give the player the previous player's move and let them maintain their own state?** That would model more the chess-by-mail scenario. That would also insulate the board from jiggery-pokery of the sort we got tangled up in over the last few days.

---

rs:
The chess-by-mail idea brings up a good point. On one hand, we want the player to be able to know what the board looks like, but passing an entire state could be bulky if say, we expanded to sending moves over a network. Much better to just send a Move. But the player shouldn't have to worry about updating his own Board, he should just be able to call getState() for an up-to-date version of the game board, where we take the liberty of updating it for him (based on whatever Move) before his makeMove() method is called.

So I'm thinking a Player class should maintain a Board, but the one thing that I'm concerned about is if we have public methods to interact with that board, the player could override them or attempt to use them to submit his Move (thinking that might be what he has to do), and then we might try to submit the player's Move, and all of a sudden the Board is out of sync.