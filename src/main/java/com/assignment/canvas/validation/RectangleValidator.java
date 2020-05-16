package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;

import java.util.Arrays;
import java.util.List;

public class RectangleValidator implements IValidator{

    private static final int REQ_PARAMS = 5;
    private static RectangleValidator validator = null;
    private RectangleValidator() {}

    public static RectangleValidator getInstance() {
        if (validator == null) {
            validator = new RectangleValidator();
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
                int x1 = Integer.parseInt(commandParamList.get(1));
                int y1 = Integer.parseInt(commandParamList.get(2));
                int x2 = Integer.parseInt(commandParamList.get(3));
                int y2 = Integer.parseInt(commandParamList.get(4));
                if (ValidationUtil.isOutOfCanvas(Arrays.asList(x1, x2), Arrays.asList(y1, y2), canvas.getMatrix())) {
                    throw new InvalidInputException(Error.SHAPE_OUT_OF_CANVAS.getErrorDesc());
                }
                if (ValidationUtil.isNegativeNum(Arrays.asList(x1, x2, y1, y2))) {
                    throw new InvalidInputException(Error.NEGATIVE_PARAMS.getErrorDesc());
                }
                if (ValidationUtil.isStraightLine(x1, y1, x2, y2)) {
                    throw new InvalidInputException(Error.RECTANGLE_FROM_LINE.getErrorDesc());
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
