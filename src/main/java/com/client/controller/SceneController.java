package com.client.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

/**
 * This class controls what screen user sees.
 */
public class SceneController {
    private HashMap<String, Pane> screenMap = new HashMap<>(); // holds panes
    private Scene main; // holds the only scene of the game

    /**
     *
     * @param scene scene of the game
     */
    public SceneController(Scene scene) {
        this.main = scene;
    }

    /**
     * Adds new pane to the controller
     * @param name name of the new pane
     * @param pane new pane
     */
    public void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    /**
     * removes existing screen.
     * used to destroy game sessions after game ended or user logged out.
     * @param name
     */
    public void removeScreen(String name){
        screenMap.remove(name);
    }

    /**
     * chane the active pane
     * @param name name of the pane user will see
     */
    public void activate(String name){
        main.setRoot( screenMap.get(name) );
    }

    /**
     *
     * @return the scene of the game
     */
    public Scene getScene(){
        return main;
    }

}
