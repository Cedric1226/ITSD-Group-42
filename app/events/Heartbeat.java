package events;

import com.fasterxml.jackson.databind.JsonNode;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.GameState;

/**
 * In the user’s browser, the game is running in an infinite loop, where there is around a 1 second delay 
 * between each loop. Its during each loop that the UI acts on the commands that have been sent to it. A 
 * heartbeat event is fired at the end of each loop iteration. As with all events this is received by the Game 
 * Actor, which you can use to trigger game logic.
 * 
 * { 
 *   String messageType = “heartbeat”
 * }
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class Heartbeat implements EventProcessor{

	@Override
	public void processEvent(ActorRef out, GameState gameState, JsonNode message) {
		// gameOver
		if(gameState.humanPlayer.getHealth()<=0 || gameState.aiPlayer.getHealth()<=0){

			if(gameState.humanPlayer.getHealth()<=0){
				BasicCommands.addPlayer1Notification(out, "Lose!", 2);
			}
			else {
				BasicCommands.addPlayer1Notification(out, "Victory!", 2);
			}

			try {Thread.sleep(2000000);} catch (InterruptedException e) {e.printStackTrace();}
		}


	}

}
