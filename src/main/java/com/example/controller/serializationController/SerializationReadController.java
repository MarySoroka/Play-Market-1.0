package com.example.controller.serializationController;

import com.example.model.game.Game;
import com.example.model.serializer.SerializerFactory;
import com.example.model.serializer.SerializerType;
import com.example.service.serializeService.SerializeService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

public class SerializationReadController {
    private SerializeService serializeService;
    private Map<SerializerType,SerializerFactory> serializer;
    public SerializationReadController(Map<SerializerType, SerializerFactory> serializer ){
        this.serializer = serializer;
    }
    public ArrayList<Game> serialize(SerializerType serializerType) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, ParseException, IOException {
        this.serializeService = this.serializer.get(serializerType);
        return this.serializeService.read();
    }
}
