package com.assignment.canvas;

import com.assignment.canvas.execution.CommandRegistry;
import com.assignment.canvas.execution.CommandRegistryLoader;
import com.assignment.canvas.execution.ExecutionContext;
import com.assignment.canvas.execution.Executor;
import com.assignment.canvas.model.CommandType;
import com.assignment.canvas.receiver.Canvas;

import java.util.Scanner;

public class ConsoleCanvas {

    public static void run(Scanner scanner) {
        Canvas canvas = new Canvas();
        CommandRegistry registry = CommandRegistryLoader.build(canvas);
        Executor executor = new Executor();
        ExecutionContext context = new ExecutionContext();
        displayCommands();
        while (true) {
            System.out.print("Enter command : ");
            String command = scanner.nextLine();
            if (command.trim().length() > 0) {
                if (command.equals(CommandType.QUIT.getShortName())) {
                    System.out.println("Quiting application");
                    break;
                } else if (command.equals(CommandType.HELP.getShortName())) {
                    displayCommands();
                } else {
                    context.setRegistry(registry);
                    context.setCanvas(canvas);
                    context.setInputCommand(command);
                    executor.execute(context);
                }
            } else {
                System.out.println("Enter valid input else press Q");
            }
        }
    }

    private static void displayCommands() {
        System.out.println("Available commands for our canvas:");
        System.out.println("Create canvas     ->   C x y");
        System.out.println("Clear canvas      ->   Z");
        System.out.println("Draw line         ->   L x1 y1 x2 y2");
        System.out.println("Draw rectangle    ->   R x1 y1 x2 y2");
        System.out.println("Quit application  ->   Q");
        System.out.println("Help              ->   H");
    }
}
