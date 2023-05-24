package ActionListeners;

import View.Control;
import commands.Collect;
import src.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtherCollectActionListener implements ActionListener {
    private JPanel panel;
    private Board board;
    private JLabel label;
    private Control control;
    public OtherCollectActionListener(JPanel otherPanel, Board board, Control control){
        panel = otherPanel;
        this.board = board;
        this.control = control;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        label = new JLabel();
        label.setText("You have called the collect function.");
        panel.add(label);
        label.setVisible(true);
        Collect collect = new Collect();
        String[] args = {"collect", "virologist0"};
        collect.collect(args, board);
        control.InvalidateBasicPanel();
    }
}
