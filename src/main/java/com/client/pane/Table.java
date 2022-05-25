package com.client.pane;

import com.client.dto.ScoreDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONArray;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Set up tabs for the scores
 */
public class Table extends TableView {

    private final ObservableList<ScoreDto> data = FXCollections.observableArrayList( new ArrayList<ScoreDto>() );
    public Table(String field1 , String field2 , String field3 ,JSONArray array) {


        TableColumn column1 = new TableColumn(field1);
        column1.setCellValueFactory(new PropertyValueFactory<ScoreDto , String>("name"));

        TableColumn column2 = new TableColumn(field2);
        column2.setCellValueFactory(new PropertyValueFactory<ScoreDto , String>("score"));

        TableColumn column3=new TableColumn(field3);
        column3.setCellValueFactory(new PropertyValueFactory<ScoreDto , String>("date"));

        ObservableList<ScoreDto> data = FXCollections.observableArrayList(createData(array));
        setItems(data);
        getColumns().addAll(column1 , column2 , column3);

    }
    private ArrayList<ScoreDto> createData(JSONArray array){
        ArrayList<ScoreDto> scores = new ArrayList<>();
        try {
            for(int i = 0 ; i < array.length() ; i++) {
                JSONObject score = array.getJSONObject(i);
                JSONObject user = score.getJSONObject("user");
                scores.add(new ScoreDto(user.getString("name") , score.getString("score") , score.getString("sqlDate")));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return scores;


    }
}
