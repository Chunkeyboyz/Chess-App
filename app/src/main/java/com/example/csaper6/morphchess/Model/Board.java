package com.example.csaper6.morphchess.Model;

import android.support.annotation.Nullable;

/**
 * Created by csaper6 on 4/27/17.
 */
public class Board {
    //should be nulls at where pieces aren't
    ChessPiece board[][] = new ChessPiece[8][8];
    public Board(ChessPiece[][] board) {
        this.board = board;
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }

    @Nullable
    public ChessPiece getPiece(int x, int y){
        return board[x][y];
    }

    public ChessPiece[][] removePiece(int x, int y){
        board[x][y] = null;
        return board;
    }

    public ChessPiece[][] setPiece(int x, int y, ChessPiece piece){
//        Log.d("ASDD", "setPiece: " + x + ", " + y + " : " + board[x][y]);
//        if(board[x][y] != null){
//            Log.d("ajfn", "setPiece: ;ojnga;ojb");
//            ((FrameLayout)board[x][y].getImage().getParent()).removeView(board[x][y].getImage());
//        }
//        board[x][y] = null;
        board[x][y] = piece;
        return board;
    }

    public boolean isOccupied(int x, int y){
        if(board[x][y] == null)
            return false;
        return true;
    } //sees if space is occupied, returns false if null

    public ChessPiece[][] switchPiece(int x, int y, int x1, int y1){
        ChessPiece piece = board[x1][y1];
        board[x1][y1] = board[x][y];
        board[x][y] = piece;
        return board;
    }
//    public boolean isInCheck(ChessPiece king) {
//        for (int i = 0; i <= 7; i++)
//            for (int j = 0; j <= 7; j++) {
//                if(board[i][j].isMovePossible(king.getX(),king.getY(),
//            }
//        return false;
//    }
}
