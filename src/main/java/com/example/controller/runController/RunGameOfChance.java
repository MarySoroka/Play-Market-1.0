package com.example.controller.runController;

import com.example.model.game.Game;

import javax.swing.*;

public class RunGameOfChance extends RunCommandFactory {
    @Override
    public void runGame(Game game) {
        JFrame frame = new JFrame();
        Object[] possibilities = new Integer[] {10,20,30,40,50,60};
        int bet = (int) JOptionPane.showInputDialog(
                frame,
                "Bet",
                "Make your bet",
                JOptionPane.PLAIN_MESSAGE,null,
                possibilities,
                10);
        JOptionPane.showMessageDialog(frame,
                game.runGame(String.valueOf(bet)));
    }
}
