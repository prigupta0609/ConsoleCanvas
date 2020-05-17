package com.assignment.canvas.execution;

import com.assignment.canvas.TestUtil;
import com.assignment.canvas.exception.Error;
import com.assignment.canvas.receiver.Canvas;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class ExecutorTest {

    private Canvas canvas = new Canvas();
    private Executor executor = new Executor();
    private ExecutionContext context = new ExecutionContext();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        CommandRegistry registry = CommandRegistryLoader.build(canvas);
        context.setCanvas(canvas);
        context.setRegistry(registry);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testCreateCanvasCommand () {
        context.setInputCommand("C 10 6");
        executor.execute(context);
        Assert.assertNotNull(context.getCanvas());
        Assert.assertNotNull(context.getCanvas().getMatrix());
        char[][] expectedMatrix = TestUtil.getCanvas(10, 6);
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
    }

    @Test
    public void testCreateHybridShapes () {
        context.setInputCommand("C 10 6");
        executor.execute(context);
        context.setInputCommand("R 2 2 8 4");
        executor.execute(context);
        context.setInputCommand("L 5 1 5 6");
        executor.execute(context);
        char[][] expectedMatrix = TestUtil.getCanvas(10, 6);
        Arrays.fill(expectedMatrix[2], 2, 9, 'x');
        Arrays.fill(expectedMatrix[4], 2, 9, 'x');
        expectedMatrix[3][2] = 'x';
        expectedMatrix[3][8] = 'x';
        for (int i=1; i<6+1; i++) {
            expectedMatrix[i][5] = 'x';
        }
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
        Assert.assertFalse(TestUtil.isShapeHollow(actualMatrix, 2, 2, 8, 4));
    }

    @Test
    public void testClearCanvas() {
        context.setInputCommand("C 10 6");
        executor.execute(context);
        Assert.assertNotNull(context.getCanvas().getMatrix());
        Assert.assertTrue(context.getCanvas().getMatrix().length > 0);
        context.setInputCommand("F 4 5 o");
        executor.execute(context);
        Assert.assertFalse(TestUtil.isShapeHollow(context.getCanvas().getMatrix(), 0, 0, 11, 7));
        context.setInputCommand("Z");
        executor.execute(context);
        Assert.assertTrue(TestUtil.isShapeHollow(context.getCanvas().getMatrix(), 0, 0, 11, 7));
    }
}
