package com.example.service.serializeService;

import com.example.model.game.Game;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface SerializeService {
    ArrayList<Game> read() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, ParseException;
    void write(ArrayList<Game> games) throws IOException;
}
