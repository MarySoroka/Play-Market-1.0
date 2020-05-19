package com.example.model.skillAndActionGame.combatGame;

import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.skillAndActionGame.SkillActionGame;
import com.example.service.skillAndActionGameService.CombatGameService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Date;

public class CombatGame extends SkillActionGame implements CombatGameService{
    @JacksonXmlProperty(localName = "combatCharacter")
    private CombatCharacter combatCharacter;

    @JacksonXmlProperty(localName = "enemy")
    private CombatCharacter enemy;

    public Mode getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = Mode.getByName(mode);
    }


    @JacksonXmlProperty(localName = "mode")
    private Mode mode;

    @JsonCreator
    public CombatGame( @JsonProperty("name") String name,
                       @JsonProperty("author") String author,
                       @JsonProperty("releaseDate") Date releaseDate,
                       @JsonProperty("graphics")Graphics graphics,
                       @JsonProperty("onlineStatus")OnlineStatus onlineStatus,
                       @JsonProperty("maxNumberOfPlayers")int maxNumberOfPlayers,
                       @JsonProperty("mode")Mode mode) {

        super("combat",name, author, releaseDate, graphics, onlineStatus, maxNumberOfPlayers);
        setMode(mode.getType());
        this.enemy = (CombatCharacter.values()[(int) (Math.random() * CombatCharacter.values().length)]);
        this.combatCharacter = (CombatCharacter.values()[(int) (Math.random() * CombatCharacter.values().length)]);
    }

    public CombatGame() {
    }

    @Override
    public String toString() {
        return " com.example.model.skillAndActionGame.combatGame.CombatGame " +
                "combatCharacter " + combatCharacter +
                " enemy " + enemy +
                " mode " + mode +
                 super.toString();
    }

    @Override
    public void chooseCharacter(CombatCharacter combatCharacter) {
        if (combatCharacter!=null){
        this.combatCharacter = combatCharacter;}
    }

    @Override
    public void generateEnemy() {
        this.enemy = (CombatCharacter.values()[(int) (Math.random() * CombatCharacter.values().length)]);
    }

    @Override
    public String runGame(String userData) {
        super.getGameEngine().runEngine();
        startNewGame(userData);
        return "Start game successfully\n Your character "+userData+" and your enemy "+this.enemy.name();
    }

    @Override
    public void startNewGame(String userData) {
        chooseCharacter(CombatCharacter.getByName(userData));
        generateEnemy();

    }


    public String getCombatCharacter() {
        return this.combatCharacter.getCharacter();
    }

    public String getEnemy() {
        return enemy.getCharacter();
    }
}
