package com.example.controller.runController;

import com.example.model.game.Game;
import com.example.model.strategy.educationalGame.Subject;

import javax.swing.*;

public class RunEducationalGame extends RunCommandFactory{
    @Override
    public void runGame(Game game) {
        JFrame frame = new JFrame();
        Object[] possibilities = Subject.values();
        Subject subject = (Subject) JOptionPane.showInputDialog(
                frame,
                "Subject",
                "Choose subject",
                JOptionPane.PLAIN_MESSAGE,null,
                possibilities,
                Subject.values()[0]);

        JOptionPane.showMessageDialog(frame,
                game.runGame(subject.name()));
    }
}
