package structures;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import utils.OrderedCardLoader;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public List<Card> playerDeck;

    public int cardIndex = 3;

    public int[] handState = {0,1,2,-1,-1,-1}; // handState keeps card ID


    public Hand(int mode){
        if(mode==0){
            playerDeck = OrderedCardLoader.getPlayer1Cards();
        }else {
            playerDeck = OrderedCardLoader.getPlayer2Cards();
        }
    }

    public static void initDisplayHand(ActorRef out, GameState gameState){
        for(int i=0; i<gameState.myHand.handState.length; i++){
            if(gameState.myHand.handState[i] != -1){
                BasicCommands.drawCard(out, gameState.myHand.playerDeck.get(gameState.myHand.handState[i]), i+1, 0);
            }
        }
    }


    public static void highlightDisplayHand(ActorRef out, GameState gameState, int handPosition){
        if(gameState.myHand.playerDeck.get(gameState.myHand.handState[handPosition]).getManacost() <= gameState.humanPlayer.getMana()){
            BasicCommands.drawCard(out, gameState.myHand.playerDeck.get(gameState.myHand.handState[handPosition]), handPosition, 0);
        }
    }


}
