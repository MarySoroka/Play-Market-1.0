package com.example.model.serializer;

import com.example.controller.serializationController.FilepathController;
import com.example.model.game.Game;
import com.example.model.game.GamesList;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

public class XMLSerializer extends SerializerFactory {
    private final static String filepath = "game/gamesSer.xml";

    @Override
    public ArrayList<Game> read() throws IOException {
        FilepathController.action = "read";
        FileInputStream fileInputStream = new FileInputStream(filepath);
        JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
        ObjectMapper xml = new ObjectMapper();
        ArrayList<Game> games = new ArrayList<>();
        GamesList gamesList = xml.readValue(fileInputStream, GamesList.class);
        xml.enable(SerializationFeature.INDENT_OUTPUT);
        xml.enableDefaultTyping();
        xml.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        xml.registerModule(jaxbAnnotationModule);
        return gamesList.getGames();

    }

    @Override
    public void write(ArrayList<Game> games) throws IOException {
        FilepathController.filepath = filepath;
        FilepathController.type = ".xml";
        FilepathController.action = "write";
        SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy");
        s.setTimeZone(TimeZone.getTimeZone("UTC"));
        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        JaxbAnnotationModule jaxbAnnotationModule = new JaxbAnnotationModule();
        ObjectMapper xml = new ObjectMapper();
        xml.enable(SerializationFeature.INDENT_OUTPUT);
        xml.enableDefaultTyping();
        xml.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        xml.registerModule(jaxbAnnotationModule);
        GamesList gamesList = new GamesList(games);
        String resXml = xml.writeValueAsString(gamesList);
        printWriter.write(resXml);
        printWriter.close();
        fileOutputStream.close();

    }
}
