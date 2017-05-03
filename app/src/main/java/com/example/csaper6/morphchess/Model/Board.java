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
        board[x][y] = piece;
        return board;
    }

    public boolean isOccupied(int x, int y){
        if(board[x][y] == null)
            return false;
        return true;
    }
}
