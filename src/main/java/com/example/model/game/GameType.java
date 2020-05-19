package com.example.model.game;

public enum GameType {
    COMBAT("combat"),
    RACE("race"),
    CHANCE("chance"),
    EDUCATIONAL("educational"),
    MAZE("maze");

    @Override
    public String toString() {
        return "com.example.model.game.GameType " +
                " type " + type ;
    }

    public String getType() {
        return type;
    }

    private String type;
    GameType(String type) {
        this.type = type;
    }
    public static GameType getByName(String name){
        for (GameType g:
             GameType.values()) {
            if (g.type.equals(name)){
                return g;
            }
        }
        return null;
    }
    public static String[] getListOfTypes(){
        return new String[]{GameType.COMBAT.getType(),GameType.RACE.getType(),GameType.EDUCATIONAL.getType(),GameType.MAZE.getType(),GameType.CHANCE.getType()};
    }
}
