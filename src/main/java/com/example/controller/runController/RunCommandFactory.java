package com.example.controller.runController;

import com.example.model.game.Game;

import javax.swing.*;

public abstract class RunCommandFactory extends JFrame implements RunCommand {
    abstract public void runGame(Game game);
}
