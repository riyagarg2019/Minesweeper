package com.example.riyagarg.minesweeper.Model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.riyagarg.minesweeper.GridView.Grid.Cell;
import com.example.riyagarg.minesweeper.MinesweeperView;

import com.example.riyagarg.minesweeper.Model.PrintModel;

//import com.example.riyagarg.minesweeper.GridView.Grid.BaseCell;

import java.util.Random;

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

    public void createGrid(Context context) {
        Log.e("GameModel", "create grid is working");

        this.context = context;
        //create the grid and store it
        int[][] Grid = generate(BOMB_NUM, WIDTH, HEIGHT);

        PrintModel.print(Grid, WIDTH, HEIGHT);
        setGrid(context, Grid);


    }

    private void setGrid(final Context context, final int[][] grid) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (MinesweeperGrid[i][j] == null) {
                    MinesweeperGrid[i][j] = new Cell(context, j * HEIGHT + i);
                }

                MinesweeperGrid[i][j].setValue(grid[i][j]);
                //MinesweeperGrid[i][j].refresh(); //invalidate, In case if you want to call it from the model, it is a bit different, the model does not refreshes the view. It should work in a way that you change the data in the model and when the view refreshes itself, it draws the new content based on the new model data.
            }
        }
    }


    public Cell getCell(int x, int y) {
        return MinesweeperGrid[x][y];
    } // check this method

    public Cell getCell(int position){
        int i = position % WIDTH;
        int j = position / WIDTH;
        return MinesweeperGrid[i][j];

    }

    public void click( int x , int y ){
        if( x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && ! getCell(x,y).isClicked() ){
            getCell(x,y).setClicked();

            if( getCell(x,y).getValue() == 0 ){
                for( int xt = -1 ; xt <= 1 ; xt++ ){
                    for( int yt = -1 ; yt <= 1 ; yt++){
                        if( xt != yt ){
                            click(x + xt , y + yt);
                        }
                    }
                }
            }

            if( getCell(x,y).isBomb() ){
                onGameLost();
            }
        }

        checkEnd();
    }

    private boolean checkEnd(){
        int bombNotFound = BOMB_NUM;
        int notRevealed = WIDTH * HEIGHT;
        for ( int x = 0 ; x < WIDTH ; x++ ){
            for( int y = 0 ; y < HEIGHT ; y++ ){
                if( getCell(x,y).isRevealed() || getCell(x,y).isFlagged() ){
                    notRevealed--;
                }

                if( getCell(x,y).isFlagged() && getCell(x,y).isBomb() ){
                    bombNotFound--;
                }
            }
        }

        if( bombNotFound == 0 && notRevealed == 0 ){
            Toast.makeText(context,"Game won", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void flag( int x , int y ){
        boolean isFlagged = getCell(x,y).isFlagged();
        getCell(x,y).setFlagged(!isFlagged);
        //getCell(x,y).invalidate();
    }

    private void onGameLost(){
        // handle lost game
        Toast.makeText(context,"Game lost", Toast.LENGTH_SHORT).show();

        for ( int x = 0 ; x < WIDTH ; x++ ) {
            for (int y = 0; y < HEIGHT; y++) {
                getCell(x,y).setRevealed(true);
            }
        }
    }

    public static int[][] generate(int bomb, final int width, final int height) {
        Random Random = new Random();
        int[][] grid = new int[width][height];
        for (int x = 0; x < width; x++) {
            grid[x] = new int[height];
        }
        while (bomb > 0) {
            int x = Random.nextInt(width);
            int y = Random.nextInt(height);

            if (grid[x][y] != -1) {
                grid[x][y] = -1;
                bomb--;
            }
        }
        grid = calculateNeighbors(grid, width, height);
        return grid;
    }

    private static int[][] calculateNeighbors(int[][] grid, final int width, final int height) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = getNeighborNum(grid, i, j, width, height);
            }
        }

        return grid;
    }

    private static int getNeighborNum(final int grid[][], final int i, final int j, final int width, final int height) {
        if (grid[i][j] == -1) {
            return -1;
        }
        int count = 0;

        if (isBomb(grid, i - 1, j, width, height)) {
            count++;
        } //top mid
        if (isBomb(grid, i + 1, j - 1, width, height)) {
            count++;
        }
        if (isBomb(grid, i + 1, j, width, height)) {
            count++;
        } //bottom min
        if (isBomb(grid, i - 1, j - 1, width, height)) {
            count++;
        }
        //if(if isBomb(grid, i, j, width, height){count++;}
        if (isBomb(grid, i + 1, j + 1, width, height)) {
            count++;
        } //bottom right
        if (isBomb(grid, i, j - 1, width, height)) {
            count++;
        } //left
        if (isBomb(grid, i - 1, j + 1, width, height)) {
            count++;
        } //trop right
        if (isBomb(grid, i, j + 1, width, height)) {
            count++;
        } //right

        return count;
    }

    private static boolean isBomb(final int[][] grid, final int i, final int j, final int width, final int height) {
        if (i >= 0 && j >= 0 && i < width && j < height) {
            if (grid[i][j] == -1) {
                return true;

            }
        }
        return false;
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
