package com.assignment.canvas.validation;

import com.assignment.canvas.model.CommandType;

import java.util.HashMap;
import java.util.Map;

public class ValidatorFactory {

    private static final Map<String, IValidator> validatorMap = new HashMap<>();

    static {
        validatorMap.put(CommandType.CREATE_CANVAS.getShortName(), CanvasValidator.getInstance());
        validatorMap.put(CommandType.CLEAR_CANVAS.getShortName(), ClearCanvasValidator.getInstance());
        validatorMap.put(CommandType.DRAW_LINE.getShortName(), LineValidator.getInstance());
        validatorMap.put(CommandType.DRAW_RECTANGLE.getShortName(), RectangleValidator.getInstance());
        validatorMap.put(CommandType.FILL_CANVAS.getShortName(), FillCanvasValidator.getInstance());
    }

    public static IValidator getValidator(String command) {
        return validatorMap.get(command.toUpperCase());
    }
}
