package com.assignment.canvas.exception;

public enum Error {
    PARAM_NOT_MATCH("Number of params don't match the requirement"),
    RECTANGLE_FROM_LINE("These are coordinates of line and not of rectangle"),
    NEGATIVE_PARAMS("Parameter value can't be negative"),
    SHAPE_OUT_OF_CANVAS("Shape can't be drawn out of canvas area"),
    COMMAND_NOT_FOUND("You have entered wrong command"),
    NON_NUMERIC_COORDINATES("Non numeric coordinates are not valid"),
    INVALID_LINE_COORDINATES("Diagonal or points not supported"),
    NOT_2D_SPACE("Invalid input to create 2D space"),
    CANVAS_NOT_EXIT("Create canvas before painting");

    private String errorDesc;

    private Error(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorDesc() {
        return errorDesc;
    }
}
