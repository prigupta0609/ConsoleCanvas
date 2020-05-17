package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;

import java.util.Arrays;
import java.util.List;

public class ClearCanvasValidator implements IValidator {

    private static final int REQ_PARAMS = 1;
    private static ClearCanvasValidator validator = null;
    private ClearCanvasValidator() {}

    public static ClearCanvasValidator getInstance() {
        if (validator == null) {
            synchronized (ClearCanvasValidator.class) {
                if (validator == null) {
                    validator = new ClearCanvasValidator();
                }
            }
        }
        return validator;
    }

    @Override
    public boolean validate(List<String> commandParamList, Canvas canvas) throws InvalidInputException {
        if (canvas == null) {
            throw new InvalidInputException(Error.CANVAS_NOT_EXIT.getErrorDesc());
        }
        if (!ValidationUtil.isReqParamPresent(commandParamList, REQ_PARAMS)) {
            throw new InvalidInputException(Error.PARAM_NOT_MATCH.getErrorDesc());
        }
        return true;
    }
}
