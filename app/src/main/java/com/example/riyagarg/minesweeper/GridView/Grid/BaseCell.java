package com.example.riyagarg.minesweeper.GridView.Grid;

import android.content.Context;
import android.view.View;


//import com.example.riyagarg.minesweeper.MinesweeperView;
import com.example.riyagarg.minesweeper.Model.MinesweeperModel;

/**
 * Created by riyagarg on 3/9/18.
 */

//public class BaseCell {




/*public abstract class BaseCell extends View {

    private int value;
    private boolean isBomb;
    private boolean isRevealed;
    private boolean isClicked;
    private boolean isFlagged;

    private int x,y;
    private int position;

    public BaseCell(Context context){
        super(context);
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        isBomb = false;
        isRevealed = false;
        isClicked = false;
        isFlagged = false;

        if(value == -1){
            isBomb = true;
        }

        this.value = value;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked() {
        this.isClicked = true;
        this.isRevealed = true;

        //MinesweeperView.refresh();
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {

        this.position = position;

        x = position % MinesweeperModel.WIDTH;
        y = position / MinesweeperModel.HEIGHT;

        //MinesweeperView.refresh();
    }

    public int getPositionX() {
        return x;
    }


    public int getPositionY() {
        return y;
    }


    //public abstract void onMeasure(int widthmeasure, int heightmeasure);
}*/
