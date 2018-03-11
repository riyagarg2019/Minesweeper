package com.example.riyagarg.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riyagarg.minesweeper.Model.MinesweeperModel;
import com.example.riyagarg.minesweeper.MinesweeperView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ((TextView)findViewById(R.id.textView)).setText("AHDKJSA"); //test

        //final MinesweeperView minesweeperView = findViewById(R.id.gameboard);
        MinesweeperModel.getInstance().createGrid(this);
    }
    //public void showMessage(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    //}

}
