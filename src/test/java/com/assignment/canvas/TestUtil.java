package com.assignment.canvas;

import org.junit.Assert;

import java.util.Arrays;

public class TestUtil {

    public static char[][] getCanvas(int x, int y) {
        char[][] expectedMatrix = new char[y+2][x+2];
        Arrays.fill(expectedMatrix[0],'-');
        Arrays.fill(expectedMatrix[y+1],'-');
        for (int i=1; i<=y; i++) {
            expectedMatrix[i][0] = '|';
            expectedMatrix[i][x+1] = '|';
        }
        return expectedMatrix;
    }

    public static boolean isShapeHollow(char[][] matrix, int x1, int y1, int x2, int y2) {
        for (int i=y1+1; i<y2; i++) {
            for (int j=x1+1; j<x2; j++) {
                if (matrix[i][j] != Character.MIN_VALUE) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void isMatrixSame(char[][] expectedMatrix, char[][] actualMatrix) {
        Assert.assertNotNull(expectedMatrix);
        Assert.assertNotNull(actualMatrix);
        Assert.assertEquals(expectedMatrix.length, actualMatrix.length);
        Assert.assertEquals(expectedMatrix[0].length, actualMatrix[0].length);
        for (int i=0; i<expectedMatrix.length; i++) {
            Assert.assertArrayEquals(expectedMatrix[i], actualMatrix[i]);
        }
    }

    public static String getMenu() {
        StringBuilder str = new StringBuilder("");
        str.append("Available commands for our canvas:\r\n");
        str.append("Create canvas     ->   C x y\r\n");
        str.append("Clear canvas      ->   Z\r\n");
        str.append("Draw line         ->   L x1 y1 x2 y2\r\n");
        str.append("Draw rectangle    ->   R x1 y1 x2 y2\r\n");
        str.append("Quit application  ->   Q\r\n");
        str.append("Help              ->   H");
        return str.toString();
    }
}