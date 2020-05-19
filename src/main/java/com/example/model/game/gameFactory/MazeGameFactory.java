package com.example.model.game.gameFactory;

import com.example.model.game.Game;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.game.gameEngine.GraphicsMode;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.skillAndActionGame.mazeGame.MazeGame;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MazeGameFactory  implements GameFactory{
    @Override
    public Game createGame(String[] data) {
        try {
            return new MazeGame(data[0],data[1],new SimpleDateFormat("dd/mm/yyyy").parse(data[2]),
                    new Graphics(GraphicsMode.getByName(data[3]))
                    , OnlineStatus.getInstance(data[4]), Integer.parseInt(data[5]), Mode.getByName(data[6]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game createSerializeGame() {
        return new MazeGame();
    }
}