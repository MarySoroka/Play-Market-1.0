package com.example.controller.runController;

import com.example.model.game.Game;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.strategy.educationalGame.Subject;

import javax.swing.*;

public class RunMazeGame extends RunCommandFactory {
    @Override
    public void runGame(Game game) {
        JFrame frame = new JFrame();
        Object[] possibilities = Mode.values();
        Mode mode = (Mode) JOptionPane.showInputDialog(
                frame,
                "Mode",
                "Choose mode",
                JOptionPane.PLAIN_MESSAGE,null,
                possibilities,
                Subject.values()[0]);
        JOptionPane.showMessageDialog(frame,
                game.runGame(mode.getType()));
    }
}
