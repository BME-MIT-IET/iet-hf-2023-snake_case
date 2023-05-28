package test.ui;

import View.GameWindow;
import View.View;
import View.SystemCall;
import commands.Start;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import src.Board;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class GameWindowUiTest {
    private FrameFixture window;

    private SystemCall mockSystemCall;

    private View spyView;

    private Board testBoard;

    private Robot robot;

    private JPanel map;
    private GameWindow Frame;

    @Before
    public void setUp() throws IOException {
        Start start = new Start();
        testBoard = new Board();
        String[] args = {"mapTestNoBear.txt"};
        try{
            start.start(args, testBoard);
        }
        catch(Exception ex){
            System.out.println(ex);
        }
        mockSystemCall = mock(SystemCall.class);
        when(mockSystemCall.out()).thenCallRealMethod();
        spyView = spy(View.class);
        Frame = new GameWindow(mockSystemCall, spyView, testBoard);
        window = new FrameFixture(Frame);
        robot = window.robot;
        window.show();
        Frame.startGame();
        map = robot.finder().find(new GenericTypeMatcher<JPanel>(JPanel.class) {
            protected boolean isMatching(JPanel panel) {
                // Customize the condition to match your button
                return "MapPanel".equals(panel.getName());
            }
        });
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
    public void CraftTest() {
        robot.moveMouse(GetMapRelativeCoordinate(new Point(576,207)));
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("CollectButton").click();
        window.label("GeneticalCodesLabel").requireText("virusdance");
        window.button("EndTurnButton").click();
        window.label("MaterialsLabel").requireText("Amino: 2 Nukleo: 2");
        window.button("CraftButton").click();
        window.button("VirusDanceButton").click();
        window.label("MaterialsLabel").requireText("Amino: 1 Nukleo: 1");
        window.label("AgentsLabel").requireText("virusdance");
    }

    private Point GetMapRelativeCoordinate(Point point){
        Point location = map.getLocationOnScreen();
        location.x += point.x;
        location.y += point.y;
        return location;
    }
}
