package com.example.csaper6.morphchess.Presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.csaper6.morphchess.Model.Board;
import com.example.csaper6.morphchess.Model.ChessPiece;
import com.example.csaper6.morphchess.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setInitialBoard();
    }

    private Board setInitialBoard() {

    }
}
