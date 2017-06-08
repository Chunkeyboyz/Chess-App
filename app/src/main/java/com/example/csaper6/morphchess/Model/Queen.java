package com.example.csaper6.morphchess.Model;

import android.widget.ImageView;

/**
 * Created by csaper6 on 4/19/17.
 */
public class Queen extends ChessPiece {

    public Queen(boolean color, int x, int y,ImageView image) {
        super( color,x,y, image);
    }

    public boolean isMovePossible(int x1, int y1, Board board){
        if(checkAxes(x1, y1, board) || checkDiagonal(x1, y1, board)){
            return true;
        }
        return false;
    }

}
