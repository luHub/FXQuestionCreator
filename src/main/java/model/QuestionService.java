package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import commons.flashcards.FlashCardIO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import meta.flashcardsdto.MultipleChoiceFlashCardDTO;

public class QuestionService<V> extends Service<V> {

	private enum SAVE{ YES , NO};
	private ConcurrentHashMap<Integer, MultipleChoiceFlashCardDTO> questionMap = new ConcurrentHashMap(); 
	private List<QuestionInList> questionForList = new ArrayList();
	private MultipleChoiceFlashCardDTO questionToSave;
	private SAVE save=SAVE.NO;
	private QuestionManager questionManager;
	protected Queue<QuestionFileOps> queue = new ConcurrentLinkedQueue();
	
	@Override
	protected Task<V> createTask() {
		return new Task<V>() {

			@Override
			protected V call() throws Exception {
				
				while ( true ) {
		            try {
		            	QuestionFileOps questionFileOps = null;
		                synchronized ( queue ) {
		                    while ( queue.isEmpty() ){
		                    	   queue.wait();
				            }
		                    // Get the next work item off of the queue
		                    questionFileOps = queue.remove();
		                
		                // Process the work item
		                //Prepare with common data to work
		                
		                questionFileOps.run();
		                }
		            }
		            catch ( InterruptedException ie ) {
		                break;  // Terminate
		            }
		        }
				
				/*if(save.equals(SAVE.YES)){
					save=SAVE.NO;
					saveQuestion();
				}
				updateAllQuestionsFromFiles();*/
				return null;
			}
		
			@Override protected void succeeded() {
		        super.succeeded();
		        updateMessage("Done!");
		    }

		    @Override protected void cancelled() {
		        super.cancelled();
		        updateMessage("Cancelled!");
		    }

		@Override protected void failed() {
		    super.failed();
		    updateMessage("Failed!");
		    }
		};
	}
	
	//This method should be enhanced to be more efficient just adding only new/modified and removed files 
	/*private void updateAllQuestionsFromFiles() {
		try {
			//Get all question from direcotry
			Map<Integer,MultipleChoiceFlashCardDTO> questionMap = new HashMap();
			questionMap = FlashCardIO.readAllFlashCards();
			this.questionMap.putAll(questionMap);
			this.getQuestionForList().clear();
			questionMap.forEach((k,v)->{
				QuestionInList q = new QuestionInList();
				q.setId(String.valueOf(v.getId()));
				q.setType(String.valueOf(v.getQuestion().getType()));
				this.questionForList.add(q);
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
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

	List<QuestionInList> getQuestionForList() {
		return questionForList;
	}

	public ConcurrentHashMap<Integer, MultipleChoiceFlashCardDTO> getQuestionMap() {
		return questionMap;
	}

	public void setQuestionManager(QuestionManager questionManager) {
		this.questionManager = questionManager;
	}
	
	public void getQuestionsFromFiles(){
		synchronized ( queue ) {
		    // Add work to the queue
			QuestionFileOps questionFileOps = new QuestionFileOps();
			questionFileOps.setQuestionFromList(this.questionForList);
			questionFileOps.setQuestionMap(this.questionMap);
			questionFileOps.setQuestionManager(this.questionManager);
			queue.add(questionFileOps); 
		    // Notify the monitor object that all the threads
		    // are waiting on. This will awaken just one to
		    // begin processing work from the queue
		    queue.notify();
		}
	}
	 
	public void addQuestionToSave(MultipleChoiceFlashCardDTO mcfd){
		synchronized ( queue ) {
		    // Add work to the queue
			QuestionFileOps questionFileOps = new QuestionFileOps();
			questionFileOps.setQuestionFromList(this.questionForList);
			questionFileOps.setQuestionMap(this.questionMap);
			questionFileOps.setQuestionManager(this.questionManager);
			questionFileOps.updateQuestionToFile(mcfd);
			queue.add(questionFileOps); 
		    // Notify the monitor object that all the threads
		    // are waiting on. This will awaken just one to
		    // begin processing work from the queue
		    queue.notify();
		}
	}
	
	

	public boolean isQueueEmpty() {
		// TODO Auto-generated method stub
		return queue.isEmpty();
	}

	public void deleteFile(int id) {
		synchronized ( queue ) {
			QuestionFileOps questionFileOps = new QuestionFileOps();
			questionFileOps.setQuestionFromList(this.questionForList);
			questionFileOps.setQuestionMap(this.questionMap);
			questionFileOps.setQuestionManager(this.questionManager);
			questionFileOps.setQuestionIdToDelete(id);
			queue.add(questionFileOps);
			queue.notify();
		}
	}
		
} 
