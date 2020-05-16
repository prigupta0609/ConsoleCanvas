package com.assignment.canvas.exception;

public enum Error {
    PARAM_NOT_MATCH(1, "Number of params don't match the requirement"),
    RECTANGLE_FROM_LINE(2, "Rectangle not possible with given coordinates"),
    NEGATIVE_PARAMS(3, "Parameter value can't be negative"),
    SHAPE_OUT_OF_CANVAS(4, "Shape can't be drawn out of canvas area"),
    COMMAND_NOT_FOUND(5, "You have entered wrong command"),
    NON_NUMERIC_PARAMS(6, "Non numeric parameters not excepted"),
    DIAGONAL_LINE_NOT_SUPPORTED(7, "Diagonal lines are not supported"),
    NOT_2D_SPACE(8, "Invalid input to create 2D space"),
    CANVAS_NOT_EXIT(9, "Create canvas before painting");

    private int errorCode;
    private String errorDesc;

    private Error(int errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
