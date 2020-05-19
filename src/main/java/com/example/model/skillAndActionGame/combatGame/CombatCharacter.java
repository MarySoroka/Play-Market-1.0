package com.example.model.skillAndActionGame.combatGame;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum CombatCharacter implements Serializable {
    CETRION("cetrion"),
    GERAS("geras"),
    KOLLECTOR("kollector"),
    KRONIKA("kronika");

    @Override
    public String toString() {
        return character;
    }

    @JsonValue
    public String getCharacter() {
        return character;
    }

    private String character;
    CombatCharacter(String character) {
        this.character = character;
    }


    public static CombatCharacter getByName(String data){
        for (CombatCharacter c:
             CombatCharacter.values()) {
            if (c.character.equals(data)){
                return c;
            }
        }
        return null;
    }
}
