package com.example.controller.runController;

import com.example.model.game.Game;
import com.example.model.skillAndActionGame.combatGame.CombatCharacter;

import javax.swing.*;

public class RunCombatGame extends RunCommandFactory {
    @Override
    public void runGame(Game game) {
        JFrame frame = new JFrame();
        Object[] possibilities = CombatCharacter.values();
        CombatCharacter character = (CombatCharacter) JOptionPane.showInputDialog(
                frame,
                "choose character for the game",
                "Choose character",
                JOptionPane.PLAIN_MESSAGE,null,
                possibilities,
                CombatCharacter.values()[0]);

        JOptionPane.showMessageDialog(frame,
                game.runGame(character.name()));
    }
}
