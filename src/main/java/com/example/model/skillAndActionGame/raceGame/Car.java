package com.example.model.skillAndActionGame.raceGame;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public enum Car implements Serializable {
    BMW("bmw"),
    AUDI("audi"),
    MERCEDES("mercedes");

    Car() {
    }
    @JacksonXmlProperty(localName = "status")
    private String status;
    Car(String status) {
        this.status = status;
    }
    public static Car getInstance(String name){
        for (Car car:
                Car.values()) {
            if (car.name().equals(name)){
                return car;
            }
        }
        return null;
    }
}
