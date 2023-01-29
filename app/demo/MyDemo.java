package demo;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Card;
import structures.basic.EffectAnimation;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationType;
import utils.BasicObjectBuilders;
import utils.StaticConfFiles;

public class MyDemo {
    public static void executeDemo(ActorRef out){
        /*
        Creat chessBoard filled with Tile objects
         */
        Tile[][] chessBoard = new Tile[9][5];
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                chessBoard[x][y] = BasicObjectBuilders.loadTile(x, y);
            }
        }

        /*
        Display chessBoard with mode 0
         */
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                BasicCommands.drawTile(out, chessBoard[x][y], 0);
            }
        }

        /*
        draw human and AI avatar
         */
        Unit humanAvatar = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
        Unit aiAvatar = BasicObjectBuilders.loadUnit(StaticConfFiles.aiAvatar, 0, Unit.class);
        humanAvatar.setPositionByTile(chessBoard[1][2]);
        aiAvatar.setPositionByTile(chessBoard[7][2]);
        BasicCommands.drawUnit(out, humanAvatar, chessBoard[1][2]);
        BasicCommands.drawUnit(out, aiAvatar, chessBoard[7][2]); //chessBoard[7][2] ?

        /*
        draw cards
         */
        Card hailstone_golem = BasicObjectBuilders.loadCard(StaticConfFiles.c_hailstone_golem, 0, Card.class);
        BasicCommands.drawCard(out, hailstone_golem, 1, 0);
        BasicCommands.drawCard(out, hailstone_golem, 2, 0);
        BasicCommands.drawCard(out, hailstone_golem, 3, 0);
        BasicCommands.drawCard(out, hailstone_golem, 4, 0);
        BasicCommands.drawCard(out, hailstone_golem, 5, 0);
        BasicCommands.drawCard(out, hailstone_golem, 6, 0);

    }
}
