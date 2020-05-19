package com.example.model.serializer;

import com.example.controller.serializationController.FilepathController;
import com.example.model.game.Game;
import org.gradle.internal.impldep.com.amazonaws.util.IOUtils;

import java.io.*;
import java.util.ArrayList;

public class BinarySerializer extends SerializerFactory {
    private final String filepath = "game/gamesSer.bin";

    @Override
    public ArrayList<Game> read() throws IOException, ClassNotFoundException {
        FilepathController.action = "read";
        FileInputStream file = new FileInputStream(new File(filepath));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(IOUtils.toByteArray(file));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        ArrayList<Game> games = new ArrayList<>();
        int amountOfObjects = (int) objectInputStream.readObject();
        for (int i = 0; i < amountOfObjects; i++) {
            Game g = (Game) objectInputStream.readObject();
            games.add(g);
        }
        objectInputStream.close();
        return games;

    }

    @Override
    public void write(ArrayList<Game> games) throws IOException {
        FilepathController.filepath = filepath;
        FilepathController.type = ".bin";
        FilepathController.action = "write";
        FileOutputStream file = new FileOutputStream(new File(filepath));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(games.size());
        objectOutputStream.flush();
        for (Game g :
                games) {
            objectOutputStream.writeObject(g);
            objectOutputStream.flush();
        }
        file.write(byteArrayOutputStream.toByteArray());
        objectOutputStream.close();

    }
}
