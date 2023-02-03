package structures;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import utils.OrderedCardLoader;

import java.util.List;

public class Hand {
    public List<Card> playerDeck;

    public int cardIndex = 3;

    public int[] handCard = {0,1,2,-1,-1,-1}; // handState keeps card ID

    public int[] cardState = new int[6];

    public Hand(int mode){
        if(mode==0){
            playerDeck = OrderedCardLoader.getPlayer1Cards();
        }else {
            playerDeck = OrderedCardLoader.getPlayer2Cards();
        }
    }

    public static void initDisplayHand(ActorRef out, GameState gameState){
        for(int i = 0; i<gameState.myHand.handCard.length; i++){
            if(gameState.myHand.handCard[i] != -1){
                BasicCommands.drawCard(out, gameState.myHand.playerDeck.get(gameState.myHand.handCard[i]), i+1, 0);
            }
        }
    }

    public static void highlightDisplayHand(ActorRef out, GameState gameState, int handPosition){
        if(gameState.myHand.playerDeck.get(gameState.myHand.handCard[handPosition-1]).getManacost() <= gameState.humanPlayer.getMana()){
            BasicCommands.drawCard(out, gameState.myHand.playerDeck.get(gameState.myHand.handCard[handPosition-1]), handPosition, 1);
            gameState.myHand.cardState[handPosition-1] = 1;
        }
    }

    public void updateHandCard(){
        for(int i=0; i<handCard.length; i++){
            if(handCard[i]==-1){
                handCard[i] = cardIndex;
                cardIndex +=1;
                break;
            }else {
                cardIndex = cardIndex;
            }
        }
    }
    public void freshCardState(){
        for(int i=0; i<cardState.length;i++){
            cardState[i] = 0;
        }
    }


}
