package com.example.model.game.gameEngine;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class Graphics implements Serializable {
    @JacksonXmlProperty(localName = "graphicsMode")
    private GraphicsMode graphicsMode;

    public Graphics() {
    }

    @Override
    public String toString() {
        return  graphicsMode.getType();
    }

    public void setGraphicsMode(String graphicsMode) {
        this.graphicsMode = GraphicsMode.getByName(graphicsMode);
    }

    public GraphicsMode getGraphicsMode() {
        return graphicsMode;
    }

    public Graphics(GraphicsMode graphicsMode) {
        this.graphicsMode = graphicsMode;
    }
}
