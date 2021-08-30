 package myProjects.cbtexam;

public class CountDown implements Runnable{
	private int timeInSecs;
	public CountDown(int min){
	  timeInSecs = min * 60;
	}
	public void drop(){
	  timeInSecs -=1;
	}
	public boolean timeUp(){
	  return timeInSecs == 0;
	}
	public String toString(){
	  String hour, min, sec;
	  sec = (timeInSecs % 60) + "";
	  min = ((timeInSecs/60)%60) + "";
	  hour = ((timeInSecs/60)/60) + "";
	  return hour + ":" + min + ":" + sec;
	}
	public void run(){
	  while (true){
		try{
		  Thread.sleep(1000);
		}catch (InterruptedException e){}
	    drop();
	    System.out.println(toString());
	    if(timeUp())
	      break;
	  }
	}

 public static void main(String[] args){
  
  Thread t = new Thread(new CountDown(120));
  t.start();

 }

}
