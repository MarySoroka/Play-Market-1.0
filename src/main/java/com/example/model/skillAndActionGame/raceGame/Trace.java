package com.example.model.skillAndActionGame.raceGame;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public enum Trace  implements Serializable {
    SPRING("forest"),
    AUTUMN("lake"),
    WINTER("home"),
    SUMMER("summer");


    @JacksonXmlProperty(localName = "type")
    private String type;
    Trace(String type) {
        this.type = type;
    }

    public static Trace getInstance(String name){
        for (Trace trace:
                Trace.values()) {
            if (trace.name().equals(name)){
                return trace;
            }
        }
        return null;
    }
}
