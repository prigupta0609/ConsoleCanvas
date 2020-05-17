package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class ClearCanvasValidatorTest {

    private final ClearCanvasValidator validator = ClearCanvasValidator.getInstance();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testSuccess() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("Z"), new Canvas()));
    }

    @Test
    public void testNonExistingCanvas() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.CANVAS_NOT_EXIT.getErrorDesc());
        validator.validate(Arrays.asList("Z"), null);
    }

    @Test
    public void testLessParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        validator.validate(Arrays.asList(), new Canvas());
    }

    @Test
    public void testMoreParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        validator.validate(Arrays.asList("Z","0"), new Canvas());
    }
}
