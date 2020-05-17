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
}