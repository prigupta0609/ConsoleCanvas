package com.assignment.canvas.receiver;

import com.assignment.canvas.TestUtil;
import org.junit.*;

import java.util.Arrays;

public class CanvasTest {

    private static Canvas canvas;
    private static int canvasWidth = 10;
    private static int canvasLength = 6;

    @BeforeClass
    public static void setUp() {
        canvas = new Canvas();
    }

    @Before
    public void setUpTest() {
        canvas.createCanvas(Arrays.asList("C","10","6"));
    }

    @AfterClass
    public static void tear() {
        canvas = null;
    }

    @Test
    public void testCreateCanvas() {
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
        Assert.assertTrue(TestUtil.isShapeHollow(actualMatrix, 0, 0, canvasWidth, canvasLength));
    }

    @Test
    public void testDrawHorizontalLine() {
        canvas.drawLine(Arrays.asList("L","2","3","5","3"));
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        for (int i=2; i<=5; i++) {
            expectedMatrix[3][i] = 'x';
        }
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
    }

    @Test
    public void testDrawHorizontalLine_2() {
        canvas.drawLine(Arrays.asList("L","5","3","2","3"));
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        for (int i=2; i<=5; i++) {
            expectedMatrix[3][i] = 'x';
        }
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
    }

    @Test
    public void testDrawVerticalLine() {
        canvas.drawLine(Arrays.asList("L","2","1","2","5"));
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        for (int i=1; i<=5; i++) {
            expectedMatrix[i][2] = 'x';
        }
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
    }

    @Test
    public void testDrawVerticalLine_2() {
        canvas.drawLine(Arrays.asList("L","2","5","2","1"));
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        for (int i=1; i<=5; i++) {
            expectedMatrix[i][2] = 'x';
        }
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
    }

    @Test
    public void testDrawRectangle() {
        canvas.drawRectangle(Arrays.asList("R","2","2","7","4"));
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        Arrays.fill(expectedMatrix[2], 2, 8, 'x');
        Arrays.fill(expectedMatrix[4], 2, 8, 'x');
        expectedMatrix[3][2] = 'x';
        expectedMatrix[3][7] = 'x';
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
        Assert.assertTrue(TestUtil.isShapeHollow(actualMatrix, 2, 2, 7, 4));
    }

    @Test
    public void testDrawRectangle_2() {
        canvas.drawRectangle(Arrays.asList("R","7","4","2","2"));
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        Arrays.fill(expectedMatrix[2], 2, 8, 'x');
        Arrays.fill(expectedMatrix[4], 2, 8, 'x');
        expectedMatrix[3][2] = 'x';
        expectedMatrix[3][7] = 'x';
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
        Assert.assertTrue(TestUtil.isShapeHollow(actualMatrix, 2, 2, 7, 4));
    }

    @Test
    public void testHybridShapes() {
        canvas.drawLine(Arrays.asList("L","5","1","5","6"));
        canvas.drawRectangle(Arrays.asList("R","2","2","8","4"));
        char[][] expectedMatrix = TestUtil.getCanvas(canvasWidth, canvasLength);
        Arrays.fill(expectedMatrix[2], 2, 9, 'x');
        Arrays.fill(expectedMatrix[4], 2, 9, 'x');
        expectedMatrix[3][2] = 'x';
        expectedMatrix[3][8] = 'x';
        for (int i=1; i<canvasLength+1; i++) {
            expectedMatrix[i][5] = 'x';
        }
        char[][] actualMatrix = canvas.getMatrix();
        TestUtil.isMatrixSame(expectedMatrix, actualMatrix);
        Assert.assertFalse(TestUtil.isShapeHollow(actualMatrix, 2, 2, 8, 4));
    }

    @Test
    public void testClearCanvas() {
        Assert.assertTrue(TestUtil.isShapeHollow(canvas.getMatrix(), 0, 0, canvasWidth, canvasLength));
        canvas.drawLine(Arrays.asList("L","5","1","5","6"));
        Assert.assertFalse(TestUtil.isShapeHollow(canvas.getMatrix(), 0, 0, canvasWidth, canvasLength));
        canvas.clearCanvas();
        Assert.assertTrue(TestUtil.isShapeHollow(canvas.getMatrix(), 0, 0, canvasWidth, canvasLength));
    }

    @Test
    public void testFillBucketCanvas() {
        canvas.drawRectangle(Arrays.asList("R","2","2","8","4"));
        canvas.fillCanvas(Arrays.asList("F","2","5","e"));
        char[][] actualMatrix = canvas.getMatrix();
        Assert.assertNotNull(actualMatrix);
        Assert.assertEquals(8, actualMatrix.length);
        Assert.assertEquals(12, actualMatrix[0].length);
        Assert.assertEquals('e',actualMatrix[1][4]);
        Assert.assertEquals('x',actualMatrix[2][4]);
        Assert.assertEquals(Character.MIN_VALUE,actualMatrix[3][4]);
    }

    @Test
    public void testFillBucketCanvas_DefaultChar() {
        canvas.drawRectangle(Arrays.asList("R","2","2","8","4"));
        canvas.fillCanvas(Arrays.asList("F","2","5"));
        char[][] actualMatrix = canvas.getMatrix();
        Assert.assertNotNull(actualMatrix);
        Assert.assertEquals(8, actualMatrix.length);
        Assert.assertEquals(12, actualMatrix[0].length);
        Assert.assertEquals('o',actualMatrix[1][4]);
        Assert.assertEquals('x',actualMatrix[2][4]);
        Assert.assertEquals(Character.MIN_VALUE,actualMatrix[3][4]);
    }
}
