package questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import flashcardfx.QuestionCreatorController;
import meta.flashcardsdto.Answer;
import meta.flashcardsdto.Choice;
import meta.flashcardsdto.MultipleChoiceFlashCardDTO;
import meta.flashcardsdto.Question;

public class CreatorModeManager {

	private QuestionCreatorController questionCreatorController;
	private QuestionManager questionManager;
	private CREATOR_STATUS creatorStatus = CREATOR_STATUS.EDIT;
	
	public void init(){
		creatorStatus=CREATOR_STATUS.EDIT;
		//Add this to Controller
		this.questionCreatorController.setCreatorModeManager(this);
		this.questionManager.setQuestionCreatorManager(this);
		//Initialize Controller
		this.questionCreatorController.init();
	}

	public void setCreatorModeController(QuestionCreatorController questionCreatorController) {
		this.questionCreatorController = questionCreatorController;
	}
	
	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}
	
	public void createNewQuestion(){
		this.creatorStatus=CREATOR_STATUS.NEW;
	}
		
	public void saveQuestion() {
	}
	
	private Optional<MultipleChoiceFlashCardDTO> loadSelectedQuestion() {
		if(!questionManager.getSeletectedQuestion().isPresent()){
			this.creatorStatus=CREATOR_STATUS.NEW;
		}
		return questionManager.getSeletectedQuestion();
	}
	
	private void asignNewIdToQuestion() {
		//Get last question ID  
	}

	// Read
	public Question readQuestion(){
		Optional<MultipleChoiceFlashCardDTO> currentMultipleChoiceFlashCardOpt =  loadSelectedQuestion();
		if(currentMultipleChoiceFlashCardOpt.isPresent()){
			return currentMultipleChoiceFlashCardOpt.get().getQuestion();
		}else{
			Question question = new Question();
			question.setQuestion("");
			return question;
		}
	}
	public List<Choice> readQuestionChoices(){
		Optional<MultipleChoiceFlashCardDTO> currentMultipleChoiceFlashCardOpt =  loadSelectedQuestion();
		if(currentMultipleChoiceFlashCardOpt.isPresent()){
			 return currentMultipleChoiceFlashCardOpt.get().getChoiceList();
		}else{
			List<Choice> questionChoices = new ArrayList();
			questionChoices.add(new Choice(1,""));
			questionChoices.add(new Choice(2,""));
			questionChoices.add(new Choice(3,""));
			questionChoices.add(new Choice(4,""));
			return questionChoices;
		}
	}
	public Answer readAnswer(){
		Optional<MultipleChoiceFlashCardDTO> currentMultipleChoiceFlashCardOpt = loadSelectedQuestion();
		if(currentMultipleChoiceFlashCardOpt.isPresent()){
			return currentMultipleChoiceFlashCardOpt.get().getAnswer();
		}else{
			Answer answer = new Answer();
			answer.setAnswer("");
			return answer;
		}
	}

	
	//Write
	public void writeQuestion(String desc,String type) {
		Question question = new Question();
		question.setQuestion(desc);
		question.setType(type);
		if (creatorStatus.equals(CREATOR_STATUS.EDIT)) {
			this.questionManager.overwriteQuestion(question);
		} 
	} 
	
	public void writeAnswer(String answerText,boolean isAtrue,boolean isBtrue,boolean isCtrue,boolean isDtrue){
		Answer answer = new Answer();
		answer.setAnswer(answerText);
		answer.setAtrue(isAtrue);
		answer.setBtrue(isBtrue);
		answer.setCtrue(isCtrue);
		answer.setDtrue(isDtrue);
		if (creatorStatus.equals(CREATOR_STATUS.EDIT)) {
			this.questionManager.overwriteAnswer(answer);
		} 
	}
	
	public void writeQuestionChoiceA(String optionA) {
			Choice choice = new Choice();
			choice.setDescription(optionA);
			choice.setId(1);
			writeQuestionChoice(choice);
	}

	public void writeQuestionChoiceB(String optionB) {
			Choice choice = new Choice();
			choice.setDescription(optionB);
			choice.setId(2);
			writeQuestionChoice(choice);
	}

	public void writeQuestionChoiceC(String optionC) {
			Choice choice = new Choice();
			choice.setDescription(optionC);
			choice.setId(3);
			writeQuestionChoice(choice);
	}

	public void writeQuestionChoiceD(String optionD) {
			Choice choice = new Choice();
			choice.setDescription(optionD);
			choice.setId(4);
			writeQuestionChoice(choice);
	}
	
	
	private void writeQuestionChoice(Choice choice){
		if(creatorStatus.equals(CREATOR_STATUS.EDIT)){
			this.questionManager.overwriteChoice(choice);
		}
	}

	public void setCurrentQuesion() {
		this.questionCreatorController.setCurrentQuesion();
	}
	
	public void createQuestion(){
		this.questionManager.createNewQuestion();
		this.questionCreatorController.createNewQuestion();
	}

}
