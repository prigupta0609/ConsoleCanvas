package com.assignment.canvas.execution;

import com.assignment.canvas.commands.ICommand;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private static final Map<String, ICommand> registry = new HashMap<>();
    private static CommandRegistry instance = null;

    private CommandRegistry(){}

    public static CommandRegistry getInstance() {
        if (instance == null) {
            instance = new CommandRegistry();
        }
        return instance;
    }

    public static void register(String commandType, ICommand command) {
        registry.put(commandType, command);
    }

    public ICommand getCommand(String command) {
        return registry.get(command);
    }
}
