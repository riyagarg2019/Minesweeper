package com.example.riyagarg.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.riyagarg.minesweeper.Model.MinesweeperModel;

import java.util.Random;


public class MinesweeperView extends View {

    //private MinesweeperView() {}

    public static int[][] generate( int bomb, final int width, final int height){
         Random Random = new Random();
         int[][] grid = new int[width][height];
         for(int x = 0; x < width; x++){
           grid[x] = new int[height];
         }
         while(bomb > 0){
           int x = Random.nextInt(width);
           int y = Random.nextInt(height);

           if(grid[x][y] != -1){
             grid[x][y] = -1;
             bomb--;
            }
          }
          grid = calculateNeighbors(grid, width, height);
          return grid;
          }

        private static int[][] calculateNeighbors(int[][] grid, final int width, final int height){
          for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
              grid[i][j] = getNeighborNum(grid, i, j, width, height);
            }
          }

          return grid;
        }

        private static int getNeighborNum(final int grid[][], final int i, final int j, final int width, final int height){
          if(grid[i][j] == -1){
            return -1;
          }
          int count = 0;

          if(isBomb(grid, i-1, j, width, height)){count++;} //top mid
          if(isBomb(grid, i+1, j-1, width, height)){count++;}
          if(isBomb(grid, i+1, j, width, height)){count++;} //bottom min
          if(isBomb(grid, i-1, j-1, width, height)){count++;}
          //if(if isBomb(grid, i, j, width, height){count++;}
          if(isBomb(grid, i+1, j+1, width, height)){count++;} //bottom right
          if(isBomb(grid, i, j-1, width, height)){count++;} //left
          if(isBomb(grid, i-1, j+1, width, height)){count++;} //trop right
          if(isBomb(grid, i, j+1, width, height)){count++;} //right

          return count;
        }

        private static boolean isBomb(final int[][] grid, final int i, final int j, final int width, final int height){
          if(i >= 0 && j >= 0 && i < width && j < height){
            if(grid[i][j] == -1){
              return true;

            }
          }
          return false;
        }

    public static refresh(){
        invalidate();
    }


}

    /*
    private Paint paintBackground;
    private Paint paintLine;
    private Paint paintLinecolor2;
    private PointF box = null;

    public MinesweeperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paintBackground = new Paint();
        paintBackground.setColor(Color.BLACK);
        paintBackground.setStyle(Paint.Style.FILL);

        paintLine = new Paint();
        paintLinecolor2 = new Paint();

        paintLine.setColor(Color.WHITE);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(5);

        paintLinecolor2.setColor(Color.BLUE);
        paintLinecolor2.setStyle(Paint.Style.STROKE);
        paintLinecolor2.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBoxes(canvas);

        drawGameArea(canvas);

        drawTmpBoxes(canvas);
    }

    private void drawTmpBoxes(Canvas canvas) {
        if (box != null) {
            if (MinesweeperModel.getInstance().getNeighborBox() == MinesweeperModel.ONE) { //error here with short and float
                canvas.drawCircle(box.x, box.y,
                        getHeight() / 10,
                        paintLinecolor2
                );
            } else {
                canvas.drawLine(box.x - getWidth() / 10,
                        box.y - getHeight() / 10,
                        box.x + getWidth() / 10,
                        box.y + getHeight() / 10,
                        paintLine
                );
                canvas.drawLine(box.x - getWidth() / 10,
                        box.y + getHeight() / 10,
                        box.x + getWidth() / 10,
                        box.y - getHeight() / 10,
                        paintLine
                );
            }
        }
    }

    private void drawGameArea(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), paintBackground);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paintLine);

        // four horizontal
        canvas.drawLine(0, getHeight() / 5, getWidth(), getHeight() / 5, paintLine);
        canvas.drawLine(0, 2 * getHeight() / 5, getWidth(), 2 * getHeight() / 5,
                paintLine);
        canvas.drawLine(0, 3 * getHeight() / 5, getWidth(), 3 * getHeight() / 5,
                paintLine);
        canvas.drawLine(0, 4 * getHeight() / 5, getWidth(),
                4 * getHeight() / 5, paintLine);


        // four vertical lines
        canvas.drawLine(getWidth() / 5, 0, getWidth() / 5, getHeight(),
                paintLine);
        canvas.drawLine(2 * getWidth() / 5, 0, 2 * getWidth() / 5, getHeight(),
                paintLine);
        canvas.drawLine(3 * getWidth() / 5, 0, 3 * getWidth() / 5, getHeight(),
                paintLine);
        canvas.drawLine(4 * getWidth() / 5, 0, 4 * getWidth() / 5, getHeight(),
                paintLine);

    }

    private void drawBoxes(Canvas canvas) {
        for (short i = 0; i < 5; i++) {
            for (short j = 0; j < 5; j++) {
                if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.ONE) {

                    // draw a circle at the center of the field

                    // X coordinate: left side of the square + half width of the square
                    float centerX = i * getWidth() / 5 + getWidth() / 10;
                    float centerY = j * getHeight() / 5 + getHeight() / 10;
                    int radius = getHeight() / 10 - 5;

                    canvas.drawCircle(centerX, centerY, radius, paintLinecolor2);

                }

                if (MinesweeperModel.getInstance().getFieldContent(i, j) == MinesweeperModel.TWO) {
                    canvas.drawLine(i * getWidth() / 5, j * getHeight() / 5,
                            (i + 1) * getWidth() / 5,
                            (j + 1) * getHeight() / 5, paintLine);

                    canvas.drawLine((i + 1) * getWidth() / 5, j * getHeight() / 5,
                            i * getWidth() / 5, (j + 1) * getHeight() / 5, paintLine);
                }
            }
        }

    }
    //redo on touch event

    @Override
    public boolean onTouchEvent(MotionEvent event) { //redo on touch event
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            box = new PointF(event.getX(), event.getY());
            invalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            box = null;

            int tX = ((int) event.getX()) / (getWidth() / 5);
            int tY = ((int) event.getY()) / (getHeight() / 5);

            if (MinesweeperModel.getInstance().getFieldContent((short) tX, (short) tY)
                    == MinesweeperModel.ONE) {
                MinesweeperModel.getInstance().setFieldContent(
                        (short) tX, (short) tY, MinesweeperModel.ONE);


            }
            /*if (MinesweeperModel.getInstance().getFieldContent((short) tX, (short) tY)
                    == MinesweeperModel.TWO) {
                MinesweeperModel.getInstance().setFieldContent(
                        (short) tX, (short) tY, MinesweeperModel.TWO);


            }
            //}


        //}
        //return true;
    \\}

}*/
