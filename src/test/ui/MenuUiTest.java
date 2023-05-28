package test.ui;

import View.Menu;
import View.SystemCall;
import org.fest.swing.core.Robot;
import org.junit.After;
import org.junit.Before;
import org.fest.swing.fixture.*;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class MenuUiTest {
    private FrameFixture window;

    private SystemCall mockSystemCall;

    private Robot robot;
    private Menu Frame;

    @Before
    public void setUp() throws IOException {
        mockSystemCall = mock(SystemCall.class);
        when(mockSystemCall.out()).thenCallRealMethod();
        Frame = new Menu(mockSystemCall);
        window = new FrameFixture(Frame);
        robot = window.robot;
        window.show();
    }

    @After
    public void tearDown() {
        // Clean up resources
        window.cleanUp();
    }

    @Test
    public void ExitButtonClicked() {
        window.button("ExitButton").click();
        verify(mockSystemCall, times(1)).exit(0);
    }

    @Test
    public void StartButtonClicked() {
        window.button("StartButton").click();
        window.requireNotVisible();
    }
}
