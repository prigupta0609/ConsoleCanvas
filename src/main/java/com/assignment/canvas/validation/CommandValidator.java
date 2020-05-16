package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.model.CommandType;
import com.assignment.canvas.receiver.Canvas;
//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandValidator {

//    private static Logger logger = LoggerFactory.getLogger(CommandValidator.class);

    private static Map<String, IValidator> validatorMap = new HashMap<>();
    static {
        validatorMap.put(CommandType.CREAT_CANVAS.getShortName(), CanvasValidator.getInstance());
        validatorMap.put(CommandType.CLEAR_CANVAS.getShortName(), ClearCanvasValidator.getInstance());
        validatorMap.put(CommandType.DRAW_LINE.getShortName(), LineValidator.getInstance());
        validatorMap.put(CommandType.DRAW_RECTANGLE.getShortName(), RectangleValidator.getInstance());
        validatorMap.put(CommandType.FILL_BACKGROUND.getShortName(), BackgroundValidator.getInstance());
    }

    public static boolean validate(List<String> commandParamList, Canvas canvas) {
        IValidator validator = validatorMap.get(commandParamList.get(0));
        if (validator == null) {
            System.out.println(Error.COMMAND_NOT_FOUND.getErrorDesc());
        } else {
            try {
                validator.validate(commandParamList, canvas);
            } catch (InvalidInputException e) {
//                logger.error(e.getMessage());
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }
}
