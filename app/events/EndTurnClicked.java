package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;

/**
 * Indicates that the user has clicked an object on the game canvas, in this case
 * the end-turn button.
 * 
 * { 
 *   messageType = “endTurnClicked”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class EndTurnClicked implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {

		gameState.tileSelected = false;

		gameState.turn+=1;
		gameState.humanPlayer.setMana(gameState.turn+1);
		gameState.aiPlayer.setMana(gameState.turn+1);

		BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);
		BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);
	}

}
