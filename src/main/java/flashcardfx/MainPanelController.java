package flashcardfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.CreatorModeManager;
import model.MODE;
import model.QuestionInList;
import model.QuestionManager;
import model.StudyModeManager;

public class MainPanelController {

    @FXML
    private Button studyModeButton;

    @FXML
    private Button createModeButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private ListView<QuestionInList> questionListView;

    @FXML
    private AnchorPane switchAnchorPane;
    
    private ModeLoader modeLoader = new ModeLoader();
    
    //Initialize first load of questions via service task
    private QuestionManager questionManager = new QuestionManager();
    
    private MODE mode = MODE.STUDY; 

	private CreatorModeManager creatorModeManager;
    
    @FXML
    private void initialize(){
    	
    	//Check Systems for Systems Start-Up
    	
    	
    	//Customization of ListView to work with objects in callback it will use toString From object
    	questionListView.setCellFactory(new Callback<ListView<QuestionInList>, ListCell<QuestionInList>>() {
			@Override
			public ListCell<QuestionInList> call(ListView<QuestionInList> param) {
				
				ListCell<QuestionInList> myQuestion = new ListCell<QuestionInList>(){
					@Override
                    protected void updateItem(QuestionInList t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.toString());
                        }else{
                        	//When deleting
                        	setText("");
                        }
                    }
				};
				return myQuestion;
			}
		});
    	//Load List View
    	questionManager.setQuestionListView(questionListView);
    	//Prepare List View
    	prepareStudyMode();
    }
    
    @FXML
    private void activateCreateMode(ActionEvent event) {
    	if(mode.equals(MODE.STUDY)){
        	mode= MODE.CREATOR;
        	prepareCreatorMode();
    	}else if(mode.equals(MODE.CREATOR)){
    		this.creatorModeManager.createQuestion();
    	}
    }
    
    @FXML
    private void activateStudyMode(ActionEvent event) {
    	mode= MODE.STUDY;
    	prepareStudyMode();
    	questionManager.setSelectedQuestionToStudyMode();
    } 
    
    @FXML
    private void deleteButtonOnAction(ActionEvent event){
    	questionManager.deleteQuestion();
    }
    
    
	private void prepareStudyMode() {
		createModeButton.setText("Edit");
		DeleteButton.setVisible(false);
		StudyModeController studyModeController = modeLoader.loadStudyMode(switchAnchorPane);
		StudyModeManager studyModeManager = new StudyModeManager();
    	studyModeManager.setMainPanelController(this);
    	studyModeManager.setStudyModeController(studyModeController);
    	questionManager.setStudyModeManager(studyModeManager);
    	//TODO Activate this 
    	//studyModeManager.setQuestionManager(questionManager);
	}
	
	private void prepareCreatorMode(){
    	DeleteButton.setVisible(true);
    	createModeButton.setText("Create New");
    	QuestionCreatorController questionCreatorController =  modeLoader.loadCreatorMode(switchAnchorPane);
    	CreatorModeManager creatorModeManager = new CreatorModeManager();
    	creatorModeManager.setCreatorModeController(questionCreatorController);
    	creatorModeManager.setQuestionManager(questionManager);
    	creatorModeManager.init();
    	this.creatorModeManager=creatorModeManager;
    }

	
	public ListView<QuestionInList> getQuestionView(){
		return questionListView;
	}
}