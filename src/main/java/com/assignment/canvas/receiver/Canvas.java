package com.assignment.canvas.receiver;

import java.util.Arrays;
import java.util.List;

public final class Canvas implements IReceiver {

    private static char[][] matrix = null;
    private static final char DEFAULT_FILL_CHAR = 'o';

    public Canvas() {}

    public void createCanvas(List<String> params) {
        int x = Integer.parseInt(params.get(1))+2;
        int y = Integer.parseInt(params.get(2))+2;
        matrix = new char[y][x];
        draw(0, 0, x-1, 0, '-');
        draw(0, y-1, x-1, y-1, '-');
        draw(0, 1, 0, y-2, '|');
        draw(x-1, 1, x-1, y-2, '|');
    }

    public void clearCanvas() {
        int xCanvas = matrix[0].length;
        int yCanvas = matrix.length;
        for (int i=1; i<yCanvas-1; i++) {
            for (int j=1; j<xCanvas-1; j++) {
                matrix[i][j] = Character.MIN_VALUE;
            }
        }
    }

    public void drawLine(List<String> params) {
        int x1 = Integer.parseInt(params.get(1));
        int y1 = Integer.parseInt(params.get(2));
        int x2 = Integer.parseInt(params.get(3));
        int y2 = Integer.parseInt(params.get(4));
        draw(x1, y1, x2, y2, 'x');
    }

    public void drawRectangle(List<String> params) {
        int x1 = Integer.parseInt(params.get(1));
        int y1 = Integer.parseInt(params.get(2));
        int x2 = Integer.parseInt(params.get(3));
        int y2 = Integer.parseInt(params.get(4));
        draw(x1, y1, x2, y1, 'x');
        draw(x1, y2, x2, y2, 'x');
        draw(x1, y1, x1, y2, 'x');
        draw(x2, y1, x2, y2, 'x');
    }

    public void fillCanvas(List<String> params) {
        int x = Integer.parseInt(params.get(1));
        int y = Integer.parseInt(params.get(2));
        char ch;
        if (params.size() == 4) {
            ch = params.get(3).charAt(0);
        } else {
            ch = DEFAULT_FILL_CHAR;
        }
        fill(x, y, ch);
    }

    private void fill (int x, int y, char ch) {
        if (matrix[y][x] != Character.MIN_VALUE) {
            return;
        }
        if (x > 0 || x < matrix.length || y > 0 || y < matrix[0].length) {
            if (matrix[y][x] == Character.MIN_VALUE)
                matrix[y][x] = ch;
            fill(x + 1, y, ch);
            fill(x - 1, y, ch);
            fill(x, y - 1, ch);
            fill(x, y + 1, ch);
        }
    }

    public char[][] getMatrix() {
        return matrix;
    }

    private void draw(int x1, int y1, int x2, int y2, char drawChar) {
        if (x1 > x2) {
            int temp = x1;
            x1 = x2;
            x2 = temp;
        }
        if (y1 > y2) {
            int temp = y1;
            y1 = y2;
            y2 = temp;
        }
        if (x1 == x2) {
            // vertical line
            for (int i = y1; i <= y2; i++) {
                matrix[i][x1] = drawChar;
            }
        } else if (y1 == y2) {
            // horizontal line
            Arrays.fill(matrix[y1], x1, x2+1, drawChar);
        }
    }
}