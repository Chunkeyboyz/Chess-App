package com.example.csaper6.morphchess.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csaper6 on 4/19/17.
 */
public class Knight extends ChessPiece {


    public Knight() {

    }

    @Override
    BoardLocation move(BoardLocation newPos) {
        List<BoardLocation> possibleMoves = possibleMoves();
        for(int i = 0; i < possibleMoves.size(); i++) {
            if(newPos == possibleMoves.get(i))
                return newPos;
        }
        return null; //if not a valid board position
    }


    @Override
    List<BoardLocation> possibleMoves() {
        BoardLocation pos = getPosition();
        List<BoardLocation> possibleMoves = new ArrayList<>();
        int x = pos.getPosHorizontal();
        int y = pos.getPosVertical();
        for(int i = 1; i <= 8; i++){
            BoardLocation temp = new BoardLocation(x,i);
            possibleMoves.add(temp);
            temp = new BoardLocation(i,y);
            possibleMoves.add(temp);
            temp = new BoardLocation(x+i,y+i);
            if(!(temp.getPosHorizontal() < 1
                    && temp.getPosHorizontal() > 8
                    && temp.getPosVertical() < 1
                    && temp.getPosVertical() > 8))
                possibleMoves.add(temp);
            temp = new BoardLocation(x-i,y-i);
            if(!(temp.getPosHorizontal() < 1
                    && temp.getPosHorizontal() > 8
                    && temp.getPosVertical() < 1
                    && temp.getPosVertical() > 8))
                possibleMoves.add(temp);


        }

        return possibleMoves;

    }
}
