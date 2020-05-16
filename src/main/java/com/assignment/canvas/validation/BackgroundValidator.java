package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;

import java.util.Arrays;
import java.util.List;

public class BackgroundValidator implements IValidator {

    private static final int REQ_PARAMS = 4;
    private static BackgroundValidator validator = null;
    private BackgroundValidator() {}

    public static BackgroundValidator getInstance() {
        if (validator == null) {
            validator = new BackgroundValidator();
        }
        return validator;
    }

    @Override
    public boolean validate(List<String> commandParamList, Canvas canvas) throws InvalidInputException {
        if (canvas == null) {
            throw new InvalidInputException(Error.CANVAS_NOT_EXIT.getErrorDesc());
        }
        if (ValidationUtil.isReqParamPresent(commandParamList, REQ_PARAMS)) {
            try {
                int x = Integer.parseInt(commandParamList.get(1));
                int y = Integer.parseInt(commandParamList.get(2));
                if (ValidationUtil.isOutOfCanvas(Arrays.asList(x), Arrays.asList(y), canvas.getMatrix())) {
                    throw new InvalidInputException(Error.SHAPE_OUT_OF_CANVAS.getErrorDesc());
                }
                if (ValidationUtil.isNegativeNum(Arrays.asList(x,y))) {
                    throw new InvalidInputException(Error.NEGATIVE_PARAMS.getErrorDesc());
                }
            } catch (NumberFormatException exception) {
                throw new InvalidInputException(Error.NON_NUMERIC_PARAMS.getErrorDesc());
            }
        } else {
            throw new InvalidInputException(Error.PARAM_NOT_MATCH.getErrorDesc());
        }
        return true;
    }
}
