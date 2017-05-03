package com.example.csaper6.morphchess.Model;

/**
 * Created by csaper6 on 4/19/17.
 */
public abstract class ChessPiece {
    boolean color;
    int x;
    int y;
    Board board;


    public ChessPiece(Board board, boolean color, int x, int y) {
        this.board = board;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void move(int x, int y); // x is current pos, x1 is pos to move to

    public boolean checkDiagonal(int x1, int y1, Board board) {
        if (board.isOccupied(x1,y1)) {
            if (board.getPiece(x1, y1).isColor() == board.getPiece(x, y).isColor()) //end position has a piece that is the same color as the initial piece
                return false;
        }
        double slope = (double) (y1 - y) / (x1 - x);
        int xs = (int) Math.signum(x1 - x); //1 if positive, -1 if negative
        int ys = (int) Math.signum(y1 - y);
        if (Math.abs(slope) != 1) {
            // is not a diagonal
            return false;
        }
        for (int i = 1; i <= Math.abs(x-x1); i++) {
            if (board.isOccupied(x + (i * xs), y + (i * ys))){
                return false;
            }
        }
        return true;

    }

    public boolean checkAxes(int x1, int y1, Board board){
        if (board.isOccupied(x1,y1)) {
            if (board.getPiece(x1, y1).isColor() == board.getPiece(x, y).isColor()) //end position has a piece that is the same color as the initial piece
                return false;
        }
        double slope = (double) (y1 - y) / (x1 - x);
        int xs = (int) Math.signum(x1 - x); //1 if positive, -1 if negative
        int ys = (int) Math.signum(y1 - y);
        if (Math.abs(slope) != 0) {
            // is not a diagonal
            return false;
        }
        for (int i = 1; i <= Math.abs(x-x1); i++) {
            if (board.isOccupied(x + (i * xs), y + (i * ys))){
                return false;
            }
        }
        return true;

    }

}