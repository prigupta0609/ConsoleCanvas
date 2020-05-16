package com.assignment.canvas.execution;

import com.assignment.canvas.receiver.Canvas;

import java.util.List;

public class ExecutionContext {

    private Canvas canvas;
    private String inputCommand;
    private CommandRegistry registry;

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public String getInputCommand() {
        return inputCommand;
    }

    public void setInputCommand(String inputCommand) {
        this.inputCommand = inputCommand;
    }

    public CommandRegistry getRegistry() {
        return registry;
    }

    public void setRegistry(CommandRegistry registry) {
        this.registry = registry;
    }
}
