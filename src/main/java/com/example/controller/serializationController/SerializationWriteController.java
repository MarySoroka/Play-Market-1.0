package com.example.controller.serializationController;

import com.example.model.game.Game;
import com.example.model.serializer.SerializerFactory;
import com.example.model.serializer.SerializerType;
import com.example.service.serializeService.SerializeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class SerializationWriteController {
    private SerializeService serializeService;
    private Map<SerializerType, SerializerFactory> serializer;
    public SerializationWriteController(Map<SerializerType, SerializerFactory> serializer ){
        this.serializer = serializer;
    }
    public void serialize(SerializerType serializerType, ArrayList<Game> games) throws IOException {
        this.serializeService = this.serializer.get(serializerType);
        this.serializeService.write(games);
    }
}
