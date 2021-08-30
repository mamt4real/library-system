/**
 * @(#)JClock.java
 *
 *
 * @author 
 * @version 1.00 2019/12/1
 */
package clock;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import javax.swing.JComponent;

public class JClock extends JComponent{
	
	private static final long serialVersionUID = 1L;
	
	private final CoolPaint paint;
	private final Object lock;
	private Thread updater;

    public JClock(CoolPaint paint) {
    	this.paint = paint;
    	this.lock = new Object();
    }
    
    private void runClock(){
    	int lastTime = -1;
    	try{
    		while(isRunning()){
    			Thread.sleep(10);
    			int t = time();
    			if(t != lastTime){
    				lastTime = t;
    				repaint();
    			}
    		}
    	}catch (InterruptedException e){
    		
    	}
    }
    private int time(){
    	return LocalTime.now().get(ChronoField.SECOND_OF_DAY);
    }
    private boolean isRunning(){
    	synchronized(lock){
    		return updater == Thread.currentThread();
    	}
    }
    public void start(){
    	synchronized(lock){
    		if(updater != null) return;
    		updater = new Thread(this::runClock);
    		updater.start();
    	}
    }
    public void stop(){
    	synchronized(lock){
    		updater = null;
    	}
    }
    public void paintComponent(Graphics g){
    	paint.paintClock(getWidth(), getHeight(), time(), (Graphics2D)g);
    }
    
}