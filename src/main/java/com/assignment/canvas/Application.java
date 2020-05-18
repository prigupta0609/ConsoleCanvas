package com.assignment.canvas;

import com.assignment.canvas.execution.CommandRegistry;
import com.assignment.canvas.execution.CommandRegistryLoader;
import com.assignment.canvas.execution.ExecutionContext;
import com.assignment.canvas.execution.Executor;
import com.assignment.canvas.model.CommandType;
import com.assignment.canvas.receiver.Canvas;
import com.assignment.canvas.util.Menu;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws Exception {
        Canvas canvas = new Canvas();
        CommandRegistry registry = CommandRegistryLoader.build(canvas);
        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor();
        ExecutionContext context = new ExecutionContext();
        Menu.display();
        while (true) {
            System.out.println("Enter command : ");
            String command = scanner.nextLine();
            if (command.trim().length() > 0) {
                if (command.equals(CommandType.QUIT.getShortName())) {
                    System.out.println("Quiting application");
                    break;
                } else if (command.equals(CommandType.HELP.getShortName())) {
                    Menu.display();
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
        scanner.close();
    }
}
