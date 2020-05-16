package com.assignment.canvas.model;

public enum CommandType {
    CREAT_CANVAS("C"),
    CLEAR_CANVAS("Z"),
    DRAW_LINE("L"),
    DRAW_RECTANGLE("R"),
    FILL_BACKGROUND("B"),
    QUIT("Q");

    private String shortName;
    private CommandType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}
