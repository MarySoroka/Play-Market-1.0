package com.example.model.game.gameFactory;


import com.example.model.game.Game;
import com.example.model.game.GameType;
import com.example.model.skillAndActionGame.combatGame.CombatGame;
import com.example.model.skillAndActionGame.mazeGame.MazeGame;
import com.example.model.skillAndActionGame.raceGame.RaceGame;
import com.example.model.strategy.educationalGame.EducationalGame;
import com.example.model.strategy.gameOfChance.GameOfChance;

import java.util.Objects;

public class GameController {
    private static GameFactory gameFactory;
    public Game createGame(Class<?> cl){
        if (CombatGame.class.equals(cl)) {
            gameFactory = new CombatGameFactory();
        } else if (MazeGame.class.equals(cl)) {
            gameFactory = new MazeGameFactory();
        } else if (RaceGame.class.equals(cl)) {
            gameFactory = new RaceGameFactory();
        } else if (EducationalGame.class.equals(cl)) {
            gameFactory = new EducationalGameFactory();
        } else if (GameOfChance.class.equals(cl)) {
            gameFactory = new GameOfChanceFactory();
        }
        return gameFactory.createSerializeGame();
    };
    public Game createSpecificGame(String[] data){
        switch (Objects.requireNonNull(GameType.getByName(data[0]))){
            case COMBAT: gameFactory = new CombatGameFactory(); break;
            case MAZE: gameFactory = new MazeGameFactory();break;
            case RACE: gameFactory = new RaceGameFactory();break;
            case EDUCATIONAL: gameFactory = new EducationalGameFactory();break;
            case CHANCE: gameFactory = new GameOfChanceFactory();break;
        }
        return gameFactory.createGame(new String[]{data[1],data[2],data[3],data[4],data[5],data[6],data[7]});
    }
}
