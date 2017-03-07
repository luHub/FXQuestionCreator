package flashcardfx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import questions.StudyModeManager;

public class ModeLoader {

	private StudyModeManager questionManager = new StudyModeManager();
	
	public  StudyModeController loadStudyMode(AnchorPane parent){
		StudyModeController studyModeController=null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/studyMode.fxml"));
			Node node = loader.load();
			removeAllParentChildren(parent);
			AnchorPane.setBottomAnchor(node, 0d);
			AnchorPane.setTopAnchor(node, 0d);
			AnchorPane.setLeftAnchor(node, 0d);
			AnchorPane.setRightAnchor(node, 0d);
			parent.getChildren().add(node);
			studyModeController = loader.<StudyModeController>getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studyModeController;
	}

	public QuestionCreatorController loadCreatorMode(AnchorPane parent) {
		QuestionCreatorController creatorModeController=null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/questionCreator.fxml"));
			Node node = loader.load();
			removeAllParentChildren(parent);
			AnchorPane.setBottomAnchor(node, 0d);
			AnchorPane.setTopAnchor(node, 0d);
			AnchorPane.setLeftAnchor(node, 0d);
			AnchorPane.setRightAnchor(node, 0d);
			parent.getChildren().add(node);
			creatorModeController = loader.<QuestionCreatorController>getController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return creatorModeController;
	}
	
	private void removeAllParentChildren(AnchorPane parent){
		if(!parent.getChildren().isEmpty()){
				parent.getChildren().clear();
		}
	}
	
}
