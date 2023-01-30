package structures;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Tile;
import utils.BasicObjectBuilders;

public class Board {
    public Tile[][] chessBoard = new Tile[9][5];
    public int[][] boardUnit = new int[9][5];
    public int[][] boardState = new int[9][5];

    public Board(){
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                chessBoard[x][y] = BasicObjectBuilders.loadTile(x, y);
            }
        }
    }

    // used to display initial board
    public static void initDisplayBoard(ActorRef out, Board myBoard, int mode){
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                BasicCommands.drawTile(out, myBoard.chessBoard[x][y], mode);
            }
        }
    }

    // used to refresh boardState to 0
    public void freshBoardState(){
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                boardState[x][y] = 0;
            }
        }
    }
}
