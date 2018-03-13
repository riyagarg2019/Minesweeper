package com.example.riyagarg.minesweeper.GridView.Grid;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

import com.example.riyagarg.minesweeper.Model.MinesweeperModel;

import java.util.jar.Attributes;

/**
 * Created by riyagarg on 3/9/18.
 */

public class Grid extends GridView{ //check name GridVIew
    public Grid(Context context, android.util.AttributeSet attributes){
        super(context, attributes);

        setNumColumns(MinesweeperModel.WIDTH);

    }
    private class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 25;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return MinesweeperModel.getInstance().getCell(position);
        }
    }
}
