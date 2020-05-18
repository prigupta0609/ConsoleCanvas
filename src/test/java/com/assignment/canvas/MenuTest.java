package com.assignment.canvas;

import com.assignment.canvas.exception.Error;
import com.assignment.canvas.execution.CommandRegistry;
import com.assignment.canvas.execution.CommandRegistryLoader;
import com.assignment.canvas.util.Menu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testDisplay() {
        Menu.display();
        Assert.assertEquals(TestUtil.getMenu().trim(), outContent.toString().trim());
    }
}
