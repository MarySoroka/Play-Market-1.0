package serializer;

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
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SpecificSerializationWriteReadTest  extends Assert {
    private final static String filepath = "test.spec";
    private Scanner scanner;
    @Test
    public void whenReadWriteFileSpecificSerialization_isRight() throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, ParseException {
        Game game = new EducationalGame("mortal","author",
                new SimpleDateFormat("dd/mm/yyyy").parse("01/04/2009"),
                new Graphics(GraphicsMode.THREED), OnlineStatus.ONLINE, Mode.EASY );

        File fi = new File(filepath);
        FileOutputStream f = new FileOutputStream(fi);
        PrintWriter file = new PrintWriter(f);
        file.print(game.toString());
        file.close();

        FileInputStream fileInputStream = new FileInputStream(filepath);
        scanner = new Scanner(fileInputStream);
        GameController gameController = new GameController();
        Game testGame = null;
        while (scanner.hasNext()) {
            Game g = gameController.createGame(Class.forName(scanner.next()));
            testGame =getObject(g.getClass(), g);
        }
        scanner.close();


        assertEquals(game.getType(),testGame.getType());
        assertEquals(game.getName(),testGame.getName());
        assertEquals(game.getGraphics().getGraphicsMode(),testGame.getGraphics().getGraphicsMode());
        assertEquals(game.getOnlineStatus(),testGame.getOnlineStatus());
        assertEquals(game.getAuthor(), testGame.getAuthor());
        assertEquals(game.getReleaseDate(),testGame.getReleaseDate());
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

}
