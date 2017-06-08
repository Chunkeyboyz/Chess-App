package com.example.csaper6.morphchess.Model;

import android.widget.ImageView;

/**
 * Created by csaper6 on 4/19/17.
 */
public class King extends ChessPiece {
    public boolean hasMoved;
    public King(boolean color, int x, int y,ImageView image) {
        super(color, x, y, image);
        hasMoved = false;
    }


    @Override
    public boolean isMovePossible(int x1, int y1, Board board) {
//        if (!hasMoved && x1 == 3) {
//            //TODO: figrure out which rook player is trying to castle with
//            boolean leftRook = true;
//        }
//        else{
//            boolean rightRook = true;
//        }
        if (Math.abs(x - x1) <= 1 && Math.abs(y - y1) <= 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isInCheck(int xPos, int yPos, Board board) {
        if (xPos > 7 || xPos < 0 || yPos > 7 || yPos < 0) { //if location is out of bounds, return false
            return false;
        }
        for (int i = 0; i <= 7; i++)
            for (int j = 0; j <= 7; j++) {
                if (board.isOccupied(i,j) && board.getPiece(x,y).getColor() != board.getPiece(i,j).getColor()){
                    if(board.getPiece(i,j).isMovePossible(x,y,board)){
                        return true;
                    }
                    //check every board location to see if there is a piece that can move to xPos, yPos)
                }
            }
        return false;
    } //checks every piece to see if they can move to xPos, yPos


    public boolean isInCheck(Board board) {
        return isInCheck(x, y, board); //used to check check with the Kings location
    } //uses isInCheck to check for the kings location

    public boolean isInCheckmate(Board board) {
        int counter = 0;
        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++) {
                if (isInCheck(x + i, y + j, board)) { //check the 9 location next to the king to make sure all of them can either
                    counter++;                //can be moved to by enemy pieces or blocked by friendly pieces
                    break;                      //each time true, counter++
                } else if (board.isOccupied(x + i, y + j) && board.getPiece(x + i, y + j).getColor() != board.getPiece(x, y).getColor())
                    counter++;
            }
        if (x == 0 || y == 0 || x == 7 || y == 7) { //if king is on an edge, add 3 to counter to account for the 3 spots next to the king that are out of bounds
            counter += 3;
        }
        if ((x == 0 && y == 0) || (x == 7 && y == 0) || (x == 0 && y == 7) || (x == 0 && y == 7)) {
            counter += 5;   //if king is in a corner, add 5 to counter to account for the 5 spots next to the king that are out of bounds
        }
        if (counter == 9) {
            return true;
        }
        return false;
    } //uses isInCheck to see if all the spaces around the king can be moved // TODO: 5/9/17

    public boolean checkCastling(int xPos, Rook rook, Board board) {
        if (xPos == 3) {
            for (int i = 0; i <= 3; i++) {
                if (isInCheck(xPos + i, y, board))
                    return false;
            }
        }
        if (xPos == 7) {
            for (int i = 0; i <= 3; i++) {
                if (isInCheck(xPos - i, y, board))
                    return false;
            }
        }
        return true;
    }
}
