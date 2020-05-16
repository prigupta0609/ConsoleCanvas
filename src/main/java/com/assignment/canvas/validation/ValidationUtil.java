package com.assignment.canvas.validation;

import java.util.List;

public class ValidationUtil {

    public static boolean isNegativeNum (List<Integer> numList) {
        for (Integer num : numList) {
            if (num < 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isReqParamPresent (List<String> commandParamList, int reqParam) {
        if (commandParamList.size() < reqParam) {
            return false;
        } else if (commandParamList.size() > reqParam) {
            return false;
        }
        return true;
    }

    public static boolean isStraightLine (int x1, int y1, int x2, int y2) {
        if (x1 != x2 && y1 != y2) {
            return false;
        }
        return true;
    }

    public static boolean isOutOfCanvas(List<Integer> xList, List<Integer> yList, char[][] matrix) {
        if (matrix != null) {
            int xCanvas = matrix[0].length;
            int yCanvas = matrix.length;
            for (int x : xList) {
                if (x > xCanvas) {
                    return true;
                }
            }
            for (int y : yList) {
                if (y > yCanvas) {
                    return true;
                }
            }
        }
        return false;
    }
}
