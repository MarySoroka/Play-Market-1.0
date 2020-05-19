package com.example.model.game;

import com.example.model.game.gameEngine.Engine;
import com.example.model.game.gameEngine.GameEngine;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.skillAndActionGame.combatGame.CombatGame;
import com.example.model.skillAndActionGame.mazeGame.MazeGame;
import com.example.model.skillAndActionGame.raceGame.RaceGame;
import com.example.model.strategy.educationalGame.EducationalGame;
import com.example.model.strategy.gameOfChance.GameOfChance;
import com.example.service.gameService.GameService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "typeOfGame")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CombatGame.class, name = "combatGame"),
        @JsonSubTypes.Type(value = MazeGame.class, name = "mazeGame"),
        @JsonSubTypes.Type(value = EducationalGame.class, name = "educationalGame"),
        @JsonSubTypes.Type(value = RaceGame.class, name = "raceGame"),
        @JsonSubTypes.Type(value = GameOfChance.class, name = "chanceGame")
})
public abstract class Game implements GameService, Serializable {
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "author")
    private String author;
    @JacksonXmlProperty(localName = "releaseDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/mm/yyyy")
    private Date releaseDate;

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine() {
        this.gameEngine = new Engine();
    }
    @JsonIgnore
    transient private GameEngine gameEngine;
    @JacksonXmlProperty(localName = "graphics")
    private Graphics graphics;
    @JacksonXmlProperty(localName = "onlineStatus")
    private OnlineStatus onlineStatus;

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return " name " + name  +
                " author " + author +
                " releaseDate " + new SimpleDateFormat("dd/mm/yyyy").format(releaseDate) +
                " gameEngine "+
                " graphics " + graphics +
                " onlineStatus " + onlineStatus +
                " type " + type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Game() {
        setGameEngine();
    }

    private String type;
    public Game(String type, String name, String author, Date releaseDate, Graphics graphics, OnlineStatus onlineStatus) {
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.graphics = graphics;
        this.onlineStatus = onlineStatus;
        this.type = type;
        this.gameEngine = new Engine();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        try {
            this.releaseDate = new SimpleDateFormat("dd/mm/yyyy").parse(releaseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Graphics getGraphics() {
        return graphics;
    }

    public void setGraphics() {
        this.graphics = new Graphics();
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus)
    {
        this.onlineStatus = OnlineStatus.getInstance(onlineStatus);
    }

    public abstract String runGame(String userData);
    public abstract void startNewGame(String userData);

}
