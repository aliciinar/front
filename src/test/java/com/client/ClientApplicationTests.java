package com.client;

import com.client.pane.Login;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {

	/**
	 * Open login screen
	 */

	@Test
	void addScreens() {

	}

	@Test
	void loginScreen() {
		Stage stage = new Stage();
		Scene scene = new Scene(new Login(800. , 800.));
		stage.setScene(scene);
		stage.show();

	}

}
