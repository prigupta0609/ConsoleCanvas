package com.assignment.canvas.commands.impl;

import com.assignment.canvas.commands.ICommand;
import com.assignment.canvas.receiver.Canvas;

import java.util.List;

public class FillCanvasCommand implements ICommand {

    private final Canvas canvas;

    public FillCanvasCommand(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void execute(List<String> params) {
        canvas.fillCanvas(params);
    }
}
