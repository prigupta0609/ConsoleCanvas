package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;

import java.util.Arrays;
import java.util.List;

public class FillCanvasValidator implements IValidator{

    private static final int MIN_PARAM = 3;
    private static final int MAX_PARAM = 4;
    private static FillCanvasValidator validator = null;
    private FillCanvasValidator() {}

    public static FillCanvasValidator getInstance() {
        if (validator == null) {
            synchronized (FillCanvasValidator.class) {
                if (validator == null) {
                    validator = new FillCanvasValidator();
                }
            }
        }
        return validator;
    }

    @Override
    public boolean validate(List<String> commandParamList, Canvas canvas) throws InvalidInputException {
        if (canvas == null || canvas.getMatrix() == null || canvas.getMatrix().length == 0) {
            throw new InvalidInputException(Error.CANVAS_NOT_EXIT.getErrorDesc());
        }
        if (ValidationUtil.isParamInRange(commandParamList.size(), MIN_PARAM, MAX_PARAM)) {
            try {
                int x = Integer.parseInt(commandParamList.get(1));
                int y = Integer.parseInt(commandParamList.get(2));
                if (ValidationUtil.isOutOfCanvas(Arrays.asList(x), Arrays.asList(y), canvas.getMatrix())) {
                    throw new InvalidInputException(Error.SHAPE_OUT_OF_CANVAS.getErrorDesc());
                }
                if (ValidationUtil.isNegativeNum(Arrays.asList(x, y))) {
                    throw new InvalidInputException(Error.NEGATIVE_PARAMS.getErrorDesc());
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
