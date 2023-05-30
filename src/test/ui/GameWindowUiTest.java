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
import org.fest.swing.fixture.JLabelFixture;
import org.fest.swing.fixture.JPanelFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
        testBoard.SetNULLVirologusok();
        testBoard.setNULLFelszerelesek();
        testBoard.setNULLGenetikaiKodok();
        testBoard.setNULLMezok();
        testBoard.setNULLPolyKoordok();
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
        Frame.startGame();
        JPanelFixture mapFixture = window.panel("MapPanel").requireVisible();
        map = mapFixture.component();
        window.show();
        window.moveTo(new Point(0,0));
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

    @Test
    public void WinTest() {
        robot.moveMouse(GetMapRelativeCoordinate(new Point(573,199)));//1
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("CollectButton").click();
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(566,232)));//2
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);

        robot.moveMouse(GetMapRelativeCoordinate(new Point(511,241)));//3
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(423,323)));//4
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(560,205)));//5
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("CollectButton").click();
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(337,297)));//6
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(293,307)));//7
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(244,243)));//8
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("CollectButton").click();
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(261,238)));//9
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(275,241)));//10
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);

        robot.moveMouse(GetMapRelativeCoordinate(new Point(374,163)));//11
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(300,212)));//12
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("CollectButton").click();
        window.button("EndTurnButton").click();

        window.button("CraftButton").requireDisabled();
        window.button("DropButton").requireDisabled();
        window.button("CollectButton").requireDisabled();
        window.button("AttackButton").requireDisabled();
        window.button("StealButton").requireDisabled();
        window.button("EndTurnButton").requireDisabled();
        window.button("SaveButton").requireDisabled();
        window.button("ExitButton").requireEnabled();
        window.label("CodesLabel").requireText("The player has earned an EPIC Victory Royale!");
        window.button("ExitButton").click();
        verify(mockSystemCall, times(1)).exit(0);
    }

    @Test
    public void AttackTest() {
        robot.moveMouse(GetMapRelativeCoordinate(new Point(573,199)));//1
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(566,232)));//2
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);

        robot.moveMouse(GetMapRelativeCoordinate(new Point(511,241)));//3
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(423,323)));//4
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("EndTurnButton").click();

        robot.moveMouse(GetMapRelativeCoordinate(new Point(560,205)));//5
        robot.pressMouse(MouseButton.LEFT_BUTTON);
        robot.releaseMouse(MouseButton.LEFT_BUTTON);
        window.button("CollectButton").click();
        window.button("EndTurnButton").click();
        window.button("CraftButton").click();
        window.button("ProtectButton").click();
        window.button("EndTurnButton").click();
        window.button("AttackButton").click();
        window.radioButton("SelfRadioButton").check();
        window.radioButton("Agent0RadioButton").check();
        window.button("ConfirmButton").click();
        window.label("AttackLabel").requireText("I attacked a virologist.");
    }

    private Point GetMapRelativeCoordinate(Point point){
        Point location = map.getLocationOnScreen();
        location.x += point.x;
        location.y += point.y;
        return location;
    }
}
