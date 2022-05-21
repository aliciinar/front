package com.client.controller.spaceCreate;

import com.client.game.Spaces.ISpace;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NormalSpaceController extends SpaceFxmlType{
    @Override
    public Pane createSpace(ISpace space) {
        try {
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/com.client.controller/normalSpace.fxml"));
            Pane property = cardLoader.load();
            return  property;
        }catch (IOException e){

        }
        return  null;
    }
}
