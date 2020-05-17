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
public class LineValidatorTest {

    private final LineValidator validator = LineValidator.getInstance();
    private Canvas mockCanvas = PowerMockito.mock(Canvas.class);

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        Mockito.when(mockCanvas.getMatrix()).thenReturn(new char[10][10]);
    }

    @Test
    public void testVerticalLine() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","4","2","9"), mockCanvas));
    }

    @Test
    public void testHorizontalLine() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","4","7","4"), mockCanvas));
    }

    @Test
    public void testDiagonalLine() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.INVALID_LINE_COORDINATES.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7","7"), mockCanvas));
    }

    @Test
    public void testSinglePointLine() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.INVALID_LINE_COORDINATES.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","7","2","7"), mockCanvas));
    }

    @Test
    public void testNullCanvas() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.CANVAS_NOT_EXIT.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","7","7"), null));
    }

    @Test
    public void testLessParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","4","7"), mockCanvas));
    }

    @Test
    public void testMoreParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","4","7","4","5"), mockCanvas));
    }

    @Test
    public void testShapeOutOfCanvasVertical() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.SHAPE_OUT_OF_CANVAS.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","2","2","20"), mockCanvas));
    }

    @Test
    public void testShapeOutOfCanvasHorizontal() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.SHAPE_OUT_OF_CANVAS.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","20","2","2","2"), mockCanvas));
    }

    @Test
    public void testNegativeInput() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NEGATIVE_PARAMS.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","-4","7","4"), mockCanvas));
    }

    @Test
    public void testNonNumericInput() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NON_NUMERIC_COORDINATES.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("L","2","-4","M","4"), mockCanvas));
    }
}
