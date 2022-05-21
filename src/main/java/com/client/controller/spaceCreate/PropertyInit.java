package com.client.controller.spaceCreate;

import com.client.game.Spaces.ISpace;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

import java.io.IOException;


public class PropertyInit extends SpaceFxmlType{

    @FXML
    Label name;
    @FXML
    Label price;

    private  String nameSpace;
    private  String  priceSpace;




        @Override
    public Pane createSpace(ISpace space) {
        try {
            System.out.println("PropertyOluştur");
            FXMLLoader cardLoader = new FXMLLoader(getClass().getResource("/com.client.controller/propertySpace.fxml"));
            cardLoader.setController(this);
            nameSpace = space.getName();
            priceSpace = "5";

            Pane property = cardLoader.load();
            return  property;
        }catch (IOException e){
            System.out.println(e);
        }
        return  null;

    }

    @FXML
    public void initialize() {
        System.out.println("isim ver");
       name.setText(nameSpace);
    }

}
