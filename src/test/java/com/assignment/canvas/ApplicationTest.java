package com.assignment.canvas;

import com.assignment.canvas.execution.ExecutionContext;
import com.assignment.canvas.execution.Executor;
import com.assignment.canvas.receiver.Canvas;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import static org.mockito.Matchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Application.class)
public class ApplicationTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog().mute();

    @Test
    public void testQuitApplication() throws Exception {
        systemInMock.provideLines("Q");
        Application.main(null);
        Assert.assertTrue(systemOutRule.getLog().contains("Available commands for our canvas:"));
        Assert.assertTrue(systemOutRule.getLog().contains("Quiting application"));
        Assert.assertFalse(systemOutRule.getLog().contains("Enter valid input else press Q"));
    }

    @Test
    public void testHelpApplication() throws Exception {
        systemInMock.provideLines("H","Q");
        Application.main(null);
        Assert.assertTrue(systemOutRule.getLog().contains("Available commands for our canvas:"));
        Assert.assertTrue(systemOutRule.getLog().contains("Quiting application"));
        Assert.assertFalse(systemOutRule.getLog().contains("Enter valid input else press Q"));
    }

    @Test
    public void testPrintCanvas() throws Exception {
        systemInMock.provideLines("C 10 10","Q");
        Application.main(null);
        Assert.assertTrue(systemOutRule.getLog().contains("Quiting application"));
        Assert.assertTrue(systemOutRule.getLog().contains("------------"));
    }

    @Test
    public void testInvalidCommand() throws Exception {
        systemInMock.provideLines("","Q");
        Application.main(null);
        Assert.assertTrue(systemOutRule.getLog().contains("Enter valid input else press Q"));
        Assert.assertTrue(systemOutRule.getLog().contains("Quiting application"));
    }

    @Test
    public void testMethodExecution() throws Exception {
        Executor executor = Mockito.mock(Executor.class);
        PowerMockito.whenNew(Executor.class).withNoArguments().thenReturn(executor);
        systemInMock.provideLines("C 10 10","L 2 1 5 1","Q");
        Application.main(null);
        Mockito.verify(executor, Mockito.times(2)).execute(any(ExecutionContext.class));
    }
}
