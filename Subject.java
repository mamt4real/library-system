/**
 * @(#)Subject.java
 *
 *
 * @author 
 * @version 1.00 2019/12/27
 */
package myProjects.cbtexam;
import java.util.*;

public class Subject {
	private String name;
	private int duration;
	private int noOfQuestions;
	private ArrayList<Question> questions;
	StringTokenizer tk;

    public Subject(String name, int dr, int n){
    	this.name = name;
    	duration = dr;
    	noOfQuestions = n;
    	questions = new ArrayList<Question>(n); 
    }
    public String getName(){
    	return name;
    }
    public int getDuration(){
    	return duration;
    }
    public int getNo(){
    	return noOfQuestions;
    }
    public void addQuestion(String ques){
    	tk = new StringTokenizer(ques,"#");
    	String q,o1,o2,o3,o4;
    	int n;
    	q = tk.nextToken();
    	o1 = tk.nextToken();
    	o2 = tk.nextToken();
    	o3 = tk.nextToken();
    	o4 = tk.nextToken();
    	n = Integer.parseInt(tk.nextToken());
    	questions.add(new Question(q,o1,o2,o3,o4,n));
    }
    public int totalScore(){
    	int x = 0;
    		for(int i = 0;i<noOfQuestions;i++)
    			x += ((Question)questions.get(i)).getScore();
    	return x;
    }
    
    
}