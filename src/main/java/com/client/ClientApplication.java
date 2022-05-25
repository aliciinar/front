package com.client;

import com.client.backenReques.Request;
import com.client.controller.StageInitializer;
import com.google.gson.Gson;
import javafx.application.Application;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class ClientApplication {

	public static Gson gson = new Gson();
	public static HttpClient httpClient = HttpClients.createDefault();

	public static String backend = "https://ceng453-term-project-group-4.herokuapp.com/";
	public static Request request = new Request();

	public static void main(String[] args) {
		Application.launch(StageInitializer.class , args);
	}



}
