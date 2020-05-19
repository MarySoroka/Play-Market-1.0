package serializer;

import com.example.model.game.Game;
import com.example.model.game.GamesList;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.game.gameEngine.GraphicsMode;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.skillAndActionGame.combatGame.CombatGame;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.Assert;
import org.junit.Test;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class XmlSerializationWriteReadTest extends Assert {
    private final static String filepath = "test.xml";

    @Test
    public void whenReadWriteFileXml_isRight() throws ParseException, FileNotFoundException {
        Game game = new CombatGame("mortal","author",
                new SimpleDateFormat("dd/mm/yyyy").parse("01/04/2009"),
                new Graphics(GraphicsMode.THREED), OnlineStatus.ONLINE, 10, Mode.EASY );

        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        XMLEncoder xml = new XMLEncoder(new BufferedOutputStream(fileOutputStream));
        xml.writeObject(game);
        xml.close();
        XMLDecoder xmlDecoder= new XMLDecoder(new BufferedInputStream(new FileInputStream(filepath)));
        Game testGame = (Game) xmlDecoder.readObject();
        xmlDecoder.close();

        assertEquals(game.getType(),testGame.getType());
        assertEquals(game.getName(),testGame.getName());
        assertEquals(game.getGraphics().getGraphicsMode(),testGame.getGraphics().getGraphicsMode());
        assertEquals(game.getOnlineStatus(),testGame.getOnlineStatus());
        assertEquals(game.getAuthor(), testGame.getAuthor());
        assertEquals(game.getReleaseDate(),testGame.getReleaseDate());
    }
    @Test
    public void whenReadFileXml_isRight() throws ParseException, IOException {
        SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy");
        s.setTimeZone(TimeZone.getTimeZone("UTC"));
        Game game = new CombatGame("mortal","author",
                s.parse("02/05/2001"),
                new Graphics(GraphicsMode.THREED), OnlineStatus.ONLINE, 10, Mode.EASY );
        Game game1 = new CombatGame("mortal","author",
                s.parse("02/05/2001"),
                new Graphics(GraphicsMode.THREED), OnlineStatus.ONLINE, 10, Mode.EASY );
        ArrayList<Game> games = new ArrayList<>();
        games.add(game);
        games.add(game1);
        GamesList gamesList = new GamesList(games);


        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
        ObjectMapper xml = new ObjectMapper();
        xml.enable(SerializationFeature.INDENT_OUTPUT);
        xml.enableDefaultTyping();
        xml.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        xml.registerModule(jaxbAnnotationModule);

        String resXml = xml.writeValueAsString(gamesList);
        fileOutputStream.write(resXml.getBytes());
        System.out.println(resXml);
        fileOutputStream.close();

        GamesList testGameList = xml.readValue(resXml, GamesList.class);
        Game testGame = testGameList.getGames().get(0);

        assertEquals(game.getType(),testGame.getType());
        assertEquals(game.getName(),testGame.getName());
        assertEquals(game.getGraphics().getGraphicsMode(),testGame.getGraphics().getGraphicsMode());
        assertEquals(game.getOnlineStatus(),testGame.getOnlineStatus());
        assertEquals(game.getAuthor(), testGame.getAuthor());
        assertEquals(game.getReleaseDate().getDate(),testGame.getReleaseDate().getDate());
    }

}
