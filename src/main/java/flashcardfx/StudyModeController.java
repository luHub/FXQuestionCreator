package flashcardfx;


import java.util.Map;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import meta.flashcardsdto.Choice;
import meta.flashcardsdto.MultipleChoiceFlashCardDTO;
import model.STATUS;
import model.UserAnswer;

public class StudyModeController {
	
    @FXML
    private TextArea questionTextArea;

    @FXML
    private CheckBox aCheckBox;

    @FXML
    private CheckBox bCheckBox;

    @FXML
    private CheckBox cCheckBox;

    @FXML
    private CheckBox dCheckBox;

    @FXML
    private TextArea answerTextArea;

    @FXML
    private ImageView rightCheckMarkAImage;

    @FXML
    private ImageView wrongCheckMarkAImage;

    @FXML
    private Label aLabel;

    @FXML
    private Label bLabel;

    @FXML
    private ImageView rightCheckMarkBImage;

    @FXML
    private ImageView wrongCheckMarkBImage;

    @FXML
    private ImageView rightCheckMarkCImage;

    @FXML
    private ImageView wrongCheckMarkCImage;

    @FXML
    private Label cLabel;

    @FXML
    private Label dLabel;

    @FXML
    private ImageView rightCheckMarkDImage;

    @FXML
    private ImageView wrongCheckMarkDImage;

    @FXML
    private ImageView wrongQuestionImage;

    @FXML
    private ImageView rightQuestionImage;
    
    //TODO MOVE TO QUESTION MANAGER
    private MultipleChoiceFlashCardDTO currentQuestion;

    //TODO MOVE TO QUESTION MANAGER
    private STATUS status= STATUS.INCOMPLETE;
	
    //TODO MOVE TO QUESTION MANAGER
    AnswerChecker validator1 = (Boolean a,CheckBox one,CheckBox two,CheckBox three )->{  
    	
    		int userTrueList = 0;
    		int questioTrueList = 0;
    		
    		if(currentQuestion.getAnswer().isAtrue()){
    			questioTrueList++;
    		}
    		if(currentQuestion.getAnswer().isBtrue()){
    			questioTrueList++;
    		}
    		if(currentQuestion.getAnswer().isCtrue()){
    			questioTrueList++;
    		}
    		if(currentQuestion.getAnswer().isDtrue()){
    			questioTrueList++;
    		}
    		
    		Boolean isATrue = currentQuestion.getAnswer().isAtrue() && a;
		    if(isATrue){userTrueList++;} 
		    Boolean isBTrue = currentQuestion.getAnswer().isBtrue() && one.isSelected();
		    if(isBTrue){userTrueList++;} 
		    Boolean isCTrue = currentQuestion.getAnswer().isCtrue() && two.isSelected();
		    if(isCTrue){userTrueList++;} 
		    Boolean isDTrue = currentQuestion.getAnswer().isDtrue() && three.isSelected();
		    if(isDTrue){userTrueList++;}
		    
		    Boolean[] results = new Boolean[2];
		    results[0]=isATrue||isBTrue||isCTrue||isDTrue;
		    results[1]=questioTrueList==userTrueList;
		    
		    return results;
		   };
		    //TODO MOVE TO QUESTION MANAGER
		   AnswerChecker validator2 = (Boolean a,CheckBox one,CheckBox two,CheckBox three )->{  
		    	
	    		int userTrueList = 0;
	    		int questioTrueList = 0;
	    		
	    		if(currentQuestion.getAnswer().isAtrue()){
	    			questioTrueList++;
	    		}
	    		if(currentQuestion.getAnswer().isBtrue()){
	    			questioTrueList++;
	    		}
	    		if(currentQuestion.getAnswer().isCtrue()){
	    			questioTrueList++;
	    		}
	    		if(currentQuestion.getAnswer().isDtrue()){
	    			questioTrueList++;
	    		}
	    		
	    		Boolean isBTrue = currentQuestion.getAnswer().isBtrue() && a;
			    if(isBTrue){userTrueList++;} 
			    Boolean isATrue = currentQuestion.getAnswer().isAtrue() && one.isSelected();
			    if(isATrue){userTrueList++;} 
			    Boolean isCTrue = currentQuestion.getAnswer().isCtrue() && two.isSelected();
			    if(isCTrue){userTrueList++;} 
			    Boolean isDTrue = currentQuestion.getAnswer().isDtrue() && three.isSelected();
			    if(isDTrue){userTrueList++;}
			    
			    Boolean[] results = new Boolean[2];
			    results[0]=isATrue||isBTrue||isCTrue||isDTrue;
			    results[1]=questioTrueList==userTrueList;
			    
			    return results;
			   };
			    //TODO MOVE TO QUESTION MANAGER
			   AnswerChecker validator3 = (Boolean a,CheckBox one,CheckBox two,CheckBox three )->{  
			    	
		    		int userTrueList = 0;
		    		int questioTrueList = 0;
		    		
		    		if(currentQuestion.getAnswer().isAtrue()){
		    			questioTrueList++;
		    		}
		    		if(currentQuestion.getAnswer().isBtrue()){
		    			questioTrueList++;
		    		}
		    		if(currentQuestion.getAnswer().isCtrue()){
		    			questioTrueList++;
		    		}
		    		if(currentQuestion.getAnswer().isDtrue()){
		    			questioTrueList++;
		    		}
		    		
		    		Boolean isCTrue = currentQuestion.getAnswer().isCtrue() && a;
				    if(isCTrue){userTrueList++;} 
				    Boolean isATrue = currentQuestion.getAnswer().isAtrue() && one.isSelected();
				    if(isATrue){userTrueList++;} 
				    Boolean isBTrue = currentQuestion.getAnswer().isBtrue() && two.isSelected();
				    if(isBTrue){userTrueList++;} 
				    Boolean isDTrue = currentQuestion.getAnswer().isDtrue() && three.isSelected();
				    if(isDTrue){userTrueList++;}
				    
				    Boolean[] results = new Boolean[2];
				    results[0]=isATrue||isBTrue||isCTrue||isDTrue;
				    results[1]=questioTrueList==userTrueList;
				    
				    return results;
				   };
				   
				   AnswerChecker validator4 = (Boolean a,CheckBox one,CheckBox two,CheckBox three )->{  
				    	
			    		int userTrueList = 0;
			    		int questioTrueList = 0;
			    		
			    		if(currentQuestion.getAnswer().isAtrue()){
			    			questioTrueList++;
			    		}
			    		if(currentQuestion.getAnswer().isBtrue()){
			    			questioTrueList++;
			    		}
			    		if(currentQuestion.getAnswer().isCtrue()){
			    			questioTrueList++;
			    		}
			    		if(currentQuestion.getAnswer().isDtrue()){
			    			questioTrueList++;
			    		}
			    		
			    		Boolean isDTrue = currentQuestion.getAnswer().isAtrue() && a;
					    if(isDTrue){userTrueList++;} 
					    Boolean isATrue = currentQuestion.getAnswer().isBtrue() && one.isSelected();
					    if(isATrue){userTrueList++;} 
					    Boolean isBTrue = currentQuestion.getAnswer().isCtrue() && two.isSelected();
					    if(isBTrue){userTrueList++;} 
					    Boolean isCTrue = currentQuestion.getAnswer().isDtrue() && three.isSelected();
					    if(isCTrue){userTrueList++;}
					    
					    Boolean[] results = new Boolean[2];
					    results[0]=isATrue||isBTrue||isCTrue||isDTrue;
					    results[1]=questioTrueList==userTrueList;
					    
					    return results;
					   };

				private Map<Integer, UserAnswer> userResponses;
		   
	@FXML
	public void initialize(){
		questionTextArea.setEditable(false);
		answerTextArea.setEditable(false);
		answerTextArea.setVisible(false);
		
		aCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			    //TODO MOVE TO QUESTION MANAGER
				if(StudyModeController.this.status!=STATUS.RESET){
				Boolean[] check = isCorrect(validator1,newValue,bCheckBox,cCheckBox,dCheckBox);
				STATUS status = answerVerifier(check);
				showAnswer(status);
				updateUserAnswer(status);
				UserAnswer userAnswer=updateUserAnswer(status);
				saveUserAnwer(currentQuestion,userAnswer);
				evaluate(userAnswer,currentQuestion);
				}
			}

		});
		bCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			    //TODO MOVE TO QUESTION MANAGER
				if(StudyModeController.this.status!=STATUS.RESET){
				Boolean[] check = isCorrect(validator2,newValue,aCheckBox,cCheckBox,dCheckBox);
				STATUS status = answerVerifier(check);
				showAnswer(status);
				updateUserAnswer(status);
				UserAnswer userAnswer=updateUserAnswer(status);
				saveUserAnwer(currentQuestion,userAnswer);
				evaluate(userAnswer,currentQuestion);
				}
			}
		});
		cCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			    //TODO MOVE TO QUESTION MANAGER
				if(StudyModeController.this.status!=STATUS.RESET){
				Boolean[] check = isCorrect(validator3,newValue,aCheckBox,bCheckBox,dCheckBox);
				STATUS status = answerVerifier(check);
				showAnswer(status);
				UserAnswer userAnswer=updateUserAnswer(status);
				saveUserAnwer(currentQuestion,userAnswer);
				evaluate(userAnswer,currentQuestion);
				}
			}
		});
		dCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
			    //TODO MOVE TO QUESTION MANAGER
				if(StudyModeController.this.status!=STATUS.RESET){
				Boolean[] check = isCorrect(validator4,newValue,aCheckBox,bCheckBox,cCheckBox);
				STATUS status = answerVerifier(check);
				showAnswer(status);
				updateUserAnswer(status);
				UserAnswer userAnswer=updateUserAnswer(status);
				saveUserAnwer(currentQuestion,userAnswer);
				evaluate(userAnswer,currentQuestion);
				}
			}
		});
	}

	public void setCurrentQuestion(MultipleChoiceFlashCardDTO currentQuestion) {
	    //TODO MOVE TO QUESTION MANAGER
		questionTextArea.setText(currentQuestion.getQuestion().getQuestion());
		//TODO Check if id is null
		
		aLabel.setText(findChoiceText(1, currentQuestion));
		bLabel.setText(findChoiceText(2, currentQuestion));
		cLabel.setText(findChoiceText(3, currentQuestion));
		dLabel.setText(findChoiceText(4, currentQuestion));
		answerTextArea.setText(currentQuestion.getAnswer().getAnswer());
		
		aCheckBox.setVisible(checkEmptyQuestions(1, currentQuestion));
		bCheckBox.setVisible(checkEmptyQuestions(2, currentQuestion));
		cCheckBox.setVisible(checkEmptyQuestions(3, currentQuestion));
		dCheckBox.setVisible(checkEmptyQuestions(4, currentQuestion));
		
		this.currentQuestion=currentQuestion;
	}
	
    //TODO MOVE TO QUESTION MANAGER
	private static String findChoiceText(int number,MultipleChoiceFlashCardDTO currentQuestion){
		Optional<Choice> opt = currentQuestion.getChoiceList().stream().filter((c)->c.getId()==number).findFirst();
		if(opt.isPresent()){
			return opt.get().getDescription();
		}
		return "";
	}

    //TODO MOVE TO QUESTION MANAGER
	private static Boolean checkEmptyQuestions(int number,MultipleChoiceFlashCardDTO currentQuestion){
		Optional<Choice> opt = currentQuestion.getChoiceList().stream().filter((c)->c.getId()==number).findFirst();
		if(opt.isPresent()){
			return true;
		}
		return false;
	}
	private static Boolean[] isCorrect(AnswerChecker answerChecker,Boolean newValue,CheckBox one,CheckBox two,CheckBox three){
		return answerChecker.correctAnswer(newValue,one,two,three);
	}
    //TODO MOVE TO QUESTION MANAGER
	private STATUS answerVerifier(Boolean[] check){
		STATUS status = STATUS.INCOMPLETE;
		if(check[1]==true&&check[1]==true){
			status = STATUS.RIGHT;
		}
		if(check[1]==false&&check[0]==false){
			status = STATUS.WRONG;
		}
		if(check[0]==false&&check[1]==true){
			status = STATUS.INCOMPLETE;
		}
		return status;
	}
    //TODO MOVE TO QUESTION MANAGER
	private void showAnswer(STATUS status){
		if(status.equals(STATUS.RIGHT)){
			//Show Answer
			answerTextArea.setVisible(true);
			aCheckBox.setDisable(true);
			bCheckBox.setDisable(true);
			cCheckBox.setDisable(true);
			dCheckBox.setDisable(true);
		}
		if(status.equals(STATUS.WRONG)){
			//Show Answer
			answerTextArea.setVisible(true);
			aCheckBox.setDisable(true);
			bCheckBox.setDisable(true);
			cCheckBox.setDisable(true);
			dCheckBox.setDisable(true);
		}
	}
	
    //TODO MOVE TO QUESTION MANAGER
	private void saveUserAnwer(MultipleChoiceFlashCardDTO currentQuestion,UserAnswer userAnswer){
		//Reference From model take caution!	
		this.userResponses.put(currentQuestion.getId(), userAnswer);
	}
	
    //TODO MOVE TO QUESTION MANAGER
	public interface AnswerChecker{
		public Boolean[] correctAnswer(Boolean newValue,CheckBox one,CheckBox two,CheckBox three);
	}
    //TODO MOVE TO QUESTION MANAGER
    public void resetQuestion(){
		this.status=STATUS.RESET;
		answerTextArea.setVisible(false);
		aCheckBox.setDisable(false);
		bCheckBox.setDisable(false);
		cCheckBox.setDisable(false);
		dCheckBox.setDisable(false);
		aCheckBox.setSelected(false);
		bCheckBox.setSelected(false);
		cCheckBox.setSelected(false);
		dCheckBox.setSelected(false);
		wrongCheckMarkAImage.setVisible(false);
		wrongCheckMarkBImage.setVisible(false);
		wrongCheckMarkCImage.setVisible(false);
		wrongCheckMarkDImage.setVisible(false);
		rightCheckMarkAImage.setVisible(false);
		rightCheckMarkBImage.setVisible(false);
		rightCheckMarkCImage.setVisible(false);
		rightCheckMarkDImage.setVisible(false);
		rightQuestionImage.setVisible(false);
		wrongQuestionImage.setVisible(false);
		this.status=STATUS.INCOMPLETE;
	}
    //TODO MOVE TO QUESTION MANAGER
	public void setUserAnsweredQuestion(UserAnswer userAnswer) {
		this.status=STATUS.RESET;
		aCheckBox.setSelected(userAnswer.isaCheck());
		bCheckBox.setSelected(userAnswer.isbCheck());
		cCheckBox.setSelected(userAnswer.iscCheck());
		dCheckBox.setSelected(userAnswer.isdCheck());
		showAnswer(userAnswer.getStatus());
		evaluate(userAnswer,currentQuestion);
		this.status=STATUS.INCOMPLETE;
	}

	//TODO MOVE TO QUESTION MANAGER
	//TODO Create place marks method to place wrong or right check marks in question
	private void evaluate(UserAnswer userAnswer, MultipleChoiceFlashCardDTO multipleChoiceFlashCardDTO) {
		
		STATUS status = STATUS.INCOMPLETE;
		int rightChoices=0; 
		int currentRight=0;
		
		if (multipleChoiceFlashCardDTO.getAnswer().isAtrue()) {
			rightChoices++;
		}
		if (multipleChoiceFlashCardDTO.getAnswer().isBtrue()) {
			rightChoices++;
		}
		if (multipleChoiceFlashCardDTO.getAnswer().isCtrue()) {
			rightChoices++;
		}
		if (multipleChoiceFlashCardDTO.getAnswer().isDtrue()) {
			rightChoices++;
		}

		if(userAnswer.isaCheck() && multipleChoiceFlashCardDTO.getAnswer().isAtrue()){ 
			aCheckBox.setDisable(true);
			rightCheckMarkAImage.setVisible(true);
			currentRight++;
		}else if(userAnswer.isaCheck()){
			aCheckBox.setDisable(true);
			wrongCheckMarkAImage.setVisible(true);
			status=STATUS.WRONG;
		}
		if(userAnswer.isbCheck() && multipleChoiceFlashCardDTO.getAnswer().isBtrue()){ 
			bCheckBox.setDisable(true);
			rightCheckMarkBImage.setVisible(true); 
			currentRight++;
		}else if(userAnswer.isbCheck()){
			bCheckBox.setDisable(true);
			wrongCheckMarkBImage.setVisible(true);
			status=STATUS.WRONG;
		}
		if(userAnswer.iscCheck() && multipleChoiceFlashCardDTO.getAnswer().isCtrue()){ 
			cCheckBox.setDisable(true);
			rightCheckMarkCImage.setVisible(true);
			currentRight++;
		}else if(userAnswer.iscCheck()){
			cCheckBox.setDisable(true);
			wrongCheckMarkCImage.setVisible(true);
			status=STATUS.WRONG;
		}
		if(userAnswer.isdCheck()&&multipleChoiceFlashCardDTO.getAnswer().isDtrue()){ 
			dCheckBox.setDisable(true);
			rightCheckMarkDImage.setVisible(true); 
			currentRight++;
		}else if(userAnswer.isdCheck()){
			dCheckBox.setDisable(true);
			wrongCheckMarkDImage.setVisible(true);
			status=STATUS.WRONG;
		}
		
		if(currentRight==rightChoices){
			rightQuestionImage.setVisible(true);
		}
		
		if(status==STATUS.WRONG){
			wrongQuestionImage.setVisible(true);
			answerTextArea.setVisible(true);
			this.status=status;
		}
	}

	//TODO MOVE TO QUESTION MANAGER
	//Gate to model take caution;
	public void setUserResponses(Map<Integer, UserAnswer> userResponses) {
		this.userResponses=userResponses;
	}
	
    //TODO MOVE TO QUESTION MANAGER
	private UserAnswer updateUserAnswer(STATUS status) {
		UserAnswer userAnswer = new UserAnswer();
		userAnswer.setStatus(status);
		userAnswer.setaCheck(aCheckBox.isSelected());
		userAnswer.setbCheck(bCheckBox.isSelected());
		userAnswer.setcCheck(cCheckBox.isSelected());
		userAnswer.setdCheck(dCheckBox.isSelected());
		return userAnswer;
	}
}