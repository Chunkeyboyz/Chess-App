package com.example.csaper6.morphchess.Model;

/**
 * Created by csaper6 on 4/19/17.
 */
public class Queen extends ChessPiece {

    public Queen(Board board, boolean color, int x, int y) {
        super(board, color,x,y);
    }

    @Override
    public void move(int x, int y) {
        ChessPiece piece = board.getPiece(getX(),getY());
        board.setPiece(x,y,piece);
        board.removePiece(getX(),getY());
    }




}
