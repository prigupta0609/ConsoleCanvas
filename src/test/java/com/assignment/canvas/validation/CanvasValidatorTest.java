package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class CanvasValidatorTest {

    private final CanvasValidator validator = CanvasValidator.getInstance();

    @Test
    public void testValidCanvas() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("C", "7", "4"), new Canvas()));
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testNegativeParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NEGATIVE_PARAMS.getErrorDesc());
        validator.validate(Arrays.asList("C", "-7", "4"), new Canvas());
    }

    @Test
    public void test2DSpaceParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NOT_2D_SPACE.getErrorDesc());
        validator.validate(Arrays.asList("C", "0", "4"), new Canvas());
    }

    @Test
    public void testNonNumericParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NON_NUMERIC_COORDINATES.getErrorDesc());
        validator.validate(Arrays.asList("C", "5", "y"), new Canvas());
    }

    @Test
    public void testMoreParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        validator.validate(Arrays.asList("C", "5", "y", "9"), new Canvas());
    }

    @Test
    public void testLessParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        validator.validate(Arrays.asList("C", "5"), new Canvas());
    }

    @Test
    public void testSeqMultipleErrors() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NON_NUMERIC_COORDINATES.getErrorDesc());
        validator.validate(Arrays.asList("C", "-5", "Y"), new Canvas());
    }

    @Test
    public void testZeroParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        validator.validate(Arrays.asList(), new Canvas());
    }

    @Test
    public void testNoCanvasExistPrior() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("C", "10", "30"), null));
    }
}
