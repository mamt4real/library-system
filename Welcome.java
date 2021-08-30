package myProjects.library;
import myProjects.loginform.LoginPanel;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*; import java.io.*;

public class Welcome extends JFrame implements ActionListener{
	
	JButton start = new JButton("Start"); 
	JButton quit = new JButton("Quit");
	JPanel south;JLabel wel = new JLabel("ABUHURAIRA'S MEMORIAL LIBRARY",SwingConstants.CENTER);
	Font f = new Font("Algerian",Font.BOLD,22);

    public Welcome() {
    	super("Wellcome Page");
    	setSize(500,500);
   	    //setLocation(500,150);
   	    setLocationByPlatform(true);
    	setLayout(new BorderLayout());
    	getContentPane().setBackground(Color.gray);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    	wel.setFont(f);
    	wel.setForeground(Color.cyan);
    	add(wel,"Center");
    	
    	south = new JPanel();
    	south.setBackground(Color.green);
    	south.setLayout(new FlowLayout());
    	start.setBackground(Color.blue); quit.setBackground(Color.RED);
    	start.setForeground(Color.WHITE); quit.setForeground(Color.WHITE);
    	quit.addActionListener(this);start.addActionListener(this);
    	south.add(start); south.add(quit);
    	add(south,"South");
    	
    	setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getSource()== quit){
    		int x = JOptionPane.showConfirmDialog(this,"Are you sure you want to exit?");
    		if (x == JOptionPane.YES_OPTION)
    			System.exit(0);
    	}
    	else{
    		setVisible(false);
    		try{new LoginPanel(new MainFrame(),"Users.txt").show();} catch(Exception b){b.printStackTrace();}
    	}
    }
    public static void main(String[] args){
    	new Welcome();
    }
    
}