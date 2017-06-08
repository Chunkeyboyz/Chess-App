package com.example.csaper6.morphchess.Model;

import android.widget.ImageView;

/**
 * Created by csaper6 on 5/25/17.
 */
public class BlankPiece extends ChessPiece {
    public BlankPiece(boolean color, int x, int y, ImageView image) {
        super(color, x, y, image);
    }

    @Override
    public boolean isMovePossible(int x1, int y1, Board board) {
        return false;
    }

}
