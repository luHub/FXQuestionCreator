package flashcardfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import meta.flashcardsdto.MultipleChoiceFlashCardDTO;
import questions.CreatorModeManager;
import questions.STATUS;

public class QuestionCreatorController {

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
	private TextField choiceATextField;

	@FXML
	private TextField choiceBTextField;

	@FXML
	private TextField choiceCTextField;

	@FXML
	private TextField choiceDTextField;

	@FXML
	private TextField questionTypeTextField;
	
	//Bridge to model
	private CreatorModeManager creatorModeManager;
	
	private enum STARTUP{ INIT,READY,NEW };
	
	private STARTUP startup = STARTUP.INIT;

	//Set Before initialize the controller
	public void setCreatorModeManager(CreatorModeManager creatorModeManager) {
		this.creatorModeManager=creatorModeManager;
	}
	
	@FXML
	public void initialize(){
		
		questionTextArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.equals(false)&&oldValue.equals(true)){
					//TODO only save if text is different
					if(QuestionCreatorController.this.startup==STARTUP.READY)	
					creatorModeManager.writeQuestion(questionTextArea.getText(), questionTypeTextField.getText());
				}
			}
		});
		
		questionTypeTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.equals(false)&&oldValue.equals(true)){
					if(QuestionCreatorController.this.startup==STARTUP.READY)	
					creatorModeManager.writeQuestion(questionTextArea.getText(), questionTypeTextField.getText());
				}
			}
		});
		
		answerTextArea.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue.equals(false) && oldValue.equals(true)) {
					if(QuestionCreatorController.this.startup==STARTUP.READY)	
					creatorModeManager.writeAnswer(answerTextArea.getText(), aCheckBox.isSelected(),
							bCheckBox.isSelected(), cCheckBox.isSelected(), dCheckBox.isSelected());
				}
			}
		});
		
		aCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(QuestionCreatorController.this.startup==STARTUP.READY)	
				creatorModeManager.writeAnswer(answerTextArea.getText(), newValue,
							bCheckBox.isSelected(), cCheckBox.isSelected(), dCheckBox.isSelected());
			}
		});
		
		bCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(QuestionCreatorController.this.startup==STARTUP.READY)	
				creatorModeManager.writeAnswer(answerTextArea.getText(), aCheckBox.isSelected(),
							newValue, cCheckBox.isSelected(), dCheckBox.isSelected());
			}
		});
		
		cCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(QuestionCreatorController.this.startup==STARTUP.READY)	
				creatorModeManager.writeAnswer(answerTextArea.getText(), aCheckBox.isSelected(),
							bCheckBox.isSelected(), newValue, dCheckBox.isSelected());
			}
		});
		
		dCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(QuestionCreatorController.this.startup==STARTUP.READY)	
				creatorModeManager.writeAnswer(answerTextArea.getText(), aCheckBox.isSelected(),
							bCheckBox.isSelected(), cCheckBox.isSelected(), newValue);
			}
		});
		
		choiceATextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.equals(false)&&oldValue.equals(true)){
					//TODO only save if text is different
					if(QuestionCreatorController.this.startup==STARTUP.READY)	
					creatorModeManager.writeQuestionChoiceA(choiceATextField.getText());
				}
			}
		});
		
		choiceBTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.equals(false)&&oldValue.equals(true)){
					//TODO only save if text is different
					if(QuestionCreatorController.this.startup==STARTUP.READY)	
					creatorModeManager.writeQuestionChoiceB(choiceBTextField.getText());
				}
			}
		});
		
		choiceCTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.equals(false)&&oldValue.equals(true)){
					//TODO only save if text is different
					if(QuestionCreatorController.this.startup==STARTUP.READY)	
					creatorModeManager.writeQuestionChoiceC(choiceCTextField.getText());
				}
			}
		});
		
		choiceDTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue.equals(false)&&oldValue.equals(true)){
					//TODO only save if text is different
					if(QuestionCreatorController.this.startup==STARTUP.READY)	
					creatorModeManager.writeQuestionChoiceD(choiceDTextField.getText());
				}
			}
		});
		
	}
	
	// Initialization from Model
	public void init() {
		this.startup=STARTUP.INIT;
		setCurrentQuestion();
		
		this.startup=STARTUP.READY;
	}

	//Model places new question
	public void setCurrentQuesion() {
		setCurrentQuestion();
	}
	
	
	private void setCurrentQuestion() {
		this.startup=STARTUP.INIT;
		prepareNewQuestion();
		questionTypeTextField.setText(this.creatorModeManager.readQuestion().getType());
		questionTextArea.setText(this.creatorModeManager.readQuestion().getQuestion());
		answerTextArea.setText(this.creatorModeManager.readAnswer().getAnswer());
		this.creatorModeManager.readQuestionChoices().forEach((c) -> {
			if (c.getId() == 1) {
				choiceATextField.setText(c.getDescription());
			}
			if (c.getId() == 2) {
				choiceBTextField.setText(c.getDescription());
			}
			if (c.getId() == 3) {
				choiceCTextField.setText(c.getDescription());
			}
			if (c.getId() == 4) {
				choiceDTextField.setText(c.getDescription());
			}
		});
		aCheckBox.setSelected(this.creatorModeManager.readAnswer().isAtrue());
		bCheckBox.setSelected(this.creatorModeManager.readAnswer().isBtrue());
		cCheckBox.setSelected(this.creatorModeManager.readAnswer().isCtrue());
		dCheckBox.setSelected(this.creatorModeManager.readAnswer().isDtrue());
		this.startup=STARTUP.READY;
	}

	public void createNewQuestion() {
		this.startup=STARTUP.NEW;
		prepareNewQuestion();
		this.startup=STARTUP.READY;
	}

	private void prepareNewQuestion() {
		//Create a new blank question
		questionTextArea.clear();
		answerTextArea.clear();
		aCheckBox.setSelected(false);
		bCheckBox.setSelected(false);
		cCheckBox.setSelected(false);
		dCheckBox.setSelected(false);
		choiceATextField.clear();
		choiceBTextField.clear();
		choiceCTextField.clear();
		choiceDTextField.clear();
	}
	
}