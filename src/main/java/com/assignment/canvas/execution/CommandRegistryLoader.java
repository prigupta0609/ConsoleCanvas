package com.assignment.canvas.execution;

import com.assignment.canvas.commands.impl.*;
import com.assignment.canvas.model.CommandType;
import com.assignment.canvas.receiver.Canvas;

public class CommandRegistryLoader {

    public static CommandRegistry build(Canvas canvas) {
        CommandRegistry registry = CommandRegistry.getInstance();
        registry.register(CommandType.CLEAR_CANVAS.getShortName(), new ClearCanvasCommand(canvas));
        registry.register(CommandType.CREATE_CANVAS.getShortName(), new CreateCanvasCommand(canvas));
        registry.register(CommandType.DRAW_LINE.getShortName(), new DrawLineCommand(canvas));
        registry.register(CommandType.DRAW_RECTANGLE.getShortName(), new DrawRectangleCommand(canvas));
        registry.register(CommandType.FILL_CANVAS.getShortName(), new FillCanvasCommand(canvas));
        return registry;
    }
}
