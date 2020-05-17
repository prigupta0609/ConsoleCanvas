package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;

import java.util.Arrays;
import java.util.List;

public class CanvasValidator implements IValidator {

    private static final int REQ_PARAMS = 3;
    private static CanvasValidator validator = null;
    private CanvasValidator() {}

    public static CanvasValidator getInstance() {
        if (validator == null) {
            synchronized (CanvasValidator.class) {
                if (validator == null) {
                    validator = new CanvasValidator();
                }
            }
        }
        return validator;
    }

    @Override
    public boolean validate(List<String> commandParamList, Canvas canvas) throws InvalidInputException {
        if (ValidationUtil.isReqParamPresent(commandParamList, REQ_PARAMS)) {
            try {
                int x = Integer.parseInt(commandParamList.get(1));
                int y = Integer.parseInt(commandParamList.get(2));
                if (ValidationUtil.isNegativeNum(Arrays.asList(x,y))) {
                    throw new InvalidInputException(Error.NEGATIVE_PARAMS.getErrorDesc());
                }
                if (x == 0 || y == 0) {
                    throw new InvalidInputException(Error.NOT_2D_SPACE.getErrorDesc());
                }
            } catch (NumberFormatException exception) {
                throw new InvalidInputException(Error.NON_NUMERIC_COORDINATES.getErrorDesc());
            }
        } else {
            throw new InvalidInputException(Error.PARAM_NOT_MATCH.getErrorDesc());
        }
        return true;
    }
}
