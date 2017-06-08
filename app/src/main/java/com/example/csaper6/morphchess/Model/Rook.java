package com.example.csaper6.morphchess.Model;

import android.widget.ImageView;

/**
 * Created by csaper6 on 4/19/17.
 */
public class Rook extends ChessPiece {
    public boolean hasMoved;


    public Rook(boolean color, int x, int y, ImageView image) {super(color,x,y, image);
        hasMoved = false;
    }
    public boolean isMovePossible(int x1, int y1, Board board){
        if(checkAxes(x1, y1, board)){
            return true;
        }
        return false;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
