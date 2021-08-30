/**
 * @(#)DateField.java
 *
 *
 * @author 
 * @version 1.00 2019/12/21
 */
package myProjects.library;
import java.awt.event.*;
import javax.swing.*; import javax.swing.text.*;
import java.awt.*; import java.util.*; import java.text.*;
import java.time.LocalTime; import java.time.LocalDate; import java.time.format.DateTimeFormatter;

public class DateField extends JFormattedTextField{
	
	Format format  = new SimpleDateFormat("dd/MMM/yyyy");

    public DateField() {
    	super();
    	MaskFormatter maskf = null;
    	try{
    		maskf = new MaskFormatter("##/***/####");
    	}catch (ParseException e){e.printStackTrace(); 	};
    	maskf.setPlaceholderCharacter('_');
    	setFormatterFactory(new DefaultFormatterFactory(maskf));
    	this.addFocusListener(new FocusAdapter(){
    		public void focusGained(FocusEvent fe){
    			if(getFocusLostBehavior( )== JFormattedTextField.PERSIST)
    				setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
    		}
    		public void focusLost(FocusEvent fe){
    			try{
    				Date date = (Date) format.parseObject(getText());
    				setValue(format.format(date));
    			}catch (ParseException pe){
    				setFocusLostBehavior(JFormattedTextField.PERSIST);
    				setText("");
    				setValue(null);
    			}
    		}
    	});
    }
    
    public void setValue(Date date){
    	super.setValue(toString(date));
    }
    private Date toDate(String sdate){
    	Date date = null;
    	if(sdate == null) return null;
    	try{
    		date = (Date) format.parseObject(sdate);
    	}catch (ParseException pe){    	}
    	return date;
    }
    private String toString(Date date){
    	try{
    		return format.format(date);
    	}catch (Exception e){ return "";}
    }
    public static void main (String args[]){
    	JFrame fr = new JFrame();
    	fr.setSize(200,150);
    	fr.setLayout(new FlowLayout());
   		JTextField fld = new JTextField(15);
   		DateField d = new DateField();
   		d.setPreferredSize(fld.getPreferredSize());
    	fr.add(d); fr.add(fld);
    	//fr.pack();
    	fr.setLocationByPlatform(true);
    	fr.setDefaultCloseOperation(fr.EXIT_ON_CLOSE);
    	JButton btn = new JButton("Click");
    	btn.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){
    			fld.setText(d.getText());
    			System.out.println(d.toDate(fld.getText()));
    			DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    			LocalDate dt = LocalDate.parse(fld.getText().replaceAll("/","-"),df);
    			System.out.println(dt.getMonth() + ", " + dt.getYear());
    		}
    	});
    	fr.add(btn);
    	fr.setVisible(true);
    	
    }
}