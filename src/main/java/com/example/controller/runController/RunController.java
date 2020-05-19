package com.example.controller.runController;

import com.example.model.game.Game;
import com.example.model.game.GameType;

import java.util.Map;

public class RunController implements AppCommand {
    private RunCommand runCommand;

    public RunController(Map<GameType, RunCommandFactory> gameTypeCommand) {
        this.gameTypeCommand = gameTypeCommand;
    }

    private Map<GameType,RunCommandFactory> gameTypeCommand;

    @Override
    public void runSpecificGame(Game game){
        final RunCommand command= this.gameTypeCommand.get(GameType.getByName(game.getType()));
        game.setGameEngine();
        command.runGame(game);
    }
}
