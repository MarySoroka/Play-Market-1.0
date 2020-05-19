package com.example.model.game.gameFactory;

import com.example.model.game.Game;

public interface GameFactory {
    Game createGame(String[] data);
    Game createSerializeGame();
}
