package com.example.model.skillAndActionGame;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum Mode  implements Serializable {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");
    @JsonValue
    public String getType() {
        return type;
    }

    private String type;
    Mode(String type) {
        this.type = type;
    }

    Mode() {
    }


    public static Mode getByName(String name){
        for (Mode g: Mode.values()) {
            if (g.type.equalsIgnoreCase(name)){
                return g;
            }
        }
        return null;
    }
    public static String[] getListOfTypes(){
        return new String[]{Mode.EASY.getType(),Mode.MEDIUM.getType(),Mode.HARD.getType()};
    }
}
