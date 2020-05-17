package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Canvas.class)
public class RectangleValidatorTest {

    private final RectangleValidator validator = RectangleValidator.getInstance();
    private Canvas mockCanvas = PowerMockito.mock(Canvas.class);

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        Mockito.when(mockCanvas.getMatrix()).thenReturn(new char[10][10]);
    }

    @Test
    public void testValidRectangle() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("R","6","7","2","2"), mockCanvas));
    }

    @Test
    public void testNullCanvas() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.CANVAS_NOT_EXIT.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7","7"), null));
    }

    @Test
    public void testNullMatrixCanvas() throws InvalidInputException {
        Mockito.when(mockCanvas.getMatrix()).thenReturn(null);
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.CANVAS_NOT_EXIT.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7","7"), mockCanvas));
    }

    @Test
    public void testLessInputParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7"), mockCanvas));
    }

    @Test
    public void testExtraInputParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7","7","5"), mockCanvas));
    }

    @Test
    public void testRectangleOutOfCanvas() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.SHAPE_OUT_OF_CANVAS.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7","17"), mockCanvas));
    }

    @Test
    public void testNegativeInputParam() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NEGATIVE_PARAMS.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","-2","2","7","7"), mockCanvas));
    }

    @Test
    public void testInvalidCoordinates() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.RECTANGLE_FROM_LINE.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7","2"), mockCanvas));
    }

    @Test
    public void testInvalidCoordinates_2() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.RECTANGLE_FROM_LINE.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","7","2","7","8"), mockCanvas));
    }

    @Test
    public void testNonNumericParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NON_NUMERIC_COORDINATES.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","7","2","7","K"), mockCanvas));
    }
}
