package flashcardfx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class QuestionPane {

	public Pane load(){
		try {
			return (Pane)FXMLLoader.load(getClass().getResource("/mainPanel.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			return null;
	}
}