package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.Board;
import structures.GameState;
import structures.Hand;

/**
 * Indicates that both the core game loop in the browser is starting, meaning
 * that it is ready to recieve commands from the back-end.
 * 
 * { 
 *   messageType = “initalize”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Initalize implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {

		gameState.gameInitalised = true;
		
		gameState.something = true;


		// initialize board
		Board.initDisplayBoard(out,gameState.myBoard,0);

		// initialize player and AI avatar
		gameState.humanAvatar.setPositionByTile(gameState.myBoard.chessBoard[1][2]);
		gameState.aiAvatar.setPositionByTile(gameState.myBoard.chessBoard[7][2]);

		gameState.myBoard.setUnitIDs(gameState.humanAvatar.getPosition(),4);
		gameState.myBoard.setUnitIDs(gameState.aiAvatar.getPosition(),32);

		BasicCommands.drawUnit(out, gameState.humanAvatar, gameState.myBoard.chessBoard[1][2]);
		BasicCommands.drawUnit(out, gameState.aiAvatar, gameState.myBoard.chessBoard[7][2]);

		try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}			// I don't know why but Thread.sleep is compulsory here
		BasicCommands.setUnitAttack(out, gameState.humanAvatar, 2);
		BasicCommands.setUnitHealth(out, gameState.humanAvatar, gameState.humanPlayer.getHealth());
		BasicCommands.setUnitAttack(out, gameState.aiAvatar, 2);
		BasicCommands.setUnitHealth(out, gameState.aiAvatar, gameState.aiPlayer.getHealth());

		// initialize player's hand
		Hand.initDisplayHand(out, gameState);


		// initialize player's mana and health
		BasicCommands.setPlayer1Health(out, gameState.humanPlayer);
		BasicCommands.setPlayer2Health(out, gameState.aiPlayer);
		BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);
		BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);


	}

}


