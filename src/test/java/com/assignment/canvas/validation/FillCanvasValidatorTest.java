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
public class FillCanvasValidatorTest {

    private final FillCanvasValidator validator = FillCanvasValidator.getInstance();
    private Canvas mockCanvas = PowerMockito.mock(Canvas.class);

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUp() {
        Mockito.when(mockCanvas.getMatrix()).thenReturn(new char[10][10]);
    }

    @Test
    public void testValidFillCanvas() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("F","4","6","o"), mockCanvas));
    }

    @Test
    public void testValidFillCanvas_MinParam() throws InvalidInputException {
        Assert.assertTrue(validator.validate(Arrays.asList("F","4","6"), mockCanvas));
    }

    @Test
    public void testNullMatrix() throws InvalidInputException {
        Mockito.when(mockCanvas.getMatrix()).thenReturn(null);
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.CANVAS_NOT_EXIT.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("F","4","6"), mockCanvas));
    }

    @Test
    public void testOutOfCanvas() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.SHAPE_OUT_OF_CANVAS.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("F","14","6"), mockCanvas));
    }

    @Test
    public void testNegativeParams() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NEGATIVE_PARAMS.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("F","-1","6"), mockCanvas));
    }

    @Test
    public void testNonNumericParam() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.NON_NUMERIC_COORDINATES.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("F","G","6"), mockCanvas));
    }

    @Test
    public void testParamSizeNotMatch() throws InvalidInputException {
        expectedEx.expect(InvalidInputException.class);
        expectedEx.expectMessage(Error.PARAM_NOT_MATCH.getErrorDesc());
        Assert.assertTrue(validator.validate(Arrays.asList("F","9","6","4","0"), mockCanvas));
    }
}
