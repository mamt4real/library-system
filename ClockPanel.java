package myProjects.animations;
import javax.swing.*;
import java.awt.*; import java.io.*;
import java.time.LocalTime;

public class ClockPanel extends JPanel implements Runnable{
	
	private final static int SECONDS_IN_MINUTE = 60;
	private final static int SECONDS_IN_HALF_HOUR = 30 * SECONDS_IN_MINUTE;
	private final static int SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE;
	private final static int SECONDS_IN_12_HOURS = 12 * SECONDS_IN_HOUR;
	
	int cx, cy,sec,xSec,ySec;
	int radius;
	String s = "";
    public ClockPanel() {
    	//setSize(350,350);
    	cx = getWidth()/2;
    	cy = getHeight()/2;
    	radius = Math.min(getWidth()/2,getHeight()/2);
    	setBackground(Color.green);	
    }
    private double ptRevToRad(double angle){
			return Math.toRadians((450 + angle * -360)% 360.0);
	}
	private void paintDots(Graphics2D g2){
			
	}
	private void paintClockArea(Graphics2D g2){
			//Point2D center = new Point2D.Double(width/2,height/2);
			//g2.setPaint(colorOnCycle(center,radius));
			g2.fillOval(cx - radius,cy - radius,radius * 2, radius*2);
			for(int i= 1; i < 60; i++){
				if(i%5 == 0) continue;
				double angle = ptRevToRad(i/60.0);
				g2.fillRect((int)(cx + Math.cos(angle) * radius * 0.9)-1,(int)(cy - Math.sin(angle)*radius*0.9)-1,3,3);
			}
			g2.drawLine(radius,radius,xSec,ySec);
    	g2.drawString(s,10,20);
	}
    public void paintComponent(Graphics g){
    	paintClockArea((Graphics2D)g);
    	return;
    /*	//super.paint(g);
    	int y = getHeight();
    	int x = getWidth();
    	int n = (x/8);
    	int xCenter = x/2, yCenter = y/2;
        //radius = 3*n;
    	Graphics2D g1 = (Graphics2D)g;
    	g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    	g1.setColor(Color.blue);
    	g1.setPaint(Color.green);
    	paintDots(g1);
    	paintClockArea(g1);
    	// outer Circle
    	g1.fillOval(n,n,2*radius,2*radius);
    	g1.setColor(Color.white);
    	// inner Circle
    	g1.fillOval(n + 15,n + 15,2*radius-30,2*radius-30);
    	g1.setColor(Color.blue);
    	//  Center
    	g1.fillOval(n + radius-5,n + radius-5,10,10);
    	
    	g1.setFont(new Font("Times New Roman",Font.BOLD,16));
    	g1.setColor(Color.black);
    	
    	// plotting the numbers in the clock nb: 15 is the difference in diameter of the circles 16 is the size of the font 
    	g1.drawString("1",n + 3*radius/2 - 15,n+(radius/4) + 15);
    	g1.drawString("2",n + 2*radius - (radius/4 + 15),n+(radius/2) + 15);
    	g1.drawString("3",n + 2*radius - 15 - 16,n+radius);
    	g1.drawString("4",n + 7*radius/4-15+2,n+(3*radius/2)-15 +2);
    	g1.drawString("5",n + 3*radius/2 - 15+2,n+(7*radius/4)-15+2);
    	g1.drawString("6",n + radius-4,n + 2*radius -15-16);
    	g1.drawString("7",n + radius/2 +15-2,n+(7*radius/4)-15+2);
    	g1.drawString("8",n + radius/4 + 15-2,n+(3*radius/2)-15+2);
    	g1.drawString("9",n+25,n+radius + 4);
    	g1.drawString("10",n + radius/4 + 15-8,n+(radius/2) + 15);
    	g1.drawString("11",n + radius/2 +15-8,n+(radius/4)+15);
    	g1.drawString("12",n + radius-8,n+15 + 16);
    
    	//drawing the hands of the clock n+ radius,n+35
    	g1.drawLine(n+radius,n+radius,xSec,ySec);
    	g.drawString(s,10,20);*/
    } 
    public JFrame displayPanel(){
    	JFrame fr = new JFrame("My Panel");
    	fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
    	fr.setSize(350,350);
    	fr.setLayout(new BorderLayout());
    	//y = fr.getHeight();
    	//x = fr.getWidth();
    	ClockPanel c = new ClockPanel();
    	fr.add(c,"Center");
    	//fr.pack();
    	fr.setLocationByPlatform(true);
    	//fr.setVisible(true);
    	return fr;
    }
    public void run(){
    	while(true){
    		try{
    			Thread.sleep(1000);
    		}catch (Exception e){
    			e.printStackTrace();
    		}
    		LocalTime time = LocalTime.now();
    		int sec = time.getSecond();
    	/*	double O = Math.PI*6*sec/180;
    		xSec = getWidth()/2;
    		ySec = (xSec/4) + 35;
    		if(0<= sec && sec < 15){
    			if(sec > 0){
    				xSec += radius*Math.sin(O);
    				ySec += radius*(1- Math.cos(O));
    			}
    		}
    		else if(15<= sec && sec < 30){
    			ySec = getWidth()/2;
    			xSec = 7*getWidth()/2 - 35;
    			if(sec > 15){
    				xSec += radius*(1- Math.cos(O));
    				ySec += radius*Math.sin(O);
    			}
    		}

    		else if(30<= sec && sec < 45){
    			xSec = getWidth()/2;
    			ySec = 7*getWidth()/2 - 35;
    			if(sec > 30){
    				xSec += radius*Math.sin(O);
    				ySec -= radius*(1- (-1*Math.cos(O)));
    			}
    		}
    		else{
    			ySec = getWidth()/2;
    			xSec = ySec/4 + 35;
    			if(sec > 45){
    				xSec += radius*(1- Math.cos(O));
    				ySec += radius*Math.sin(O);
    			}
    		}*/
    		s = time.getHour() + " : " + time.getMinute() + " : " + time.getSecond();
    		repaint();
    		//repaint();
    	}
    }
    public static void main(String[] args){
    	ClockPanel cp = new ClockPanel();
    	cp.displayPanel().setVisible(true);
    	(new Thread(cp)).start();
    }
}