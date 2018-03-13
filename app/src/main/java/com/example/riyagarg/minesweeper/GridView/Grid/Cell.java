package com.example.riyagarg.minesweeper.GridView.Grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.example.riyagarg.minesweeper.Model.MinesweeperModel;
import com.example.riyagarg.minesweeper.R;

/**
 * Created by riyagarg on 3/9/18.
 */

public class Cell extends BaseCell implements View.OnClickListener, View.OnLongClickListener{

    private MinesweeperModel instance;

    public Cell(Context context, int position){
        super(context);

        setPosition(position);

        setOnClickListener(this);
        setOnLongClickListener(this);
    }

    @Override
    protected void onMeasure(int widthmeasure, int heightmeasure){
        super.onMeasure(widthmeasure, heightmeasure);

    }

    @Override
    protected void onClick(View v){
        MinesweeperModel.getInstance().click(getPositionX(), getPositionY());
    }

    @Override
    public boolean onLongClick(View v) {
        MinesweeperModel.getInstance().flag(getPositionX(), getPositionY());

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawButton(canvas);

        if( isFlagged() ){
            drawFlag(canvas);
        }else if( isRevealed() && isBomb() && !isClicked() ){
            MinesweeperModel.onGameLost();
        }else {
            if( isClicked() ){
                if( getValue() == -1 ){
                    MinesweeperModel.onGameLost();
                }else {
                    drawNum(canvas);
                }
            }else{
                drawButton(canvas);
            }
        }
    }


    private void drawFlag( Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flagpic);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas ){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.buttonnorm);
        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }

    private void drawNum( Canvas canvas ){
        Drawable drawable = null;

        switch (getValue() ){
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.nothing);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num1);
                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num2);
                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.num3);
                break;
        }

        drawable.setBounds(0,0,getWidth(),getHeight());
        drawable.draw(canvas);
    }


}

