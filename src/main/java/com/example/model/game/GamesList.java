package com.example.model.game;

import com.example.model.game.gameFactory.GameController;
import com.example.service.gameService.GamesListService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;

@JacksonXmlRootElement(localName = "gameList")
public class GamesList implements GamesListService, Serializable {
    public ArrayList<Game> getGames() {
        return games;
    }
    @JacksonXmlProperty(localName = "game")
    @JacksonXmlElementWrapper(useWrapping = false)
    private ArrayList<Game> games = new ArrayList<Game>();
    @JsonCreator
    public GamesList( @JsonProperty("game") ArrayList<Game> games) {
        this.games = games;
    }
    @Override
    public void deleteGame(String name) {
        this.games.removeIf(g -> g.getName().equals(name));
    }

    @Override
    public void addNewGame(String[] data) {
        GameController gameController = new GameController();
        this.games.add(gameController.createSpecificGame(data));
    }


}
