package structures;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Position;
import structures.basic.Tile;
import utils.BasicObjectBuilders;

public class Board {
    public Tile[][] chessBoard = new Tile[9][5];
    public int[][] unitState = new int[9][5];
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

    // used to highlight the board according to boardState
    public static void displayBoard(ActorRef out, Board myBoard, int[][] boardState){
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                if(boardState[x][y]!=0){
                    BasicCommands.drawTile(out, myBoard.chessBoard[x][y], boardState[x][y]);
                }
                else {
                    continue;
                }
            }
        }
    }

    // used to fresh highlighted board to mode 0
    public static void freshDisplayBoard(ActorRef out, Board myBoard, int[][] boardState){
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                if(boardState[x][y]!=0){
                    BasicCommands.drawTile(out, myBoard.chessBoard[x][y], 0);
                }
                else {
                    continue;
                }
            }
        }
    }

    // used to update boardState according to input (x, y)
    public void calBoardState(int x, int y, int mode){
        for (int i=x-1; i<=x+1; i++){
            for(int j=y-1; j<=y+1; j++){
                if(i>=0 & i<=8 & j>=0 & j<=4){
                    boardState[i][j] = mode;
                }
                else {
                    continue;
                }
            }
        }
        if(y-2>=0){
            boardState[x][y-2] = mode;
        }
        if(y+2<=4){
            boardState[x][y+2] = mode;
        }
        if(x-2>=0){
            boardState[x-2][y] = mode;
        }
        if(x+2<=8){
            boardState[x+2][y] = mode;
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

    //
    public void setUnitState(Position position, int mode){
        /*
        mode 0 indicates there is no unit in (x, y)
        mode 1 indicates there is human's unit in (x, y)
        mode 2 indicates there is AI's unit in (x, y)
         */
        int x = position.getTilex();
        int y = position.getTiley();
        unitState[x][y] = mode;
    }


}
