package com.example.csaper6.morphchess.Model;

import android.widget.ImageView;

/**
 * Created by csaper6 on 4/19/17.
 */
public class Knight extends ChessPiece {
    public Knight(boolean color, int x, int y, ImageView image) {
        super(color, x, y, image);
    }

    @Override
    public boolean isMovePossible(int x1, int y1, Board board) {
        if(checkLshape(x1,y1,board)){
            return true;
        }
        return false;
    }




}
