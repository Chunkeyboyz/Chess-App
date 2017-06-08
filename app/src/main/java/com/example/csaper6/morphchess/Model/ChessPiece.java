package com.example.csaper6.morphchess.Model;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by csaper6 on 4/19/17.
 */
public abstract class ChessPiece {
    boolean color;
    int x;
    int y;
    ImageView image;


    public ChessPiece(boolean color, int x, int y, ImageView image) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public boolean getColor() {
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

    public abstract boolean isMovePossible(int x1, int y1, Board board); //abstract method that lets pieces use their personal movement styles

    public void move(int x, int y, Board board) {
        board.removePiece(getX(), getY());
        if (board.getPiece(x, y) != null) {
            ((FrameLayout)board.getPiece(x, y).getImage().getParent()).removeView(board.getPiece(x, y).getImage());
            board.removePiece(x, y);
        }
        board.setPiece(x, y, this);

//        board.setPiece(x, y, piece);
        this.setX(x);
        this.setY(y);
        if(board.getPiece(x,y) instanceof Pawn){
            if(!((Pawn) board.getPiece(x,y)).hasMoved)
                ((Pawn) board.getPiece(x,y)).setHasMoved(true);
        }
        King k1, k2; // k1 is white king, k2 is black king
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getPiece(i, j) instanceof King) {
                    if (board.getPiece(i, j).getColor())
                        k1 = (King)board.getPiece(i, j);
                    else
                        k2 = (King)board.getPiece(i, j);
                }
            }
        }

    }

//    public boolean movingStopsCheck(int x, int y, ChessPiece piece, Board board) { //checks to see if a piece can move to block the kings check. Assumes already checked
//        if (isMovePossible(x, y, board)) {
//            board.setPiece(x, y, piece); //TODO: make invis if possible
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    if (board.isOccupied(i, j) && board.getPiece(i, j) instanceof King) {
//                        if (((King) board.getPiece(i, j)).isInCheck(board)) { //if the move doesnt block check,
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }

    public boolean checkDiagonal(int x1, int y1, Board board) {
        if (x1 > 7 || x1 < 0 || y1 > 7 || y1 < 0) { //if the spot we want to check is out of bounds, return false
            return false;
        }
        if (board.isOccupied(x1, y1)) {
            if (board.getPiece(x1, y1).getColor() == this.getColor()) //end position has a piece that is the same color as the initial piece
                return false;
        }
        double slope = (double) (y1 - y) / (x1 - x);
        int xsign = (int) Math.signum(x1 - x); //1 if positive, -1 if negative
        int ysign = (int) Math.signum(y1 - y);
        if (Math.abs(slope) != 1) {
            // is not a diagonal
            return false;
        }
        for (int i = 1; i < Math.abs(x - x1); i++) {
            if (board.isOccupied(x + (i * xsign), y + (i * ysign))) { //if it occupied on the way to the diagonal then is it not possible
                return false;
            }
        }
        return true;

    } //check to see if diagonal movement is possible to a location. Used by: King, Queen, Bishop

    public boolean checkAxes(int x1, int y1, Board board) {
        if (x1 > 7 || x1 < 0 || y1 > 7 || y1 < 0) {
            return false;
        }
        if (board.isOccupied(x1, y1)) {
            if (board.getPiece(x1, y1).getColor() == board.getPiece(x, y).getColor()) //check if end position has a piece that is the same color as the initial piece
                return false;
        }
        if ((y == y1 && x != x1) || (x == x1 && y != y1)) {
            for (int i = 1; i < Math.max(Math.abs(x - x1), Math.abs(y - y1)); i++) {
                if (y == y1) {
                    if (x1 > x) {
                        if (board.isOccupied(x + i, y)) {
                            Log.d("TAG", "checkAxes: bo");
                            return false;
                        }
                    } else if (board.isOccupied(x - i, y)) {
                        Log.d("TAG", "checkAxes: boom11111");
                        return false;
                    }

                } else {
                    if (y1 > y) {
                        if (board.isOccupied(x, y + i)) {
                            Log.d("TAG", "checkAxes: boom");
                            return false;
                        }
                    } else if (board.isOccupied(x, y - i)) {
                        Log.d("TAG", "checkAxes: boom11");
                        return false;
                    }
                }
            }
            return true;
        }
        return false;

    } //check to see if axes movement is possible to a location. Used by: King, Queen, Rook

    public boolean checkLshape(int x1, int y1, Board board) {
        if (x1 > 7 || x1 < 0 || y1 > 7 || y1 < 0) {
            return false;
        }
        if ((Math.abs(y1 - y) == 1 && Math.abs(x1 - x) == 2) || (Math.abs(y1 - y) == 2 && Math.abs(x1 - x) == 1)) { // check all L positions
            if (board.isOccupied(x1, y1)) {
                if (board.getPiece(x1, y1).getColor() == this.getColor()) //check if end position has a piece that is the same color as the initial piece
                    return false;
                else
                    return true;
            } else {
                return true;
            }
        }
        return false;
    } //check to see if L movement is possible to a location. Used by: Knight


}