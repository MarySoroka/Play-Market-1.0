package com.example.model.skillAndActionGame.mazeGame;

import com.example.model.skillAndActionGame.Mode;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public enum Labyrinth  implements Serializable {
    FOREST("forest", Mode.EASY),
    LAKE("lake",Mode.MEDIUM),
    HOME("home",Mode.HARD);

    public String getType() {
        return type;
    }
    @JacksonXmlProperty(localName = "type")
    private String type;
    @JacksonXmlProperty(localName = "mode")
    private Mode mode;

    Labyrinth() {
    }

    Labyrinth(String type, Mode mode) {
        this.mode = mode;
        this.type = type;
    }
    public static Labyrinth getByName(Mode mode){
        for (Labyrinth l:
             Labyrinth.values()) {
            if (l.mode.equals(mode)){
                return l;
            }
        }
        return null;
    }
}
