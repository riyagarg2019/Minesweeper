package com.example.riyagarg.minesweeper.Model;

/**
 * Created by riyagarg on 3/8/18.
 */

public class PrintModel {
    public static void print(final int[][] grid, final int width, final int height){
        for(int x = 0; x < width; x++) {
            String printedText = "| ";
            for (int y = 0; y < height; y++) {
                printedText += String.valueOf(grid[x][y]).replace("-1", "B") + "| ";
            }
            //System.out.print(printedText);
        }
    }
}
