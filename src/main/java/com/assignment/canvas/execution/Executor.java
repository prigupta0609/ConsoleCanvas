package com.assignment.canvas.execution;

import com.assignment.canvas.commands.ICommand;
import com.assignment.canvas.commands.impl.CreateCanvasCommand;
import com.assignment.canvas.receiver.Canvas;
import com.assignment.canvas.validation.CommandValidator;

import java.util.ArrayList;
import java.util.List;

public class Executor {

    public void execute(ExecutionContext context) {
        String command = context.getInputCommand();
        String[] arguments = command.split(" ");
        List<String> commandList = new ArrayList<>();
        for (String arg : arguments) {
            commandList.add(arg.trim());
        }
        Canvas canvas = context.getCanvas();
        CommandRegistry registry = context.getRegistry();
        if (CommandValidator.validate(commandList, canvas)) {
            ICommand commandObj = registry.getCommand(arguments[0]);
            if (commandObj instanceof CreateCanvasCommand) {
                canvas = new Canvas();
                registry = CommandRegistryLoader.build(canvas);
                context.setCanvas(canvas);
                context.setRegistry(registry);
                commandObj = registry.getCommand(arguments[0]);
            }
            commandObj.execute(commandList);
            printCanvas(canvas.getMatrix());
        }
    }

    private void printCanvas(char[][] matrix) {
        int y = matrix.length;
        int x = matrix[0].length;
        for (int i=0; i<y; i++) {
            for (int j=0; j<x; j++) {
                if (matrix[i][j] == Character.MIN_VALUE)
                    System.out.print(" ");
                else
                    System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
}
