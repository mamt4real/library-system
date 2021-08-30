/**
 * @(#)ClockDemo.java
 *
 *
 * @author 
 * @version 1.00 2019/12/1
 */
 package clock;
 import java.awt.EventQueue;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import javax.swing.JFrame;


public class ClockDemo {
	
	public static void main(String[] args){
		EventQueue.invokeLater(ClockDemo::runIt);
	}
	
	private static void runIt(){
		final JFrame j = new JFrame("JClock");
		final JClock clock = new JClock(new CoolPaint());
		j.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				clock.stop();
				j.dispose();
			}
		});
		j.add(clock);
		j.setBounds(20,20,600,500);
		j.setVisible(true);
		clock.start();
	}

    public ClockDemo() {
    }
    
    
}