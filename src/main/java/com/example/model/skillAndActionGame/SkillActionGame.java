package com.example.model.skillAndActionGame;

import com.example.model.game.Game;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(propOrder = {"maxNumberOfPlayers", "currentLevel"})
public abstract class SkillActionGame extends Game{
    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaxNumberOfPlayers(String maxNumberOfPlayers) {
        this.maxNumberOfPlayers = Integer.parseInt(maxNumberOfPlayers);
    }
    @JacksonXmlProperty(localName = "maxNumberOfPlayers")
    public int maxNumberOfPlayers;

    public void setCurrentLevel(String currentLevel) {
        this.currentLevel = Integer.parseInt(currentLevel);
    }
    @JacksonXmlProperty(localName = "currentLevel")
    public int currentLevel;

    public SkillActionGame() {
    }

    @Override
    public String toString() {
        return " maxNumberOfPlayers " + maxNumberOfPlayers +
                " currentLevel " + currentLevel +
                super.toString();
    }

    public SkillActionGame(String type, String name, String author, Date releaseDate, Graphics graphics, OnlineStatus onlineStatus, int maxNumberOfPlayers) {
        super(type,name, author, releaseDate, graphics, onlineStatus);
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.currentLevel = 1;
    }
    public abstract String runGame(String userData);
    public abstract void startNewGame(String userData);

    public int getCurrentLevel() {
        return currentLevel;
    }
}
