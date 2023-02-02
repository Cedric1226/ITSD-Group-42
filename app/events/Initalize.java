package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import demo.CheckMoveLogic;
import demo.CommandDemo;
import demo.MyDemo;
import structures.Board;
import structures.GameState;

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

		gameState.myBoard.setUnitState(gameState.humanAvatar.getPosition(),1);
		gameState.myBoard.setUnitState(gameState.aiAvatar.getPosition(),2);

		BasicCommands.drawUnit(out, gameState.humanAvatar, gameState.myBoard.chessBoard[1][2]);
		BasicCommands.drawUnit(out, gameState.aiAvatar, gameState.myBoard.chessBoard[7][2]);

		// initialize player's hand
		BasicCommands.drawCard(out, gameState.hailstone_golem, 1, 0);
		BasicCommands.drawCard(out, gameState.hailstone_golem, 2, 0);
		BasicCommands.drawCard(out, gameState.hailstone_golem, 3, 0);

		// initialize player's mana and health
		BasicCommands.setPlayer1Health(out, gameState.humanPlayer);
		BasicCommands.setPlayer2Health(out, gameState.aiPlayer);
		BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);
		BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);


		//MyDemo.executeDemo(out);
	}

}


