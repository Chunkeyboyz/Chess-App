package com.example.csaper6.morphchess.Model;

import android.widget.ImageView;

/**
 * Created by csaper6 on 4/19/17.
 */
public class Pawn extends ChessPiece {
    public boolean hasMoved;

    public Pawn(boolean color, int x, int y, ImageView image) {
        super(color, x, y, image);
        hasMoved = false;

    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isMovePossible(int x1, int y1, Board board) {
        if (board.isOccupied(x1, y1)) {
            if (board.getPiece(x1, y1).getColor() == color) //if the piece the pawn is trying to move on top of is friendly return false
                return false;
            if (color && y1 - y == 1 && x1 - x == 1) {
                return true;//if player is playing black, then check the diagonals to the top left and right for enemy pieces
            }
            if (color && y1 - y == 1 && x1 - x == -1) {
                return true;//if player is playing black, then check the diagonals to the top left and right for enemy pieces
            }

            if (!color && y - y1 == 1 && x1 - x == -1) {
                return true;
            }

            if (!color && y - y1 == 1 & x1 - x == 1) {
                return true;
            }  //if player is playing white, then check the diagonals to the bottom left and right for enemy pieces
        }


        if (!hasMoved && color && y1 - y == 2 && x == x1) {//if pawn hasnt moved, 2 spots are available to move
            return true;
        }
        if (!hasMoved && !color && y - y1 == 2 && x == x1) {
            return true;
        }
        if (color && y1 - y == 1 && x == x1)
            return true;
        if (!color && y - y1 == 1 && x == x1)
            return true;
        return false; //if the move is impossible return false
    }

}

