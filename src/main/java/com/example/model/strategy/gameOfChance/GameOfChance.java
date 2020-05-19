package com.example.model.strategy.gameOfChance;

import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.strategy.Strategy;
import com.example.service.strategyService.GameOfChanceService;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(propOrder ={"bet"})

public class GameOfChance extends Strategy implements GameOfChanceService {
    public void setBet(String bet) {
        Bet = Integer.parseInt(bet);
    }
    @JacksonXmlProperty(localName = "bet")
    private int Bet;

    public GameOfChance() {
    }

    public GameOfChance(String name, String author, Date releaseDate, Graphics graphics, OnlineStatus onlineStatus, Mode mode) {
        super("gameOfChance",name, author, releaseDate, graphics, onlineStatus, mode);
    }

    @Override
    public String toString() {
        return " com.example.model.strategy.gameOfChance.GameOfChance " +
                "Bet " + Bet +
                super.toString();
    }

    @Override
    public String runGame(String bet) {
        super.getGameEngine().runEngine();
        startNewGame(bet);
        return "You make a bet "+getBet();
    }

    @Override
    public void startNewGame(String bet) {
         setBet(bet);
    }



    @Override
    public void makeABet() {

    }

    public int getBet() {
        return Bet;
    }
}
