package questions;

import java.util.HashMap;
import java.util.Map;

import flashcardfx.MainPanelController;
import flashcardfx.ModeLoader;
import flashcardfx.StudyModeController;
import meta.flashcardsdto.MultipleChoiceFlashCardDTO;

public class StudyModeManager {
	
	//QuestionMap Responses
	private Map<Integer,UserAnswer> userResponses;

	private StudyModeController studyModeController;
	
	public StudyModeManager(){}

	public void setMainPanelController(MainPanelController mainPanelController) {
		// TODO Auto-generated method stub
	}

	public void setStudyModeController(StudyModeController studyModeController) {
		//Create a new userMap per Session 
		this.userResponses = new HashMap();
		this.studyModeController=studyModeController;
		this.studyModeController.setUserResponses(userResponses);
	}

	public void setCurrentQuestion(MultipleChoiceFlashCardDTO currentQuestion) {
		this.studyModeController.resetQuestion();
		this.studyModeController.setCurrentQuestion(currentQuestion);
		if(userResponses.containsKey(currentQuestion.getId())){
			this.studyModeController.setUserAnsweredQuestion(userResponses.get(currentQuestion.getId()));
		}
	}

	public void setQuestionManager(QuestionManager questionManager) {
		// TODO Auto-generated method stub
		
	}
}