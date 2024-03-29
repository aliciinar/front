package com.client.controller;


import com.client.pane.Login;
import com.client.pane.MainMenu;
import com.client.pane.Register;
import com.client.pane.Session;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class StageController implements ApplicationListener<StageInitializer.StageReadyEvent> {


    private Resource ChartResource;
    private String applicationTitle;
    private ApplicationContext applicationContext;
    private double width , height;
    public static SceneController screenController;

    public StageController(@Value("${spring.application.ui.title}") String applicationTitle, @Value("${spring.application.ui.width}") double width, @Value("${spring.application.ui.height}") double height , ApplicationContext applicationContext){
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
        this.width = width;
        this.height = height;
        Pane pane  = new MainMenu(width , height);
        Scene scene = new Scene(pane);
        screenController = new SceneController(scene);
        screenController.addScreen("MainMenu",pane);
        screenController.addScreen("Login",new Login(width , height));
        screenController.addScreen("Register",new Register(width , height));

    }

    @Override
    public void onApplicationEvent(StageInitializer.StageReadyEvent event) {

        Stage stage = event.getStage();
        stage.setScene(screenController.getScene());
        stage.show();

    }


}
