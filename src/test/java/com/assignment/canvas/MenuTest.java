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
        Assert.assertEquals(getExpectedMenu().trim(), outContent.toString().trim());
    }

    private String getExpectedMenu() {
        StringBuilder str = new StringBuilder("");
        str.append("Available commands for our canvas:\r\n");
        str.append("Create canvas     ->   C x y\r\n");
        str.append("Clear canvas      ->   Z\r\n");
        str.append("Draw line         ->   L x1 y1 x2 y2\r\n");
        str.append("Draw rectangle    ->   R x1 y1 x2 y2\r\n");
        str.append("Quit application  ->   Q\r\n");
        str.append("Help              ->   H");
        return str.toString();
    }
}
