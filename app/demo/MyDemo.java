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

        Unit humanAvatar = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
        humanAvatar.setPositionByTile(chessBoard[1][2]);
        BasicCommands.setUnitAttack(out, humanAvatar, 20);
        BasicCommands.drawUnit(out, humanAvatar, chessBoard[1][2]);

         */

        BasicCommands.addPlayer1Notification(out, "drawUnit", 2);
        Unit unit = BasicObjectBuilders.loadUnit(StaticConfFiles.humanAvatar, 0, Unit.class);
        unit.setPositionByTile(chessBoard[1][2]);
        BasicCommands.drawUnit(out, unit, chessBoard[1][2]);
        try {Thread.sleep(44);} catch (InterruptedException e) {e.printStackTrace();}

        // setUnitAttack
        BasicCommands.addPlayer1Notification(out, "setUnitAttack", 2);
        BasicCommands.setUnitAttack(out, unit, 2);



    }
}
