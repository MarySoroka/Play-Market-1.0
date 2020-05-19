package com.example.model.skillAndActionGame.raceGame;

import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.skillAndActionGame.SkillActionGame;
import com.example.service.skillAndActionGameService.RaceGameService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

@XmlType(propOrder = {"car","trace"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RaceGame extends SkillActionGame implements RaceGameService, Serializable {
    public RaceGame(String name, String author, Date releaseDate, Graphics graphics, OnlineStatus onlineStatus, int maxNumberOfPlayers) {
        super("race",name, author, releaseDate, graphics, onlineStatus, maxNumberOfPlayers);

    }

    public RaceGame() {

    }

    @Override
    public String toString() {
        return " com.example.model.skillAndActionGame.raceGame.RaceGame " +
                " car " + car +
                " trace " + trace +
                 super.toString();
    }

    public void setCar(String car) {
        this.car = Car.getInstance(car);
    }
    @JacksonXmlProperty(localName = "car")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Car car;
    public void setTrace(String trace) {
        this.trace = Trace.valueOf(trace);
    }
    @JacksonXmlProperty(localName = "trace")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Trace trace;



    @Override
    public String runGame(String userData) {
        super.setGameEngine();
        super.getGameEngine().runEngine();
        startNewGame(userData);
        return "You start on the "+this.trace+" with "+this.car;
    }

    @Override
    public void startNewGame(String userData) {
       setCar(userData);
       generateRacetrack();
    }



    @Override
    public void generateRacetrack() {
        setTrace(String.valueOf(Trace.values()[(int) (Math.random()*Trace.values().length)]));
    }

    public String getCar() {
        return car.name();
    }

    public String getTrace() {
        return trace.name();
    }
}
