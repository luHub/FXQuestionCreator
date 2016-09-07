package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import commons.flashcards.FlashCardIO;
import meta.flashcardsdto.MultipleChoiceFlashCardDTO;

public class QuestionFileOps implements Runnable {
	
	private enum SAVE{ YES , NO,DELETE};
	private ConcurrentHashMap<Integer, MultipleChoiceFlashCardDTO> questionMap; 
	private List<QuestionInList> questionForList;
	private MultipleChoiceFlashCardDTO questionToSave;
	private SAVE save=SAVE.NO;
	private QuestionManager questionManager;
	private int deleteId;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(save.equals(SAVE.YES)){
			save=SAVE.NO;
			saveQuestion();
		}
		if(save.equals(SAVE.DELETE)){
			save=SAVE.NO;
			deleteQuestion(deleteId);
		}
		updateAllQuestionsFromFiles();
	}
	
	private void deleteQuestion(int deleteId) {
		try {
			FlashCardIO.deleteQuestion(deleteId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//This method should be enhanced to be more efficient just adding only new/modified and removed files 
	private void updateAllQuestionsFromFiles() {
		try {
			//Get all question from direcotry
			Map<Integer,MultipleChoiceFlashCardDTO> questionMap = new HashMap();
			questionMap = FlashCardIO.readAllFlashCards();
			this.questionMap.putAll(questionMap);
			this.questionForList.clear();
			questionMap.forEach((k,v)->{
				QuestionInList q = new QuestionInList();
				q.setId(String.valueOf(v.getId()));
				q.setType(String.valueOf(v.getQuestion().getType()));
				this.questionForList.add(q);
			});
			  questionManager.updateUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveQuestion(){
		try {
			FlashCardIO.createFlashCardAsJsonFile(this.questionToSave);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void updateQuestionToFile(MultipleChoiceFlashCardDTO mcfc){
		save=save.YES;
		this.questionToSave=mcfc;
	}

	
	public void setQuestionFromList(List<QuestionInList> questionForList) {
		this.questionForList = questionForList;
	}

	public void setQuestionMap(ConcurrentHashMap<Integer, MultipleChoiceFlashCardDTO> questionMap) {
		this.questionMap=questionMap;
	}

	public void setQuestionManager(QuestionManager questionManager) {
		// TODO Auto-generated method stub
		this.questionManager=questionManager;
	}

	public void createQuestionToFile(MultipleChoiceFlashCardDTO mcfc) {
		save=save.YES;
		this.questionToSave=mcfc;
	}

	public void setQuestionIdToDelete(int id) {
		save=SAVE.DELETE;
		this.deleteId=id;
	}

}