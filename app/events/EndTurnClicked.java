package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;
import structures.Hand;

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
		gameState.turn+=1;

		gameState.myHand.updateHandCard();
		Hand.initDisplayHand(out, gameState);
		gameState.myHand.freshCardState();

		gameState.tileSelected = false;


		gameState.humanPlayer.setMana(gameState.turn+1);
		gameState.aiPlayer.setMana(gameState.turn+1);

		BasicCommands.setPlayer1Mana(out, gameState.humanPlayer);
		BasicCommands.setPlayer2Mana(out, gameState.aiPlayer);
	}

}
