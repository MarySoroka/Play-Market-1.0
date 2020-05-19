package com.example.model.strategy.educationalGame;

import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.strategy.Strategy;
import com.example.service.strategyService.EducationalGameService;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Date;

@JacksonXmlRootElement(localName = "educationalGame")
public class EducationalGame extends Strategy implements EducationalGameService {
    @JacksonXmlProperty(localName = "subject")
    private Subject subject;

    public EducationalGame(String name, String author, Date releaseDate, Graphics graphics, OnlineStatus onlineStatus, Mode mode) {
        super("educational",name, author, releaseDate, graphics, onlineStatus, mode);
    }

    public EducationalGame() {
        super();
    }

    @Override
    public String toString() {
        return " com.example.model.strategy.educationalGame.EducationalGame " +
                " test " + test +
                " materialSource " + materialSource + super.toString();
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    @JacksonXmlProperty(localName = "test")
    private String test;

    public String getMaterialSource() {
        return materialSource;
    }
    @JacksonXmlProperty(localName = "materialSource")
    private String materialSource;

    @Override
    public void startNewTest() {
        setTest("test "+this.subject);
    }
    @Override
    public void openMaterial(){
        System.out.println(getMaterialSource());
    }
    @Override
    public String runGame(String userData){
        super.getGameEngine().runEngine();
        this.subject = Subject.getByName(userData.toLowerCase());
        startNewGame(userData);
        return "You start " +getTest();
    }
    @Override
    public void startNewGame(String userData){
         startNewTest();
    }


    public String getSubject() {
        return this.subject.name();
    }
}
