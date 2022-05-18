package com.client;

import com.client.controller.StageInitializer;
import com.google.gson.Gson;
import javafx.application.Application;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


@SpringBootApplication

public class ClientApplication {

	public static Gson gson = new Gson();
	public static HttpClient httpClient = HttpClients.createDefault();
	public static String backend = "http://localhost:8080/";
	public static Request request = new Request();

	public static void main(String[] args) {
		Application.launch(StageInitializer.class , args);
	}



}
