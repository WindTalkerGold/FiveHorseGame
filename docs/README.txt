Five Horse Game is a traditional chessboard game in my hometown.
Two players play this game, each holds 5 chessmen, represented by clam shells.
One has the 5 clam shells on and another has 5 clam shells off.

The chessboard 5*5 with diagonal (see in chessboard.png). The advanced mode has another 2*2 zone as an addition.
In the initial version we will not implement it. But we need to make our code flexible for it.

Chessmen sit on each focus point of the vertical boundaries. Each time it can go along some line.
Chessmen will be flipped if one is clamped by two other, or two is ~~

Will implement it in three steps:
1) enable the chessboard. Each player will be assumed as human
2) enable AI on normal mode
3) enable the advanced mode with 2*2 additional zone, and AI on it

All code will be in package heavenchess, with tests.

Packages of heavenchess, heavenchess.board, heavenchess.movements are basic classes and interfaces.
All new features should be implemented by implementing existing interfaces, and changing the factories.