package com.example.riyagarg.minesweeper.GridView.Grid;

import android.content.Context;

/**
 * Created by riyagarg on 3/9/18.
 */

public class Cell extends BaseCell{

    public Cell(Context context, int position){
        super(context);
    }

    @Override
    protected void onMeasure(int widthmeasure, int heightmeasure){
        super.onMeasure(widthmeasure, heightmeasure);

    }
}
