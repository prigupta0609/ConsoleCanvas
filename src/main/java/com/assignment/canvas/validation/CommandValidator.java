package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;
import java.util.List;

public class CommandValidator {

    public static boolean validate(List<String> commandParamList, Canvas canvas) {
        IValidator validator = ValidatorFactory.getValidator(commandParamList.get(0));
        if (validator == null) {
            System.out.println(Error.COMMAND_NOT_FOUND.getErrorDesc());
            return false;
        } else {
            try {
                return validator.validate(commandParamList, canvas);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
}
