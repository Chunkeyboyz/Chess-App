package com.example.csaper6.morphchess.Model;

import android.widget.ImageView;

/**
 * Created by csaper6 on 5/11/17.
 */
public class PassantPiece extends ChessPiece{
    public Pawn pawn;
    public PassantPiece(boolean color, int x, int y, ImageView image, Pawn pawn) {
        super(color, x, y, image);
        this.pawn = pawn;
    }

    @Override
    public boolean isMovePossible(int x1, int y1, Board board) {
        return false;
    }

    public int getPawnX(){
        return pawn.getX();
    }

    public int getPawnY(){
        return pawn.getY();
    }



}
