package com.client.pane.game.space.spaceCreation;


import java.util.List;

public interface ISpaceCreatorFactory {
    /**
     *
     * spaces created with factory design pattern.
     */

    List<NormalCreation.GridCord> createSpaces();
}
