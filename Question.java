/**
 * @(#)Question.java
 *
 *
 * @author 
 * @version 1.00 2019/12/27
 */
 package myProjects.cbtexam;

public class Question {
	
	private String question,choice,answer;
	private String[] options;
	private int ans;

    public Question(String ques,String op1,String op2,String op3,String op4,int a) {
    	question = ques;
    	options[0] = op1;
    	options[1] = op2;
    	options[2] = op3;
    	options[3] = op4;
    	answer = options[a-1];
    }
    public String[] getOptions(){
    	return options;
    }
    public boolean isAnswered(){
    	return choice == null;
    }
    public void choose(String s){
    	choice = s;
    }
    public String getQuestion(){
    	return question;
    }
    public int getScore(){
    	return (choice.equals(answer))? 1: 0;
    }
}