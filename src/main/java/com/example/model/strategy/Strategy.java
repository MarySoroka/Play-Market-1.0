package com.example.model.strategy;

import com.example.model.game.Game;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.skillAndActionGame.Mode;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.Date;

public abstract class Strategy extends Game implements Serializable {
    public Mode getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = Mode.getByName(mode);
    }

    @Override
    public String toString() {
        return " mode " + mode + super.toString();
    }

    public Strategy() {
    }
    @JacksonXmlProperty(localName = "mode")
    private Mode mode;

    public Strategy(String type, String name, String author, Date releaseDate, Graphics graphics, OnlineStatus onlineStatus, Mode mode) {
        super(type,name, author, releaseDate, graphics, onlineStatus);
        this.mode = mode;
    }


    public abstract String runGame(String userData);
    public abstract void startNewGame(String userData);
}
