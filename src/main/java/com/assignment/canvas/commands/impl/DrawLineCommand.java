package com.assignment.canvas.commands.impl;

import com.assignment.canvas.commands.ICommand;
import com.assignment.canvas.receiver.Canvas;

import java.util.List;

public class DrawLineCommand implements ICommand {

    private final Canvas canvas;

    public DrawLineCommand(Canvas canvas) {
        this.canvas = canvas;
    }

    public void execute(List<String> params) {
        canvas.drawLine(params);
    }
}
