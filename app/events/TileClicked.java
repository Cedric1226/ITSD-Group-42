package events;


import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.Board;
import structures.GameState;
import structures.Hand;

/**
 * Indicates that the user has clicked an object on the game canvas, in this case a tile.
 * The event returns the x (horizontal) and y (vertical) indices of the tile that was
 * clicked. Tile indices start at 1.
 * 
 * { 
 *   messageType = “tileClicked”
 *   tilex = <x index of the tile>
 *   tiley = <y index of the tile>
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class TileClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {

		Hand.initDisplayHand(out, gameState);

		int tilex = message.get("tilex").asInt();
		int tiley = message.get("tiley").asInt();

		/*
		if (gameState.something == true) {
			// do some logic
		}

		 */
		if(!gameState.tileSelected){

			gameState.tileSelected = true;
			gameState.myBoard.lastSelectedX = tilex;
			gameState.myBoard.lastSelectedY = tiley;

			if(gameState.myBoard.unitState[tilex][tiley]==1){
				gameState.myBoard.calBoardState(tilex, tiley,1);

				Board.displayBoard(out,gameState.myBoard,gameState.myBoard.boardState);
			}
		}
		else {
			if (gameState.myBoard.unitState[tilex][tiley] == 0 && gameState.myBoard.boardState[tilex][tiley]==1) {

				Board.freshDisplayBoard(out,gameState.myBoard,gameState.myBoard.boardState);
				gameState.myBoard.freshBoardState();

				gameState.myBoard.setUnitState(gameState.humanAvatar.getPosition(),0);
				BasicCommands.moveUnitToTile(out, gameState.humanAvatar, gameState.myBoard.chessBoard[tilex][tiley]);
				gameState.humanAvatar.setPositionByTile(gameState.myBoard.chessBoard[tilex][tiley]);
				gameState.myBoard.setUnitState(gameState.humanAvatar.getPosition(),1);

				gameState.tileSelected = false;
			}
			else if(gameState.myBoard.unitState[tilex][tiley] == 1){
				gameState.tileSelected = true;
				gameState.myBoard.lastSelectedX = tilex;
				gameState.myBoard.lastSelectedY = tiley;

				gameState.myBoard.freshBoardState();
				gameState.myBoard.calBoardState(tilex, tiley,1);

				Board.displayBoard(out,gameState.myBoard,gameState.myBoard.boardState);

			}
			else if(gameState.myBoard.unitState[tilex][tiley] == 0 && gameState.myBoard.boardState[tilex][tiley]==0){
				Board.freshDisplayBoard(out,gameState.myBoard,gameState.myBoard.boardState);
				gameState.myBoard.freshBoardState();
				gameState.tileSelected = false;
			}
		}

	}

}
