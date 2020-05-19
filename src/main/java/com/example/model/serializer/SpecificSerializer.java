package com.example.model.serializer;

import com.example.controller.serializationController.FilepathController;
import com.example.model.game.Game;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Engine;
import com.example.model.game.gameEngine.GameEngine;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.game.gameEngine.GraphicsMode;
import com.example.model.game.gameFactory.GameController;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.skillAndActionGame.combatGame.CombatCharacter;
import com.example.model.skillAndActionGame.combatGame.CombatGame;
import com.example.model.skillAndActionGame.mazeGame.Labyrinth;
import com.example.model.skillAndActionGame.mazeGame.MazeGame;
import com.example.model.skillAndActionGame.raceGame.Car;
import com.example.model.skillAndActionGame.raceGame.RaceGame;
import com.example.model.skillAndActionGame.raceGame.Trace;
import com.example.model.strategy.educationalGame.EducationalGame;
import com.example.model.strategy.educationalGame.Subject;

import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SpecificSerializer extends SerializerFactory {
    private final static String filepath = "game/gamesSer.spec";
    private FileInputStream fileInputStream = new FileInputStream(filepath);
    private Scanner scanner = new Scanner(fileInputStream);
    public SpecificSerializer() throws FileNotFoundException {
    }

    @Override
    public ArrayList<Game> read() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, ParseException {
        FilepathController.action = "read";
        ArrayList<Game> games = new ArrayList<>();
        GameController gameController = new GameController();
        while (scanner.hasNext()) {
            Game g = gameController.createGame(Class.forName(scanner.next()));
            games.add(getObject(g.getClass(), g));
        }
        return games;
    }

    public Game getObject(Class<?> o, Game ob) throws NoSuchFieldException, IllegalAccessException, ParseException {
        Class<?> obj = o;
        while (obj != null) {
            int i = 0;
            while (i < obj.getDeclaredFields().length) {
                Field field = obj.getDeclaredField(scanner.next());
                field.setAccessible(true);
                if (field.getType().equals(String.class)) {
                    field.set(ob, scanner.next());
                } else if (field.getType().equals(int.class)) {
                    field.setInt(ob, scanner.nextInt());
                } else if (field.getType().equals(CombatCharacter.class)) {
                    field.set((CombatGame) ob, CombatCharacter.getByName(scanner.next()));
                } else if (field.getType().equals(Subject.class)) {
                    field.set((EducationalGame) ob, Subject.getByName(scanner.next()));
                } else if (field.getType().equals(OnlineStatus.class)) {
                    field.set(ob, OnlineStatus.getInstance(scanner.next()));
                } else if (field.getType().equals(Graphics.class)) {
                    field.set(ob, new Graphics(GraphicsMode.getByName(scanner.next())));
                } else if (field.getType().equals(GameEngine.class)) {
                    field.set(ob, new Engine());
                } else if (field.getType().equals(Engine.class)) {
                    field.set(ob, new Engine());
                } else if (field.getType().equals(Labyrinth.class)) {
                    field.set((MazeGame) ob, Labyrinth.getByName(Mode.getByName(scanner.next())));
                } else if (field.getType().equals(Car.class)) {
                    field.set((RaceGame) ob, Car.getInstance(scanner.next()));
                } else if (field.getType().equals(Trace.class)) {
                    field.set((RaceGame) ob, Trace.getInstance(scanner.next()));
                } else if (field.getType().equals(Mode.class)) {
                    field.set(ob, Mode.getByName(scanner.next()));
                } else if (field.getType().equals(Date.class)) {
                    field.set(ob, new SimpleDateFormat("dd/mm/yyyy").parse(scanner.next()));
                }
                i++;
            }
            obj = obj.getSuperclass();
        }
        return ob;


    }

    @Override
    public void write(ArrayList<Game> games) throws IOException {
        FilepathController.filepath = filepath;
        FilepathController.type = ".spec";
        FilepathController.action = "write";
        FileOutputStream f = new FileOutputStream(filepath);
        PrintWriter file = new PrintWriter(f);
        for (Game g :
                games) {
            file.print(g.toString());
        }
        file.close();
        f.close();
    }

}
