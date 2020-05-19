package com.example.model.skillAndActionGame.mazeGame;

import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.skillAndActionGame.SkillActionGame;
import com.example.service.skillAndActionGameService.MazeGameService;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@XmlType(propOrder = {"mode","labyrinth"})
public class MazeGame extends SkillActionGame implements MazeGameService, Serializable {
    public Mode getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = Mode.getByName(mode);
    }

    public MazeGame() {
    }

    @Override
    public String toString() {
        return " com.example.model.skillAndActionGame.mazeGame.MazeGame " +
                "labyrinth " + labyrinth +
                " mode " + mode +
                 super.toString();
    }

    public void setLabyrinth(String labyrinth) {
        this.labyrinth = labyrinth;
    }
    @JacksonXmlProperty(localName = "labyrinth")
    private String labyrinth;
    @JacksonXmlProperty(localName = "mode")
    private Mode mode;

    public MazeGame(String name, String author, Date releaseDate, Graphics graphics, OnlineStatus onlineStatus, int maxNumberOfPlayers, Mode mode) {
        super("maze",name, author, releaseDate, graphics, onlineStatus, maxNumberOfPlayers);
        this.mode = mode;
    }

    @Override
    public void generateLabyrinth() {
        setLabyrinth(Objects.requireNonNull(Labyrinth.getByName(this.mode)).getType());
    }

    @Override
    public String runGame(String userData) {
        super.getGameEngine().runEngine();
        startNewGame(userData);
        return "Your labyrinth "+this.labyrinth;
    }

    @Override
    public void startNewGame(String userData) {
        setMode(userData);
        generateLabyrinth();
    }


    public String getLabyrinth() {
        return labyrinth;
    }
}
