package com.example.model.game.gameEngine;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public enum GraphicsMode implements Serializable {
    THREED("3D"),
    TWOD("2D");

    @Override
    public String toString() {
        return type;
    }

    GraphicsMode() {
    }
    @JsonValue
    public String getType() {
        return type;
    }

    @JacksonXmlProperty(localName = "type")
    private String type;
    GraphicsMode(String type) {
        this.type = type;
    }
    public static GraphicsMode getByName(String name){
        for (GraphicsMode g:
             GraphicsMode.values()) {
            if (g.type.equalsIgnoreCase(name)){
                return g;
            }
        }
        return null;
    }
    public static String[] getListOfTypes(){
        return new String[]{GraphicsMode.THREED.getType(),GraphicsMode.TWOD.getType()};
    }
}
