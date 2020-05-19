package com.example.model.game.gameEngine;

import java.io.Serializable;

public class Engine implements GameEngine, Serializable {

    public Engine() {
    }

    @Override
    public void runEngine(){
        System.out.println("Engine Run");
    }
}
