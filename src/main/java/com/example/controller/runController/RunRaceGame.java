package com.example.controller.runController;

import com.example.model.game.Game;
import com.example.model.skillAndActionGame.raceGame.Car;
import com.example.model.strategy.educationalGame.Subject;

import javax.swing.*;

public class RunRaceGame extends RunCommandFactory {
    @Override
    public void runGame(Game game) {
        JFrame frame = new JFrame();
        Object[] possibilities = Car.values();
        Car car = (Car) JOptionPane.showInputDialog(
                frame,
                "Car",
                "Choose car",
                JOptionPane.PLAIN_MESSAGE,null,
                possibilities,
                Subject.values()[0]);
        JOptionPane.showMessageDialog(frame,
                game.runGame(car.name()));
    }
}
