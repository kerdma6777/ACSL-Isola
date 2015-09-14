# ACSL-Isola
This program was written for Contest #1 of the Senior Division of the [American Computer Science League](http://www.acsl.org/) 2014-2015 competition.  It was written in Java.  As per competition rules, I had three days to complete this program.  

#Program Description
The purpose of this program is to calculate the best move a player can make when playing a hypothetical board game called Isola.  The board game is played with two players and each player has one marker, either an X or a cross (+).  In this board game, there is a board with 49 grid squares.  Each square contains a removable numbered tile.  The tiles are always configured the same way with tile 1 in the lower left hand corner and tile 49 in the upper right hand corner.  Tiles increment from left to right and from the bottom to the top.  

When it is a player’s turn, he/she moves his/her marker on a liner path in any direction (horizontally, vertically, or diagonally) until the marker is at the boundary of the board or is adjacent to the other player’s piece.  Tiles along the chosen path are removed.  Paths may not cross tiles that have already been removed.

This program, given the positions of the markers and the tile numbers of removed tiles, outputs the path (in any order of tiles) that will result in the X marker being adjacent to the cross (+) marker.  If there is no such path, “NONE” is outputted.

#Input
It is unknown how many lines of input there will be.  The first line is the tile number of the cross marker.  The second line is the tile number of the X marker.  The following lines are the tile numbers of the removed tiles.  The input will end when 0 is entered. 

 #Sample 
Sample Input (each comma represents a hard enter):

46, 4, 45, 47, 38, 40, 0

Sample Output:

39 32 25 18 11
