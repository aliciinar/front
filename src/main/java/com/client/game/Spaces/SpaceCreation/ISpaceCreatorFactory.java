package com.client.game.Spaces.SpaceCreation;

import com.client.controller.spaceCreate.SpaceFxmlType;
import com.client.game.Spaces.ISpace;
import javafx.util.Pair;

import java.util.HashMap;

public interface ISpaceCreatorFactory {

    Pair<ISpace[] , HashMap<ISpace, SpaceFxmlType>> createSpaces();
}
