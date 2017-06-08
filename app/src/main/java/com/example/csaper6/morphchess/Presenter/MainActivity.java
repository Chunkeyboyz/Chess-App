package com.example.csaper6.morphchess.Presenter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.csaper6.morphchess.Model.Bishop;
import com.example.csaper6.morphchess.Model.Board;
import com.example.csaper6.morphchess.Model.ChessPiece;
import com.example.csaper6.morphchess.Model.King;
import com.example.csaper6.morphchess.Model.Knight;
import com.example.csaper6.morphchess.Model.Pawn;
import com.example.csaper6.morphchess.Model.Queen;
import com.example.csaper6.morphchess.Model.Rook;
import com.example.csaper6.morphchess.R;

public class MainActivity extends AppCompatActivity {

    private Board board;
    private boolean turn = true, end = false; // white is true, black is false
    private int turnCounter = 0;
    private ImageView Square1_1;
    private FrameLayout frameLayout;
    int width;
    int height;
    int size;
    private ChessPiece selectedPiece;
    private Drawable sandstone, large;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = new Board(setInitialBoard());

        sandstone = getResources().getDrawable(R.drawable.sandstone);
        large = getResources().getDrawable(R.drawable.large);

        //Square1_1 = (ImageView) findViewById(R.id.Square1_1);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                createBoard(board);
            }
        });
    }

    private void createBoard(Board board) {
//        frameLayout.removeAllViews();
        width = frameLayout.getMeasuredWidth() / 8;
        height = frameLayout.getMeasuredHeight() / 8;
        size = Math.min(width, height);
        ImageView temp;
        int counter = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Log.d("ASD", " I EM HERE " + counter);
                counter++;

                ImageView square = new ImageView(getApplicationContext());
                if (i % 2 == j % 2) {
                    //square.setBackground(getResources().getDrawable(R.drawable.sandstone));
                    square.setImageDrawable(sandstone); //if an odd set to sandstone
                } else {
                    //square.setBackground(getResources().getDrawable(R.drawable.large));
                    square.setImageDrawable(large); //if even set to large
                }
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
                params.setMargins(size * i, size * j, 0, 0);
                square.setScaleType(ImageView.ScaleType.FIT_CENTER);
                wireSquare(i, j, square);

                frameLayout.addView(square, params);

                if (board.isOccupied(i, j)) {
                    ChessPiece piece = board.getPiece(i, j);
                    temp = piece.getImage();
                    wirePiece(piece);
                    frameLayout.addView(temp, params);
                    frameLayout.bringChildToFront(temp);
                }
            }
        }
    }
//&& board.isOccupied(selectedPiece.getX(),selectedPiece.getY())
    private void wireSquare(final int x, final int y, ImageView square) {
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPiece != null && selectedPiece.isMovePossible(x,y,board)) {
                    selectedPiece.move(x, y, board);
                    Log.d("i dont care", "onClick: " + x + ", " + y);
                    updatePiece(selectedPiece);
                    selectedPiece = null;
                    turn = !turn;
                }
            }
        });
    }

    private void updatePiece(ChessPiece piece) {
        int i = piece.getX();
        int j = piece.getY();

        Log.d("TAG", "x : " + i + ", y : " + j);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size, size);
        params.setMargins(size * i, size * j, 0, 0);
        ImageView temp = piece.getImage();
        temp.setLayoutParams(params);
        frameLayout.bringChildToFront(temp);
        //hjgjgj

//        frameLayout.remove

        Log.d("TAG", "x : " + i + ", y : " + j);
    }



    private void wirePiece(final ChessPiece piece) {
        piece.getImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == piece.getColor()) {
                    Log.d("if", "onClick: ");
                    selectedPiece = piece;
                }
                else {
                    if (selectedPiece != null) {
                        if (selectedPiece.isMovePossible(piece.getX(), piece.getY(), board)) {
                            selectedPiece.move(piece.getX(),piece.getY(),board);
                            updatePiece(selectedPiece);
                            selectedPiece = null;
                            turn = !turn;
                        }
                    }
                }
            }

        });
    }

    private ChessPiece[][] setInitialBoard() { //all moves use move() method
        ChessPiece array[][] = new ChessPiece[8][8];

        for (int i = 0; i <= 7; i++) {
            ImageView wPawn = new ImageView(getApplicationContext());
            wPawn.setImageDrawable(getResources().getDrawable(R.drawable.wpawn));
            array[i][1] = new Pawn(true, i, 1, wPawn);

            ImageView bPawn = new ImageView(getApplicationContext());
            bPawn.setImageDrawable(getResources().getDrawable(R.drawable.bpawn));
            array[i][6] = new Pawn(false, i, 6, bPawn);
        }
        ImageView wRookL = new ImageView(getApplicationContext());
        wRookL.setImageDrawable(getResources().getDrawable(R.drawable.wrook));
        array[0][0] = new Rook(true, 0, 0, wRookL);

        ImageView wRookR = new ImageView(getApplicationContext());
        wRookR.setImageDrawable(getResources().getDrawable(R.drawable.wrook));
        array[7][0] = new Rook(true, 7, 0, wRookR);

        ImageView bRookL = new ImageView(getApplicationContext());
        bRookL.setImageDrawable(getResources().getDrawable(R.drawable.brook));
        array[0][7] = new Rook(false, 0, 7, bRookL);

        ImageView bRookR = new ImageView(getApplicationContext());
        bRookR.setImageDrawable(getResources().getDrawable(R.drawable.brook));
        array[7][7] = new Rook(false, 7, 7, bRookR);

        ImageView wKnightL = new ImageView(getApplicationContext());
        wKnightL.setImageDrawable(getResources().getDrawable(R.drawable.wknight));
        array[1][0] = new Knight(true, 1, 0, wKnightL);

        ImageView wKnightR = new ImageView(getApplicationContext());
        wKnightR.setImageDrawable(getResources().getDrawable(R.drawable.wknight));
        array[6][0] = new Knight(true, 6, 0, wKnightR);

        ImageView bKnightL = new ImageView(getApplicationContext());
        bKnightL.setImageDrawable(getResources().getDrawable(R.drawable.bknight));
        array[1][7] = new Knight(false, 1, 7, bKnightL);

        ImageView bKnightR = new ImageView(getApplicationContext());
        bKnightR.setImageDrawable(getResources().getDrawable(R.drawable.bknight));
        array[6][7] = new Knight(false, 6, 7, bKnightR);

        ImageView wBishopL = new ImageView(getApplicationContext());
        wBishopL.setImageDrawable(getResources().getDrawable(R.drawable.wbishop));
        array[2][0] = new Bishop(true, 2, 0, wBishopL);

        ImageView wBishopR = new ImageView(getApplicationContext());
        wBishopR.setImageDrawable(getResources().getDrawable(R.drawable.wbishop));
        array[5][0] = new Bishop(true, 5, 0, wBishopR);

        ImageView bBishopL = new ImageView(getApplicationContext());
        bBishopL.setImageDrawable(getResources().getDrawable(R.drawable.bbishop));
        array[2][7] = new Bishop(false, 2, 7, bBishopL);

        ImageView bBishopR = new ImageView(getApplicationContext());
        bBishopR.setImageDrawable(getResources().getDrawable(R.drawable.bbishop));
        array[5][7] = new Bishop(false, 5, 7, bBishopR);


        ImageView wQueen = new ImageView(getApplicationContext());
        wQueen.setImageDrawable(getResources().getDrawable(R.drawable.wqueen));
        array[3][0] = new Queen(true, 3, 0, wQueen);

        ImageView bQueen = new ImageView(getApplicationContext());
        bQueen.setImageDrawable(getResources().getDrawable(R.drawable.bqueen));
        array[4][7] = new Queen(false, 4, 7, bQueen);

        ImageView wKing = new ImageView(getApplicationContext());
        wKing.setImageDrawable(getResources().getDrawable(R.drawable.wking));
        array[4][0] = new King(true, 4, 0, wKing);

        ImageView bKing = new ImageView(getApplicationContext());
        bKing.setImageDrawable(getResources().getDrawable(R.drawable.bking));
        array[3][7] = new King(false, 3, 7, bKing);

        return array;
    }

}





