package com.example.model.game;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public enum OnlineStatus  implements Serializable {
    ONLINE("online"),
    OFFLINE("offline");

    @Override
    public String toString() {
        return status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
    @JacksonXmlProperty(localName = "status")
    private String status;
    OnlineStatus(String status) {
        this.status =status;
    }
    public static OnlineStatus getInstance(String name){
        for (OnlineStatus s:
             OnlineStatus.values()) {
            if (s.status.equals(name)){
                return s;
            }
        }
        return null;
    }
    public static String[] getListOfTypes(){
        return new String[]{OnlineStatus.ONLINE.getStatus(),OnlineStatus.OFFLINE.getStatus()};
    }
}
