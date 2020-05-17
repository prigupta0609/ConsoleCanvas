package com.assignment.canvas.validation;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.exception.InvalidInputException;
import com.assignment.canvas.receiver.Canvas;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidatorFactory.class)
public class CommandValidatorTest {

    private static IValidator mockValidator = null;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        mockValidator = Mockito.mock(IValidator.class);
        System.setOut(new PrintStream(outContent));
        PowerMockito.mockStatic(ValidatorFactory.class);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testValidationSuccessful() throws InvalidInputException {
        Mockito.when(mockValidator.validate(any(List.class), any(Canvas.class))).thenReturn(true);
        PowerMockito.when(ValidatorFactory.getValidator(anyString())).thenReturn(mockValidator);
        Assert.assertTrue(CommandValidator.validate(Arrays.asList("C 3 4"), new Canvas()));
    }

    @Test
    public void testValidationUnSuccessful() throws InvalidInputException {
        Mockito.when(mockValidator.validate(any(List.class), any(Canvas.class))).thenReturn(false);
        PowerMockito.when(ValidatorFactory.getValidator(anyString())).thenReturn(mockValidator);
        Assert.assertFalse(CommandValidator.validate(Arrays.asList("W"), new Canvas()));
    }

    @Test
    public void testCommandNotFound() throws InvalidInputException {
        Mockito.when(mockValidator.validate(any(List.class), any(Canvas.class))).thenReturn(false);
        PowerMockito.when(ValidatorFactory.getValidator(anyString())).thenReturn(null);
        Assert.assertFalse(CommandValidator.validate(Arrays.asList(""), new Canvas()));
        Assert.assertEquals(Error.COMMAND_NOT_FOUND.getErrorDesc(), outContent.toString().trim());
    }

    @Test
    public void testInvalidInputException() throws InvalidInputException {
        Mockito.when(mockValidator.validate(any(List.class), any(Canvas.class))).thenThrow(new InvalidInputException(Error.NEGATIVE_PARAMS.getErrorDesc()));
        PowerMockito.when(ValidatorFactory.getValidator(anyString())).thenReturn(mockValidator);
        Assert.assertFalse(CommandValidator.validate(Arrays.asList("C -3 -4"), new Canvas()));
        Assert.assertEquals(Error.NEGATIVE_PARAMS.getErrorDesc(), outContent.toString().trim());
    }
}
