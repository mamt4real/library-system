/**
 * @(#)CbtPanel.java
 *
 *
 * @author 
 * @version 1.00 2019/12/27
 *
 */
 package myProjects.cbtexam;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;

public class CbtPanel extends JFrame{
	
	JPanel pans[] = new JPanel[8];
	JLabel lbls[] = new JLabel[8];
	JRadioButton ops[] = new JRadioButton[4];
	JButton[] btns;
	Subject sbj;
	ArrayList<Question> ques;

    public CbtPanel(int x){
    	super("CBT Exams Simulation");
    	setLocationByPlatform(true);
    	setSize(600,500);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLayout(null);
    	if(x == 0)
    		add(loginPane());
    	else
    		add(examPanel());
    	setVisible(true);
    }
    public JPanel loginPane(){
    	String[] subs = {"Select Exam","Math 101","Math 102"	};
    	JComboBox cb = new JComboBox(subs);
    	Box bx = new Box(BoxLayout.Y_AXIS);
    	bx.add(cb);
    	bx.add(new JLabel("Registratio Number"));
    	bx.add(new JTextField(15));
    	bx.add(new JLabel("Password"));
    	bx.add(new JTextField(15));
    	JButton b1 = new JButton("Proceed");
    	b1.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			/*getContentPane().removeAll();
    			add(examPanel());*/
    			dispose();
    			new CbtPanel(1);
    		}
    	});
    	bx.add(b1);
    	pans[0] = new JPanel();
    	pans[0].setBounds(200,10,200,400);
    	pans[0].setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),"Login Details"));
    	pans[0].add(bx,BorderLayout.CENTER);
    	return pans[0];
    	
    }
    public JPanel examPanel(){
    	pans[0] = new JPanel();
    	pans[0].setBounds(10,10,220,520);
    	pans[0].setLayout(null);
    	pans[1] = new JPanel();
    	pans[1].setBounds(0,0,200,500);
    	pans[0].add(pans[1]);
    	Box bx = new Box(BoxLayout.Y_AXIS);
    	bx.add(new JLabel("Name: Mahadi"));
    	pans[1].add(bx);
    	pans[0].add(pans[1]);
    	return pans[0];
    }
    
    public static void main(String args[]){
    	new CbtPanel(0);
    }
    
}