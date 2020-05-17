package com.assignment.canvas.validation;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorFactoryTest {

    @Test
    public void testValidatorFactoryForCreateCanvas() {
        Assert.assertTrue(ValidatorFactory.getValidator("C") instanceof CanvasValidator);
    }

    @Test
    public void testValidatorFactoryForClearCanvas() {
        Assert.assertTrue(ValidatorFactory.getValidator("Z") instanceof ClearCanvasValidator);
    }

    @Test
    public void testValidatorFactoryForLine() {
        Assert.assertTrue(ValidatorFactory.getValidator("L") instanceof LineValidator);
    }

    @Test
    public void testValidatorFactoryForRectangle() {
        Assert.assertTrue(ValidatorFactory.getValidator("R") instanceof RectangleValidator);
    }

    @Test
    public void testValidatorFactoryForInvalidInput() {
        Assert.assertNull(ValidatorFactory.getValidator("W"));
    }
}
