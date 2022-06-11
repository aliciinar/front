package com.client.pane.game.space.spaceCreation;


import java.util.List;

public interface ISpaceCreatorFactory {
    /**
     *
     * spaces created with factory design pattern.
     * If we need different type of creations or want to changes something on the game we just need to add new creation type
     */

    List<NormalCreation.GridCord> createSpaces();
}
