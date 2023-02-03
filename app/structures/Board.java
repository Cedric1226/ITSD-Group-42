package structures;

import akka.actor.ActorRef;
import commands.BasicCommands;
import structures.basic.Position;
import structures.basic.Tile;
import utils.BasicObjectBuilders;

public class Board {
    public Tile[][] chessBoard = new Tile[9][5];
    public int[][] unitIDs = new int[9][5];
    public int[][] boardState = new int[9][5];

    public int lastSelectedX;

    public int lastSelectedY;

    public Board(){
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                chessBoard[x][y] = BasicObjectBuilders.loadTile(x, y);
            }
        }

        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                unitIDs[x][y] = -1;
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
        for (int i=x-2; i<=x+2; i++){
            for(int j=y-2; j<=y+2; j++){
                if(i>=0 & i<=8 & j>=0 & j<=4 & Math.abs(i-x)+Math.abs(j-y)<=2){
                    if(unitIDs[i][j] == -1){
                        boardState[i][j] = mode;
                    }
                }
            }
        }
        boardState[x][y] = 0;

        for (int i=x-1; i<=x+1; i++){
            for(int j=y-1; j<=y+1; j++){
                if(i>=0 & i<=8 & j>=0 & j<=4){
                    if(unitIDs[i][j] >19){
                        boardState[i][j] = 2;
                    }
                }
            }
        }



    }

    // used to update boardState to realize card using and attack
    public void calBoardState(int x, int y){
        for (int i=x-1; i<=x+1; i++){
            for(int j=y-1; j<=y+1; j++){
                if(i>=0 & i<=8 & j>=0 & j<=4){
                    if(unitIDs[i][j] == 0) {
                        boardState[i][j] = 1;
                    }
                }
            }
        }
    }

    // used to refresh boardState to 0
    public void freshBoardState(){
        for(int x=0;x<=8;x++){
            for(int y=0;y<=4;y++){
                boardState[x][y] = -1;
            }
        }
    }

    //
    public void setUnitIDs(Position position, int unitID){
        /*
        ID 4 indicates human's avatar
        ID 32 indicates AI's avatar
        ID -1 indicates there is no unit in (x, y)
        ID 0-19 indicates there is human's unit in (x, y)
        ID 20-39 indicates there is AI's unit in (x, y)
         */
        int x = position.getTilex();
        int y = position.getTiley();
        this.unitIDs[x][y] = unitID;
    }


}
