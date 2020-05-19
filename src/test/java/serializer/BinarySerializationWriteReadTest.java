package serializer;

import com.example.model.game.Game;
import com.example.model.game.OnlineStatus;
import com.example.model.game.gameEngine.Graphics;
import com.example.model.game.gameEngine.GraphicsMode;
import com.example.model.skillAndActionGame.Mode;
import com.example.model.skillAndActionGame.combatGame.CombatGame;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BinarySerializationWriteReadTest extends Assert {
    private final static String filepath = "test.bin";


    @Test
    public void whenWriteReadFileBinaryObject_isRight() throws ParseException, IOException, ClassNotFoundException {
        Game game = new CombatGame("mortal","author",
                new SimpleDateFormat("dd/mm/yyyy").parse("01/04/2009"),
                new Graphics(GraphicsMode.THREED), OnlineStatus.ONLINE, 10, Mode.EASY );

        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(game);
        objectOutputStream.flush();
        fileOutputStream.write(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(filepath);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileInputStream.readAllBytes());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        final Game testGame  = (Game) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        assertEquals(game.getType(),testGame.getType());
        assertEquals(game.getName(),testGame.getName());
        assertEquals(game.getGraphics().getGraphicsMode(),testGame.getGraphics().getGraphicsMode());
        assertEquals(game.getOnlineStatus(),testGame.getOnlineStatus());
        assertEquals(game.getAuthor(), testGame.getAuthor());
        assertEquals(game.getReleaseDate(),testGame.getReleaseDate());
    }
}
