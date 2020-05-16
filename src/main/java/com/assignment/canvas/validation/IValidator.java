package com.assignment.canvas.validation;

import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;

import java.util.List;

public interface IValidator {
    public boolean validate(List<String> commandParamList, Canvas canvas) throws InvalidInputException;
}
