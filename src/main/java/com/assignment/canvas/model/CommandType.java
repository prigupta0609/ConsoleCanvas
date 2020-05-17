package com.assignment.canvas.model;

public enum CommandType {
    CREATE_CANVAS("C"),
    CLEAR_CANVAS("Z"),
    DRAW_LINE("L"),
    DRAW_RECTANGLE("R"),
    FILL_CANVAS("F"),
    QUIT("Q"),
    HELP("H");

    private String shortName;
    private CommandType(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return this.shortName;
    }
}
