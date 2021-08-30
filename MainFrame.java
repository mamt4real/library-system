package myProjects.library;
import java.awt.event.*; import java.awt.font.*;
import javax.swing.*; 
import java.awt.*; import java.util.*; import java.io.*;
import java.time.LocalTime; import java.time.LocalDate;
import clock.*; import javax.swing.border.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MainFrame extends JFrame{
	
	Library lib;
	
	JPanel p1 = new JPanel(), p2 = new JPanel(), p3 = new JPanel(), p4 = new JPanel(),p5 = new JPanel(),p6 = new JPanel(), p7 = new JPanel();
	JLabel head = new JLabel("");
	JScrollPane scrl; JTextArea txt, notice = new JTextArea();
	JLabel[] services = new JLabel[10];
	JButton btns[] = new JButton[5];
	DateField datefld = new DateField();
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MMM/yyyy"); 
	JClock clock;
	String defaultNotice = "\tOpening Hours\nMonday to Friday\n\t8:00am to 10:00pm\nWeekends\n\t10:00am to 5:00pm";
	Border b1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,new Color(128,0,128),Color.black);
	Border b2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED,Color.pink,Color.green);
	Border b = BorderFactory.createCompoundBorder(b1,b1);
	String fields[] = {"","Science","Computing","Mathematics","programming","Art","History","Medicine","Statistics"};
	String[] bl = { "Return","Search Books","Edit Books","Notice","format" };
	String[] serv = {"Menu", " 1. Add new Member", " 2. List All Members", " 3. Add new Book", " 4. List All Books", " 5. Borrow Book", " 6. Return a Book", " 7. Remove a Member", " 8. Clear Dispaly Pane","NOTICE BOARD"};
	Font f = new Font("Times New Roman",Font.BOLD,18);
    public MainFrame() throws IOException{
    	super("ABUHURAIRA'S MEMORIAL LIBRARY");
    	setSize(915,600);
    	setLayout(new BorderLayout());
    	//setVisible(true);
    	//setLocationByPlatform(true);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	lib = new Library();
    	
    	p1.setLayout(new GridLayout(9,1,2,2)); p1.setBackground(Color.orange);
    	p2.setLayout(new GridLayout(1,5,3,0));    p2.setBackground(Color.green);
    	p3.setLayout(new GridLayout(1,3,5,1));
    	p4.setLayout(new BorderLayout());    p4.setBackground(Color.cyan);
    	p5.setLayout(new GridLayout(2,1,1,3)); p7.setBackground(Color.cyan);
    	clock = new JClock(new CoolPaint());
    	p6.setLayout(new BorderLayout());
    	p6.setBorder(BorderFactory.createTitledBorder(b,"Clock")); p1.setBorder(b); p4.setBorder(BorderFactory.createTitledBorder(b,"Display Pane")); p7.setBorder(b);
    	p6.add(clock,"Center");
    	//String m2[] = {"MyClock.png","300","300","16:30:00"};
    	//ClockSave.main(m2);
    	p7.setLayout(new BorderLayout());
    	p5.add(p6); clock.start();
    	datefld.setFont(new Font("Calibiri",Font.BOLD,12));
    	p3.add(p1); p3.add(p4);p3.add(p5);
    	add(p3,"Center"); add(p2, "South");
    	 
    	services[0] = new JLabel(serv[0],SwingConstants.CENTER); p1.add(services[0]); services[0].setFont(new Font("Algerian",Font.BOLD,25)); services[0].setForeground(new Color(0,100,0));
    	for(int i=1; i<10; i++){
    		services[i] = new JLabel(serv[i],SwingConstants.NORTH_EAST);
    		services[i].setFont(f);
    		services[i].setForeground(Color.blue);
    		if(i == 9) continue;
    		services[i].addMouseListener(new MyService(i));
    		p1.add(services[i]);
    	}
    	notice.setBackground(Color.green);	notice.setForeground(Color.black);	notice.setFont(new Font("Calibiri",Font.BOLD,14));
    	p7.add(services[9],"North"); p7.add(new JScrollPane(notice),"Center");
    	p5.add(p7);
    	for(int i =0; i<5;i++){
    		btns[i] = new JButton(bl[i]);
    		btns[i].setBackground(Color.blue);
    		btns[i].setForeground(Color.white);
    		btns[i].setBorder(b);
    		btns[i].addActionListener(new LowerButtons(i));
    		p2.add(btns[i]);
    	}
    	Notice(); notice.setEditable(false); notice.setText(defaultNotice);
    	Font ft = services[0].getFont(); Map m = ft.getAttributes(); m.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON); m.put(TextAttribute.WEIGHT,TextAttribute.WEIGHT_BOLD); services[0].setFont(ft.deriveFont(m));services[9].setFont(ft.deriveFont(m));
    	//services[0] = new JLabel("<html><h1><u>" + serv[0] + "</u></h1></html>"); // 
    	
    	txt = new JTextArea(); txt.setForeground(Color.blue); txt.setFont(f); txt.setBackground(Color.cyan); txt.setEditable(false);
    	scrl = new JScrollPane(txt);
    	head.setFont(f);m = head.getFont().getAttributes(); m.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON); m.put(TextAttribute.WEIGHT,TextAttribute.WEIGHT_BOLD); head.setFont(head.getFont().deriveFont(m));head.setForeground(new Color(0,100,0));
    	p4.add(head,"North"); p4.add(scrl,"Center");
    	
    	
    }
    
    class MyService extends MouseAdapter{
    		Color def; int sType;
    		public MyService(int x){
    			sType = x;
    		}
    	public void mouseEntered(MouseEvent x){
    		Component c = (Component)x.getSource();
    		def = c.getForeground();
    		c.setForeground(Color.red);
    	}
    	public void mouseExited(MouseEvent x){
    		Component c = (Component)x.getSource();
    		c.setForeground(def);
    	}
    	public void mouseClicked(MouseEvent e){
    		if(e.getClickCount()== 2){
    			new GuiLibrary(sType).controller(sType);
    		}
    		/*else if(e.getClickCount()== 2 && e.getSource()== reg){
    			new Register().show();
    		}*/
    	}
    }
    
    public static void main(String[] args)throws IOException{
		new MainFrame().setVisible(true);
		

	}
	class GuiLibrary {
		int X;
		JPanel[] pans = new JPanel[8];
		JLabel[] lbl = new JLabel[8];
		JTextField[] fld = new JTextField[8];
		JButton ok, cncl = new JButton("Cancel"); JRadioButton M,F;
		JDialog dialog = new JDialog();
		JComboBox f = new JComboBox(fields);
		ButtonGroup grp = new ButtonGroup(); 
		String id,nm,tit,aut;	int no;
		public GuiLibrary(int in){
			for(int i = 0; i<8;i++){
				pans[i] = new JPanel();
				pans[i].setBackground(Color.green);
				lbl[i] = new JLabel("",SwingConstants.LEFT);
				lbl[i].setFont(new Font("Times New Roman",Font.BOLD + Font.ITALIC,14));
				lbl[i].setForeground(Color.black);
				fld[i] = new JTextField(14);
				fld[i].setFont(new Font("Calibiri",Font.BOLD,12));
			}
			dialog.setTitle("");
    		dialog.setSize(400,200);
    		dialog.setLocationRelativeTo(null);
    		dialog.setModal(true);
    		dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    		cncl.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				dialog.dispose();
    			}
    		});
    		X = in;
		}
		public JPanel S1(){
			pans[0].setLayout(new BorderLayout());
			pans[1].setLayout(new FlowLayout());
			pans[2].setLayout(new GridLayout(4,2,0,3));
			lbl[0].setText(" Member ID:"); pans[2].add(lbl[0]);
			pans[2].add(fld[0]);
			lbl[1].setText(" Member Name:"); pans[2].add(lbl[1]);
			pans[2].add(fld[1]);
			lbl[3].setText("Teller No:"); pans[2].add(lbl[3]);
			pans[2].add(fld[2]);
			lbl[2].setText(" Gender:"); pans[2].add(lbl[2]);
			pans[3].setLayout(new FlowLayout());pans[2].add(pans[3]);
			pans[3].add(M = new JRadioButton("Male",true)); pans[3].add(F = new JRadioButton("Female"));
			grp.add(M); grp.add(F); M.setBackground(Color.green); F.setBackground(Color.green);
			pans[1].add(ok = new JButton("  Add  ")); ok.setBackground(Color.blue); ok.setForeground(Color.white);
			pans[1].add(cncl); cncl.setBackground(Color.red); cncl.setForeground(Color.white);
			ok.setBorder(b); cncl.setBorder(b);
			pans[2].setBackground(Color.green); pans[3].setBackground(Color.green); pans[1].setBackground(Color.green);
			pans[0].add(pans[1],"South"); pans[0].add(pans[2],"Center");
			M.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e){
					tit = "Male";
				}
			});
			F.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent e){
					tit = "Female";
				}
			});
			
			ok.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					id = fld[0].getText();
					nm = fld[1].getText();
					if(id.equals("") || nm.equals("")){
						JOptionPane.showMessageDialog(dialog,"Both ID and Name Fields are required!");
						fld[1].requestFocus();
					}
					else if(Library.findMember(id) == -1){
						try{
							 lib.addMember(new Member(id,nm,tit,0,0.0));
						}catch(Exception i){
							i.printStackTrace();
						}
						JOptionPane.showMessageDialog(dialog,"Member added Successfully!");
						dialog.dispose();
					}
					else{
						JOptionPane.showMessageDialog(dialog,"Same id exist");
						fld[0].requestFocus();fld[0].selectAll();	
					}
				
				}
			});
			pans[0].setBorder(b);
			return pans[0];
			
		}
		void S2(){
			head.setText("Club Members");
			lib.listMembers(txt);
		}
		public JPanel S3(){
			pans[0].setLayout(new BorderLayout());
			pans[1].setLayout(new FlowLayout());
			pans[2].setLayout(new GridLayout(5,2,0,3));
			pans[3].setLayout(new GridLayout(1,2,3,0));pans[4].setLayout(new GridLayout(1,2,3,0));
			pans[5].setLayout(new FlowLayout(FlowLayout.LEFT,5,1)); pans[6].setLayout(new FlowLayout(FlowLayout.LEFT,5,1));
			lbl[0].setText(" Book ID:	*"); pans[2].add(lbl[0]);
			pans[2].add(fld[0]);
			lbl[1].setText(" Book Title:	*"); pans[2].add(lbl[1]);
			pans[2].add(fld[1]);
			pans[2].add(pans[3]);pans[2].add(pans[4]);
			lbl[6].setText("Edition"); pans[3].add(lbl[6]);
			pans[3].add(fld[2]);
			lbl[5].setText(" No of Copies:"); pans[4].add(lbl[5]);
			pans[4].add(fld[5]);
			lbl[7].setText("Date Published: *");
			pans[5].add(lbl[7]);pans[5].add(datefld);datefld.setText("");
			pans[2].add(pans[5]);
			lbl[2].setText(" Field:");pans[6].add(lbl[2]);
			pans[6].add(f); pans[2].add(pans[6]);
			lbl[3].setText(" AUTHOR:	*"); pans[2].add(lbl[3]);
			pans[2].add(fld[4]);
			pans[1].add(ok = new JButton("  Add  ")); ok.setBackground(Color.blue); ok.setForeground(Color.white);
			ok.setBorder(b); cncl.setBorder(b);
			pans[1].add(cncl); cncl.setBackground(Color.red); cncl.setForeground(Color.white);
			pans[2].setBackground(Color.green); pans[3].setBackground(Color.green); pans[1].setBackground(Color.green);
			pans[0].add(pans[1],"South"); pans[0].add(pans[2],"Center");
			ok.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					id = fld[0].getText();	//Book id
					tit = fld[1].getText(); //Book title
					nm = fld[4].getText();	//Author
					aut = (String)f.getSelectedItem();	//field
					DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); LocalDate dt = null;
					if(!datefld.getText().equals("__/___/____"))
						dt = LocalDate.parse(datefld.getText().replaceAll("/","-"),df);
					String ed = (fld[2].getText().equals(""))? "1st edition" : fld[2].getText();
					no = (fld[5].getText().equals(""))? 1:Integer.parseInt(fld[5].getText()) ;
					if(id.equals("") || nm.equals("")|| tit.equals("") || datefld.toString().equals("")){
						JOptionPane.showMessageDialog(dialog,"All fields marked * are required!");
					}
					else if(Library.findBook(id) == -1){
						try{
							 lib.addBook(new Book2(id,nm,tit,ed,aut,dt,no));
						} 
						catch(Exception i){
							i.printStackTrace();
						}
						JOptionPane.showMessageDialog(dialog,"Book added Successfully!");
						dialog.dispose();
					}
					else{
						JOptionPane.showMessageDialog(dialog,"Same Book id exist");
						fld[0].requestFocus();fld[0].selectAll();	
					}
				
				}
			});
			pans[0].setBorder(b);
			return pans[0];
			
		}
		void S4(){
			head.setText("List of Books");
			lib.listBooks(txt);
		}
		public JPanel S5(){
			pans[0].setLayout(new BorderLayout());
			pans[1].setLayout(new FlowLayout());
			pans[2].setLayout(null);
			lbl[0].setText("Member ID:"); lbl[0].setBounds(30,10,80,25);
			fld[0].setBounds(110,10,250,25);
			pans[2].add(lbl[0]);
			pans[2].add(fld[0]);
			lbl[1].setText("Book ID:"); lbl[1].setBounds(30,40,70,25);
			fld[1].setBounds(110,40,250,25);
			pans[2].add(lbl[1]);
			pans[2].add(fld[1]);
			pans[1].add(ok = new JButton("Proceed")); ok.setBackground(Color.blue); ok.setForeground(Color.white);
			pans[1].add(cncl); cncl.setBackground(Color.red); cncl.setForeground(Color.white);
			ok.setBorder(b); cncl.setBorder(b);
			pans[2].setBackground(Color.green); pans[3].setBackground(Color.green); pans[1].setBackground(Color.green);
			pans[0].add(pans[1],"South"); pans[0].add(pans[2],"Center");
			fld[0].addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent fe){
				}
				public void focusLost(FocusEvent fe){
					int x = Library.findMember(fld[0].getText());
					if(fld[0].getText().equals("")){
						JOptionPane.showMessageDialog(fld[0],"Member ID is required");
						fld[0].requestFocus();
					}
					else if(x == -1){
						JOptionPane.showMessageDialog(fld[0],"Sorry this ID is not a registered member");
						fld[0].requestFocus();
					}
					else if(lib.getMember(x).getNumberOfBooks()== 3){
						if(X==5){
						JOptionPane.showMessageDialog(fld[0],"Sorry you can not borrow more than 3 books");
						fld[0].requestFocus();
						}
					}
					else{
						if(!(X==7))
						fld[1].requestFocus();
					}	
				}
			});
			ok.addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e){
					int y = Library.findBook(fld[1].getText());
					if(fld[1].getText().equals("")){
						if( X == 5)
							JOptionPane.showMessageDialog(fld[0],"Supply the ID of the book you wish to borrow");
						else if(X == 6) 
							JOptionPane.showMessageDialog(fld[0],"Supply the ID of the book you wish to return");
						fld[1].requestFocus();
					}
					else if(y == -1){
						JOptionPane.showMessageDialog(fld[1],"Sorry we do not have this book");
						fld[1].requestFocus();
					}
					else{
						if (X == 5){
							try{lib.borrowBook(fld[1].getText(),fld[0].getText());}catch (Exception ei){ ei.printStackTrace();}
						}
						else if(X == 6){
							try{ lib.returnBook(lib.getBook(y),lib.getMember(fld[0].getText())); }catch (Exception ei){ ei.printStackTrace();}
						}
						dialog.dispose();		
					}
				}
			});
			pans[0].setBorder(b);
			return pans[0];
		}
		public JPanel S7(){
			pans[0].setLayout(new BorderLayout());
			pans[1].setLayout(new FlowLayout());
			pans[2].setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
			lbl[0].setText("Member ID:"); pans[2].add(lbl[0]);
			pans[2].add(fld[0]);
			pans[1].add(ok = new JButton("Proceed")); ok.setBackground(Color.blue); ok.setForeground(Color.white);
			pans[1].add(cncl); cncl.setBackground(Color.red); cncl.setForeground(Color.white);
			pans[2].setBackground(Color.green); pans[3].setBackground(Color.green); pans[1].setBackground(Color.green);
			ok.setBorder(b); cncl.setBorder(b);
			pans[0].add(pans[1],"South"); pans[0].add(pans[2],"Center");
			ok.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					int x = Library.findMember(fld[0].getText());
					if(fld[0].getText().equals("")){
						JOptionPane.showMessageDialog(fld[0],"Member ID is required");
						fld[0].requestFocus();
					}
					else if(x == -1){
						JOptionPane.showMessageDialog(fld[0],"Sorry this ID is not a registered member");
						fld[0].requestFocus();
					}
					else{
						String name = lib.getMember(x).getName();
						int y = JOptionPane.showConfirmDialog(ok,"Are you sure you want to remove\n\t" + name + " from the club?");
						if(y == JOptionPane.YES_OPTION){
							try{lib.removeMember(fld[0].getText());}catch (Exception ei){ ei.printStackTrace();}
						}
						dialog.dispose();
					}
				}
			});
			pans[0].setBorder(b);
			return pans[0];
		}
		public void controller(int x){
			
			if(x==1){
				dialog.setTitle("Add new Member");
				dialog.setContentPane(S1());
				dialog.show();
			}
			else if(x == 2){
				S2();
			}
			else if(x == 3){
				dialog.setTitle("Add new Book");
				dialog.setContentPane(S3());
				dialog.show();
			}
			else if(x == 4){
				S4();
			}
			else if((x == 5) || (x == 6)){
				if( x == 5)
					dialog.setTitle("Borrowing a Book");
				else
					dialog.setTitle("Returning a Book");
				dialog.setContentPane(S5());
				dialog.show();
			}
			else if(x == 7){
				dialog.setTitle("Removing a member");
				dialog.setContentPane(S7());
				dialog.show();
			}
			else if(x == 8){
				txt.setText("");
				head.setText("");
			}
		}
	}
	public void Notice(){
		JPopupMenu pop = new JPopupMenu();
		final JButton ok,cncl;
		final JPanel pl = new JPanel();
		pl.setLayout(new FlowLayout());
		final JMenuItem add,clear,edit,save;
		final JDialog note = new JDialog();
		JTextArea nt = new JTextArea();
		note.setLayout(new BorderLayout());
		note.setSize(400,200);
		note.setModal(true);
		note.setLocationRelativeTo(txt);
		note.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		pl.add(ok = new JButton("Post"),"South"); ok.setBackground(Color.blue); ok.setForeground(Color.white);
		pl.add(cncl = new JButton("Cancel"),"South"); cncl.setBackground(Color.red); cncl.setForeground(Color.white);
		pl.setBackground(Color.green);
		ok.setBorder(b); cncl.setBorder(b);
		cncl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nt.setText("");
				note.dispose();
			}
		});
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				notice.append("\n" + nt.getText());
				note.dispose();
			}
		});
		note.add(pl,"South"); note.add(new JScrollPane(nt),"Center");
		pop.add(add = new JMenuItem("Add"));
		pop.add(save = new JMenuItem("Save"));
		pop.add(clear = new JMenuItem("Clear"));
		pop.add(edit = new JMenuItem("Edit"));
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				note.show();
			}
		});
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				notice.setText(defaultNotice);
			}
		});
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				notice.setEditable(true);
			}
		});
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				notice.setEditable(false);
			}
		});
		btns[3].setToolTipText("Right click to have options");
		btns[3].addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JComponent c = (JComponent)e.getComponent();
				if(e.isMetaDown()){
					pop.show(c,e.getX(),e.getY()-80);
				}
				else if(e.getClickCount() == 2)
					add.doClick();
			}
		});
	}
	class LowerButtons implements ActionListener{
		int x;
		JDialog dialog = new JDialog(); JPanel pn1 = new JPanel(), pn2 = new JPanel(), pn3 = new JPanel();
		JRadioButton[] options = new JRadioButton[4]; JButton ok = new JButton("Proceed"), cncl = new JButton("Cancel");
		ButtonGroup grp = new ButtonGroup();
		int sch = 0;
		String[] opt = {"1. By Title","2. By Author","3. By Field","4. By publish Date"};
		public LowerButtons(int X){
			x = X;
			dialog.setTitle("Search Books");
    		dialog.setSize(400,200);
    		dialog.setLocationRelativeTo(txt);
    		dialog.setLayout(new BorderLayout());
    		JLabel spcfy = new JLabel("Please specify your search criteria..",SwingConstants.CENTER);
    		spcfy.setFont(f);
    		pn3.setBackground(Color.green); spcfy.setForeground(Color.blue); pn3.add(spcfy);
    		dialog.add(pn3,"North");
    		pn1.setLayout(new GridLayout(2,2,3,3));
    		pn1.setBackground(Color.green); pn2.setBackground(Color.green);
    		dialog.add(pn1,"Center");
    		ok.setBackground(Color.blue); ok.setForeground(Color.white);
    		ok.addActionListener(this);
    		cncl.setBackground(Color.red); cncl.setForeground(Color.white);
    		ok.setBorder(b); cncl.setBorder(b);
    		cncl.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					dialog.dispose();
				}
		    });
    		pn2.setLayout(new FlowLayout()); pn2.add(ok); pn2.add(cncl);
    		dialog.add(pn2,"South");
    		for(int i=0;i<4;i++){
    			options[i] = new JRadioButton(opt[i]);
    			options[i].setFont(f);
    			options[i].setBackground(Color.green); options[i].setForeground(Color.blue);
    			grp.add(options[i]);
    			pn1.add(options[i]);
    		}
		}
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == ok){
				String s;
				if(options[2].isSelected()){
					s = JOptionPane.showInputDialog(null,"Enter the Field");
					try{if(!s.equals("")){
						head.setText("List of Books in the Field of " + s);
						lib.searchByFld(txt,s);
					}}catch(NullPointerException n){}	
				}
				else if(options[1].isSelected()){
					s = JOptionPane.showInputDialog(null,"Enter the Author");
					try{if(!s.equals("")){
						head.setText("List of Books by " + s);
						lib.searchByAuth(txt,s);
					}}catch(NullPointerException n){}	
				}
				else if(options[0].isSelected()){
					s = JOptionPane.showInputDialog(null,"Enter the title");
					try{if(!s.equals("")){
						head.setText("List of Books titled " + s);
						lib.searchByTit(txt,s);
					}}catch(NullPointerException n){}	
				}
				else if(options[3].isSelected()){
					byDate().show();
				}
				dialog.dispose();
			}
			else if(x == 0){
				clock.stop();
				dispose();
				setInitials();
				new Welcome();
			}
			else if(x == 1){
				dialog.show();
			}
			else if(x == 2){
				new EditBooks().show();
			}	
		}
		public JDialog byDate(){
			JDialog diag = new JDialog();
			JLabel lbl = new JLabel("Choose Date:");
			diag.getContentPane().setBackground(Color.green);
			diag.setLayout(new FlowLayout());
			diag.add(lbl); diag.add(datefld);datefld.setText("");
			JButton ok = new JButton("Find");
			ok.setBackground(Color.blue); ok.setForeground(Color.white);
    		ok.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				LocalDate dt = null;
					if(!datefld.getText().equals("__/___/____")){
						dt = LocalDate.parse(datefld.getText(),df);
						head.setText("Books Published on " + datefld.getText());
						lib.searchByDate(txt,dt);
						diag.dispose();
					}
    			}
    		});
    		JButton cncl = new JButton("Cancel");cncl.setBackground(Color.blue); cncl.setForeground(Color.white);
    		diag.add(ok); diag.add(cncl);
    		cncl.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					diag.dispose();
				}
		    });
    		diag.pack(); diag.setLocationRelativeTo(txt);
    		return diag;
		}
	}
	class EditBooks extends JDialog{
		ArrayList<Book2> books = lib.getBooks();
		ListIterator iter = books.listIterator();
		JPanel pans[] = new JPanel[7];
		JTextField flds[] = new JTextField[7];
		JLabel lbls[] = new JLabel[7];
		JButton[] btns = new JButton[4];
		int nxtprv = 0; int index = 0;
		String[] labels = {"Book ID:","Title:","Edition:","No of Copies:","Date Published:","Field","Author"," Close ","  Next  ","Previous","  Save  "};
		public EditBooks(){
			setTitle("Edit Books");
			setSize(415,215);
    		setLocationRelativeTo(null);
    		setModal(true);
    		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			for(int i = 0; i<7;i++){
				pans[i] = new JPanel();
				pans[i].setBackground(Color.green);
				lbls[i] = new JLabel(labels[i],SwingConstants.LEFT);
				lbls[i].setFont(new Font("Times New Roman",Font.BOLD + Font.ITALIC,14));
				lbls[i].setForeground(Color.black);
				flds[i] = new JTextField(14);
				flds[i].setFont(new Font("Calibiri",Font.BOLD,12));
				if(i>3) continue;
				btns[i] = new JButton(labels[i+7]);
				btns[i].setBackground(Color.blue);
				btns[i].setForeground(Color.white);
				btns[i].setBorder(b);
			}
			btns[0].setBackground(Color.red);
			pans[6].setLayout(new BorderLayout());
			pans[0].setLayout(new GridLayout(5,2,0,3));
			pans[1].setLayout(new FlowLayout());
			pans[2].setLayout(new GridLayout(1,2)); pans[3].setLayout(new GridLayout(1,2));
			pans[2].add(lbls[2]); pans[2].add(flds[2]);pans[3].add(lbls[3]); pans[3].add(flds[3]);
			pans[4].setLayout(new FlowLayout(FlowLayout.LEFT,3,1)); pans[5].setLayout(new FlowLayout(FlowLayout.LEFT,3,1));
			pans[4].add(lbls[4]); pans[4].add(datefld);pans[5].add(lbls[5]); pans[5].add(flds[5]);
			pans[0].add(lbls[0]);pans[0].add(flds[0]);pans[0].add(lbls[1]);pans[0].add(flds[1]);
			pans[0].add(pans[2]);pans[0].add(pans[3]);pans[0].add(pans[4]);pans[0].add(pans[5]);
			pans[0].add(lbls[6]);pans[0].add(flds[6]);
			pans[1].add(btns[0]);pans[1].add(btns[1]);pans[1].add(btns[2]);pans[1].add(btns[3]);
			pans[6].add(pans[0],"Center");pans[6].add(pans[1],"South");pans[6].setBorder(b);//pans[0].setBorder(b);
			setContentPane(pans[6]);
    		setFields((Book2)iter.next());
    		nxtprv = 1;
    		btns[0].addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				dispose();
    			}
    		});
    		btns[1].addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				if(iter.hasNext()){
    					if(nxtprv == 0)
    						iter.next();
    					setFields((Book2)iter.next());
    					index++; nxtprv = 1;
    					//System.out.println(index);
    				}
    			}
    		});
    		btns[2].addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				if(iter.hasPrevious()){
    					if(nxtprv == 1)
    						iter.previous();
    					setFields((Book2)iter.previous());
    					index--; nxtprv = 0;
    				}
    			}
    		});
    		btns[3].addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent e){
    				saveBook((Book2)books.get(index));
    			}
    		});
		}
		public void setFields(Book2 b){
			flds[0].setText(b.getBookNumber());
			flds[1].setText(b.getTit());
			flds[2].setText(b.getEdit());
			flds[3].setText(b.getCopies());
			datefld.setText(b.getDate());
			flds[5].setText(b.getField());
			flds[6].setText(b.getAuth());
			flds[0].setEditable(false);flds[6].setEditable(false);
		}
		public void saveBook(Book2 b){
			b.setTit(flds[1].getText());
			b.setEdition(flds[2].getText());
			b.setField(flds[5].getText());
			b.setPublishDate(LocalDate.parse(datefld.getText(),df));
			try{ lib.updateBook(b);	}catch(Exception e){e.printStackTrace();}
		}
	}
	public void setInitials(){
		lib.setInitials();
	}
	
}