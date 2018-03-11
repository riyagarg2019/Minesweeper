package com.example.riyagarg.minesweeper.Model;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.riyagarg.minesweeper.GridView.Grid.Cell;
import com.example.riyagarg.minesweeper.MinesweeperView;

public class MinesweeperModel {

    private static MinesweeperModel instance;

    public static final int BOMB_NUM = 3;
    public static final int WIDTH = 5;
    public static final int HEIGHT = 5;

    private Context context;

    private Cell[][] MinesweeperGrid = new Cell[WIDTH][HEIGHT];

    public static MinesweeperModel getInstance() {
        if (instance == null) {
            instance = new MinesweeperModel();
        }

        return instance;
    }

    private MinesweeperModel() {

    }

    public void createGrid(Context context){
      Log.e("GameModel", "create grid is working");

      this.context = context;
      //create the grid and store it
      int[][] Grid = MinesweeperView.generate(BOMB_NUM, WIDTH, HEIGHT);

      PrintModel.print(Grid, WIDTH, HEIGHT);
      setGrid(context, Grid);



    }

    private void setGrid(final Context context, final int[][] grid){
        for(int i = 0; i < WIDTH; i++){
            for (int j = 0; j < HEIGHT; j++){
                if(MinesweeperGrid[i][j] == null){
                    MinesweeperGrid[i][j] = new Cell(context, j*HEIGHT + i);
                }

                MinesweeperGrid[i][j].setValue(grid[i][j]);
                MinesweeperGrid[i][j].refresh(); //invalidate, In case if you want to call it from the model, it is a bit different, the model does not refreshes the view. It should work in a way that you change the data in the model and when the view refreshes itself, it draws the new content based on the new model data.
            }
        }
    }


    public View getCell(int position) {
        return null;
    }
}





    /*
    public static final short BOMB = 0;
    public static final short ONE = 1;
    public static final short TWO = 2;
    public static final short THREE = 3;
    public static final short EMPTY = 4;

    private short[][] model = {
            {ONE, ONE, ONE, EMPTY, EMPTY},
            {ONE, BOMB, TWO, ONE, EMPTY},
            {ONE, TWO, BOMB, ONE, EMPTY},
            {EMPTY, ONE, TWO, TWO, ONE},
            {EMPTY, EMPTY, ONE, BOMB, ONE}
    };

    public short getFieldContent(short x, short y) {return model[x][y];}

    public short getNeighborBox(){
        return ONE;
    }


    public void setFieldContent(short x, short y, short entry){
        model[x][y] = entry;
    }


}*/
