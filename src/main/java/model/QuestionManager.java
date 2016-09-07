package model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import commons.flashcards.FlashCardIO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import meta.flashcardsdto.Answer;
import meta.flashcardsdto.Choice;
import meta.flashcardsdto.MultipleChoiceFlashCardDTO;
import meta.flashcardsdto.Question;

public class QuestionManager {
 
	private ListView<QuestionInList> questionListView = new ListView();
	private StudyModeManager studyModeManager;
	private CreatorModeManager creatorModeManager;
	private MultipleChoiceFlashCardDTO currentQuestion;
	private enum MODE{ CREATE,STUDY }
	private enum QUESTION_STATUS{ NEW,OLD,DELETED }
	
	private MODE mode = MODE.STUDY;
	private QUESTION_STATUS questionStatus = QUESTION_STATUS.OLD;
	
	//Create a concurrent Map and be careful
	private QuestionService questionService = new QuestionService();

	private ChangeListener<QuestionInList> managerListener = new ChangeListener<QuestionInList>() {
		public void changed(ObservableValue<? extends QuestionInList> observable, QuestionInList oldValue,
				QuestionInList newValue) {
			// Set StudyModeManagerView
			if(newValue!=null){
			Integer questionId = FlashCardIO.getQuestionId(newValue.getId());
			MultipleChoiceFlashCardDTO currentQuestion = (MultipleChoiceFlashCardDTO) questionService.getQuestionMap()
					.get(questionId);
			QuestionManager.this.currentQuestion = currentQuestion;
			if (mode.equals(MODE.STUDY)) {
				QuestionManager.this.studyModeManager.setCurrentQuestion(currentQuestion);
			} else if (mode.equals(MODE.CREATE)) {
				QuestionManager.this.creatorModeManager.setCurrentQuesion();
			}
		}}
	};

	public QuestionManager(){
		try {
			FlashCardIO.createPathIfNotExist();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkFileSystems();
		initializeQuestionService();
	}
	
	public void loadListFromFile(){
		//Load all questions from directory
		//populateListView
	}

	
	
	public void deleteQuestion() {
		this.questionStatus=QUESTION_STATUS.DELETED;
		// TODO ADD to SERVICE THREAD
		int id = this.currentQuestion.getId();
		questionService.deleteFile(id);
	}
	
	public void saveQuestion(){
		//Save Question to File
		
		//when a question is saved question service will update list with new question
		
		//Excecute question service update
	}

	//TODO MOVE TO QUESTION MANAGER
	//TODO Remove this method, obtain Listview from StudyModeManager -> MainController  
	public void setQuestionListView(ListView<QuestionInList> questionListView) {
		this.questionListView=questionListView;
	}

	 
	//TODO MOVE TO QUESTION MANAGER 
	public void setStudyModeManager(StudyModeManager studyModeManager) {
		this.studyModeManager = studyModeManager;
		//Remove All Listeners  
		this.questionListView.getSelectionModel().selectedItemProperty().removeListener(managerListener);
		this.questionListView.getSelectionModel().selectedItemProperty().addListener(managerListener);
	}
	
	//Create Update Service with FileWorker Inside
	//Here is the part of careful
	private void initializeQuestionService(){
			/*questionService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			public void handle(WorkerStateEvent event) {
				updateUI();
			}
		});*/
		this.questionService.setQuestionManager(this);
		this.questionService.start();
		this.questionService.getQuestionsFromFiles();
	}

	//TODO MOVE TO QUESTION MANAGER
	public void setSelectedQuestionToStudyMode() {
		this.mode=MODE.STUDY;
		QuestionInList item = this.questionListView.getSelectionModel().getSelectedItem();
		Integer questionId = FlashCardIO.getQuestionId(item.getId());
		MultipleChoiceFlashCardDTO currentQuestion = (MultipleChoiceFlashCardDTO) questionService.getQuestionMap()
				.get(questionId);
		//ONCE IN QUESTION MANAGER CALL GET QUESTION METHOD
		QuestionManager.this.studyModeManager.setCurrentQuestion(currentQuestion);
	}
	//TODO MOVE TO CREATOR MANAGER
	public void setQuestionCreatorManager(CreatorModeManager creatorModeManager) {
		this.mode=MODE.CREATE;
		this.creatorModeManager = creatorModeManager; 
		this.questionListView.getSelectionModel().selectedItemProperty().removeListener(managerListener);
		this.questionListView.getSelectionModel().selectedItemProperty().addListener(managerListener);
	}

	public MultipleChoiceFlashCardDTO getQuestion(QuestionInList questionInList  ){
		Integer questionId = FlashCardIO.getQuestionId(questionInList.getId());
		MultipleChoiceFlashCardDTO question = (MultipleChoiceFlashCardDTO) questionService.getQuestionMap()
				.get(questionId);
		return question;
	}

	public Optional<MultipleChoiceFlashCardDTO> getSeletectedQuestion() {
		Optional<MultipleChoiceFlashCardDTO> questionInListopt = Optional.of(currentQuestion);
		return questionInListopt;
	}

	public void overwriteQuestion(Question question) {
		this.currentQuestion.setQuestion(question);
		this.questionService.addQuestionToSave(this.currentQuestion);
	}

	
	 
	public void updateUI() {
		Platform.runLater(() -> {
			// questionListView.getSelectionModel().selectedItemProperty().removeListener(managerListener);
			questionListView.getSelectionModel().selectedItemProperty().removeListener(managerListener);
			List<QuestionInList> questions = questionService.getQuestionForList();
			ObservableList<QuestionInList> q = FXCollections.observableList(questions);
			questionListView.setItems(q); 
			questionListView.getSelectionModel().selectedItemProperty().addListener(managerListener);
			// TODO not use this null comparison
			if (currentQuestion == null) {
				questionListView.getSelectionModel().selectFirst();
			} else if (questionStatus.equals(QUESTION_STATUS.OLD)) {
				QuestionInList qil = new QuestionInList();
				qil.setId(String.valueOf(currentQuestion.getId()));
				qil.setType(currentQuestion.getQuestion().getType());
				questionListView.getSelectionModel().select(qil);
			} else if (questionStatus.equals(QUESTION_STATUS.NEW)) {
				questionStatus = QUESTION_STATUS.OLD;
				questionListView.getSelectionModel().selectLast();
			}else if(questionStatus.equals(QUESTION_STATUS.DELETED)){
				questionListView.getSelectionModel().selectLast();
				questionStatus=QUESTION_STATUS.OLD;
			}
		});
	}

	public void overwriteAnswer(Answer answer) {
		this.currentQuestion.setAnswer(answer);
		this.questionService.addQuestionToSave(this.currentQuestion);
	}

	

	public void overwriteChoice(Choice choice) {
		this.currentQuestion.getChoiceList().removeIf(c->c.getId()==choice.getId());
		if(!choice.getDescription().isEmpty()){
			this.currentQuestion.getChoiceList().add(choice);
		}
		this.questionService.addQuestionToSave(this.currentQuestion);
	}
	
	public void createNewQuestion(){
		questionStatus=QUESTION_STATUS.NEW;
		MultipleChoiceFlashCardDTO mcfd = new MultipleChoiceFlashCardDTO();
		Question question = new Question();
		Answer answer = new Answer();
		mcfd.setQuestion(question);
		question.setType("New Question");
		mcfd.setAnswer(answer);
		int id=questionService.getQuestionForList().size();
		mcfd.setId(id); 
		questionService.addQuestionToSave(mcfd);
	}

	private void checkFileSystems() {
		try {
			FlashCardIO.createPathIfNotExist();
			Map<Integer, MultipleChoiceFlashCardDTO> fcm = FlashCardIO.readAllFlashCards();
			if (fcm.isEmpty()){
				MultipleChoiceFlashCardDTO multipleChoiceFlashCardDTO = new MultipleChoiceFlashCardDTO();
				Question question = new Question();
				question.setId(0);
				question.setType("Write your question");
				multipleChoiceFlashCardDTO.setQuestion(question);
				FlashCardIO.createFlashCardAsJsonFile(multipleChoiceFlashCardDTO);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} 
}
