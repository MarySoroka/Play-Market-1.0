package com.example.model.serializer;

import com.example.model.game.Game;
import com.example.service.serializeService.SerializeService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public abstract class SerializerFactory implements SerializeService {
    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    private ArrayList<Game> games;
    public abstract ArrayList<Game> read() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, ParseException;
    public abstract void write( ArrayList<Game> games) throws IOException;
}
