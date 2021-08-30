package myProjects.loginform;
import java.awt.event.*;
import javax.swing.*;  import javax.swing.text.*;
import myProjects.library.Welcome;
import java.awt.*;
import java.util.*; import java.io.*;
import myProjects.library.MainFrame;

public class LoginPanel extends JDialog{
	JFrame window;	String userPath;
	JButton login = new JButton("Login"), cancel = new JButton("Cancel");
	JLabel user = new JLabel("Username:"), pass = new JLabel("Password:");
	JTextField txtUser = new JTextField(15);
	JPasswordField txtPass = new JPasswordField(15);
	JPanel Fr = new JPanel(), lg = new JPanel();
	Admins[] User = new Admins[10];
	String Code = "Admins_Secured_Code";
	JLabel fgt = new JLabel("Forgot password"), reg = new JLabel("Register");
	Font f = new Font("Calibri",Font.ITALIC,12);
	int nobAd = 0;
	//Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.gray,Color.yellow);

    public LoginPanel(JFrame win,String path) throws IOException{
    	setTitle("Login Page");
    	setSize(300,150);
   	    setLocation(500,200);
    	setLayout(new BorderLayout());
    	lg.setBackground(Color.green);
    	Fr.setBackground(Color.green);
    	getRootPane().setDefaultButton(login);
    	setDefaultCloseOperation(HIDE_ON_CLOSE);
    	setModal(true);
    	window = win;	userPath = path;
    	
    	Login l = new Login();
    	cancel.addActionListener(l); login.addActionListener(l);
    	cancel.setBackground(Color.red); login.setBackground(Color.blue);
    	cancel.setForeground(Color.white); login.setForeground(Color.white);
    	//cancel.setBorder(b); login.setBorder(b);
    	
    	ForgotRegister ms = new ForgotRegister();
    	
    	initUsers();
    	lg.setLayout(new FlowLayout());
    	lg.add(user); lg.add(txtUser); lg.add(pass); lg.add(txtPass); lg.add(login); lg.add(cancel);
    	add(lg,"Center");
    	
    	Fr.setLayout(new BorderLayout());
    	fgt.setFont(f); fgt.setForeground(Color.red); fgt.addMouseListener(ms); Fr.add(fgt,"West");
    	reg.setFont(f); reg.setForeground(Color.red); reg.addMouseListener(ms); Fr.add(reg,"East");
    	add(Fr,"South");
    	
    	//setVisible(true);
    }
    class Login implements ActionListener{
    	public void actionPerformed(ActionEvent e){
    		int x = -1;
    				
    		if(e.getSource()== cancel){
    			setVisible(false);
    			MainFrame m = (MainFrame)window;
    			m.setInitials();
    			new Welcome();
    		}
    		else{
    			
    			for(int i =0;i<nobAd;i++)
    			if(txtUser.getText().equals(User[i].getUser())){
    				x = i;
    				break;
    			}
    			
    			if(txtUser.getText().equals("")){
    				JOptionPane.showMessageDialog(login,"Please Enter Your Username");
    				txtUser.requestFocus();
    			}
    			else if(txtPass.getText().equals("")){
    				JOptionPane.showMessageDialog(login,"Please Enter Your Password");
    				txtPass.requestFocus();
    			}
    			else if(x == -1){
    					JOptionPane.showMessageDialog(login,"Incorrect Username","",JOptionPane.ERROR_MESSAGE);
    					txtUser.requestFocus();
    			}
    			else if(!txtPass.getText().equals(User[x].getPass())){
    					JOptionPane.showMessageDialog(login,"Incorrect Password","",JOptionPane.ERROR_MESSAGE);
    					txtPass.requestFocus();
    			}
    			else{
    				JOptionPane.showMessageDialog(txtPass,"Wellcome to A.M.L");
    				hide();
    				try{window.setVisible(true);} catch(Exception xi){xi.printStackTrace(); }
    				
    		}
    		
    	}
    }
    }
    public void initUsers()throws IOException{
    	Scanner in = new Scanner(new FileReader(userPath));
    	StringTokenizer tk;
    	while(in.hasNext()){
    		tk = new StringTokenizer(in.nextLine(),"#");
    		User[nobAd] = new Admins(tk.nextToken(),tk.nextToken(),tk.nextToken(),tk.nextToken());
    		nobAd++;
    	}
    	in.close();
    }
    public void saveUsers()throws IOException{
    	PrintWriter out = new PrintWriter(new FileWriter("Users.txt"));
    	for (int i =0; i<nobAd;i++)
    		out.println(User[i]);
    	out.close();
    }
    class ForgotRegister extends MouseAdapter{
    	Color def;
    	public void mouseEntered(MouseEvent x){
    		Component c = (Component)x.getSource();
    		def = c.getForeground();
    		c.setForeground(Color.white);
    	}
    	public void mouseExited(MouseEvent x){
    		Component c = (Component)x.getSource();
    		c.setForeground(def);
    	}
    	public void mouseClicked(MouseEvent e){
    		if(e.getClickCount()== 2 && e.getSource()== fgt){
    			dispose();
    			new Forgot().show();	
    		}
    		else if(e.getClickCount()== 2 && e.getSource()== reg){
    			dispose();
    			new Register().show();
    		}
    	}
    }
    class Forgot extends JDialog implements ActionListener{
    	JLabel user = new JLabel("  Username:",SwingConstants.CENTER),SecQ = new JLabel("  Question:",SwingConstants.CENTER), pass = new JLabel("  New Password:",SwingConstants.CENTER), pass2 = new JLabel(" Confirm Password:",SwingConstants.CENTER);
    	JButton ok = new JButton("OK"), cncl = new JButton("Cancel");
    	TextField User1 = new TextField(12), ques = new TextField(12);
    	JPasswordField Pass = new JPasswordField(12), Pass2 = new JPasswordField(12);
    	JPanel p1 = new JPanel(), p2 = new JPanel();
    	JLabel[] L = new JLabel[4];
    	protected int x;
    	public Forgot(){
    		setTitle("Forgot Password");
    		setSize(500,200);
    		setLocation(500,200);
    		setLayout(new BorderLayout());
    		setModal(true);
    		p1.setBackground(Color.green); p2.setBackground(Color.green);
    		ok.setBackground(Color.blue); cncl.setBackground(Color.red);
    		p1.setLayout(new GridLayout(4,3,2,1));
    		p2.setLayout(new FlowLayout());
    		setDefaultCloseOperation(HIDE_ON_CLOSE);
    		cncl.setForeground(Color.white); ok.setForeground(Color.white);
    		for(int i=0;i<4;i++)
    			L[i] = new JLabel("",SwingConstants.CENTER);
    		p1.add(user); p1.add(User1);p1.add(L[0]); p1.add(SecQ); p1.add(ques);p1.add(L[1]); p1.add(pass); p1.add(Pass);p1.add(L[2]); p1.add(pass2); p1.add(Pass2);p1.add(L[3]); p2.add(ok); p2.add(cncl);
    		add(p1,"Center"); add(p2,"South"); 
    		ok.addActionListener(this);
    		cncl.addActionListener(this);
    		Font f1 = new Font("Calibri",Font.ITALIC,13);
    		L[2].setFont(f1); L[3].setFont(f1);
    		Pass.addKeyListener(new Ps(L[2],ok)); Pass2.addKeyListener(new KeyAdapter(){
    			public void keyReleased(KeyEvent e){
    				JPasswordField ps = (JPasswordField)e.getSource();
    				String pass = new String(ps.getPassword());
    				if(Pass.getText().equals(null) || (new String(Pass.getPassword())).equals("")){
    					L[3].setText("");
    					ps.setText("");
    					Pass.requestFocus();
    				}
    				else if(!pass.equals(Pass.getText())){
    					L[3].setText("password mismatch");
    					L[3].setForeground(Color.red);
    					ok.setEnabled(false);
    				}
    				else{
    					L[3].setText("password matched");
    					L[3].setForeground(Color.blue);
    					ok.setEnabled(true);
    				}
    			}
    		});
    		x  = -1;
    		User1.addFocusListener(new FocusListener(){
    			public void focusLost(FocusEvent fe){
    				for(int i =0;i<nobAd;i++)
    					if(User1.getText().equals(User[i].getUser())){
    						x = i;
    						break;
    					}
    				if(x == -1){
    					JOptionPane.showMessageDialog(ok,"Username doesn't exist","",JOptionPane.ERROR_MESSAGE);
    					User1.requestFocus();
    				}
    				else{
    					SecQ.setText(User[x].getQues());
    					ques.requestFocus();
    				}
    			}
    			public void focusGained(FocusEvent fe){
    			}
    		});
    		ques.addFocusListener(new FocusListener(){
    			public void focusLost(FocusEvent fe){
    				if(!SecQ.getText().equals("  Question:"))
    				if(ques.getText().equals("")){
    					JOptionPane.showMessageDialog(ok,"This field is required");
    					ques.requestFocus();
    				}
    				else if(!ques.getText().equals(User[x].getAns())){
    						JOptionPane.showMessageDialog(ok,"Wrong Answer!","",JOptionPane.ERROR_MESSAGE);
    						ques.selectAll(); ques.requestFocus();
    				}
    			}
    			public void focusGained(FocusEvent fe){
    			}
    		});
    	}
    	public void actionPerformed(ActionEvent e){
    			
    		if(e.getSource()== cncl)
    			dispose();
    		else{
    				User[x].setPass(Pass.getText());
    				try{saveUsers();}
    				catch(Exception y){ y.printStackTrace();} 
    				JOptionPane.showMessageDialog(ok,"Password changed successfully!","",JOptionPane.INFORMATION_MESSAGE);
    				hide();
    		}
    		try{new LoginPanel(window,userPath).show();}catch(Exception z){z.printStackTrace();}
    	}
    	
    }
    class Register extends JDialog implements ActionListener{
    	JLabel user = new JLabel("  Username:",SwingConstants.LEFT),SecQ = new JLabel("  Security Question:",SwingConstants.LEFT), pass = new JLabel("  Password:",SwingConstants.LEFT), pass2 = new JLabel("  Confirm Password:",SwingConstants.LEFT),ans = new JLabel("  Answer:",SwingConstants.LEFT);
    	JButton ok = new JButton("Register"), cncl = new JButton("Cancel");
    	String[] qs = {"","What is your 1st phone no?","What is your favourite food?","Who is your favourite Uncle?" };
    	JTextField User1 = new JTextField(12), Ans = new JTextField(12);
    	JComboBox ques = new JComboBox(qs);
    	JPasswordField Pass2 = new JPasswordField(12); JPasswordField Pass = new JPasswordField(12);
    	JPanel p1 = new JPanel(), p2 = new JPanel();
    	JLabel[] L = new JLabel[5];
    	
    	public Register(){
    		setTitle("Registeration");
    		setSize(450,200);
    		setLocation(500,200);
    		setLayout(new BorderLayout());
    		setModal(true);
    		p1.setBackground(Color.green); p2.setBackground(Color.green);
    		ok.setBackground(Color.blue); cncl.setBackground(Color.red);
    		p1.setLayout(new GridLayout(5,3,2,1));
    		p2.setLayout(new FlowLayout());
    		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    		ques.setBackground(Color.white);
    		cncl.setForeground(Color.white); ok.setForeground(Color.white);
    		for(int i=0;i<5;i++)
    			L[i] = new JLabel("",SwingConstants.CENTER);
    		p1.add(user); p1.add(User1); p1.add(L[0]); p1.add(pass); p1.add(Pass); p1.add(L[1]); p1.add(pass2); p1.add(Pass2);p1.add(L[2]); p1.add(SecQ); p1.add(ques); p1.add(L[3]); p1.add(ans);p1.add(Ans); p1.add(L[4]); p2.add(ok); p2.add(cncl);
    		add(p1,"Center"); add(p2,"South"); 
    		ok.addActionListener(this);
    		cncl.addActionListener(this);
    		Font f1 = new Font("Calibri",Font.ITALIC,13);
    		L[1].setFont(f1); L[2].setFont(f1);
    		Pass.addKeyListener(new Ps(L[1],ok)); Pass2.addKeyListener(new KeyAdapter(){
    			public void keyReleased(KeyEvent e){
    				JPasswordField ps = (JPasswordField)e.getSource();
    				String pass = new String(ps.getPassword());
    				if(Pass.getText().equals(null) || (new String(Pass.getPassword())).equals("")){
    					L[2].setText("");
    					ps.setText("");
    					Pass.requestFocus();
    				}
    				else if(!pass.equals(Pass.getText())){
    					L[2].setText("password mismatch");
    					L[2].setForeground(Color.red);
    				}
    				else{
    					L[2].setText("password matched");
    					L[2].setForeground(Color.blue);
    				}
    			}
    		});
    	
    	}
    	public void actionPerformed(ActionEvent e){
    		int x = -1;
    		for(int i =0;i<nobAd;i++)
    			if(User1.getText().equals(User[i].getUser())){
    				x = i;
    				break;
    			}	
    		if(e.getSource()== cncl){
    			dispose();
    		}
    		else{
    			if(x != -1){
    					JOptionPane.showMessageDialog(ok,"Username already exist","",JOptionPane.ERROR_MESSAGE);
    					User1.requestFocus();
    			}
    			else if (Pass.getText().equals("") || Pass2.getText().equals("") || Ans.getText().equals("") || ((String)ques.getSelectedItem()).equals("")){
    				JOptionPane.showMessageDialog(ok,"All fields are required","",JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    			else if(!Pass.getText().equals(Pass2.getText())){
    				JOptionPane.showMessageDialog(ok,"Password mismatch","",JOptionPane.ERROR_MESSAGE);
    				Pass2.selectAll(); Pass2.requestFocus();
    			}
    			else{
    				String code = JOptionPane.showInputDialog(null,"Enter Verification Code");
    				try{
    					if(code.equals(Code)){
    						User[nobAd] = new Admins(User1.getText(),Pass.getText(),(String)ques.getSelectedItem(),Ans.getText());
    						nobAd++;
    						try{saveUsers();} catch(Exception b){}
    						JOptionPane.showMessageDialog(ok,"Registration Successful!");
    						dispose();
    					}
    					else{
    						JOptionPane.showMessageDialog(ok,"Incorrect verification code!","",JOptionPane.ERROR_MESSAGE);
    						cncl.doClick();
    					}
    				}catch(NullPointerException xi){xi.printStackTrace();}
    			}
    		}
    		try{new LoginPanel(window,userPath).show();}catch(Exception z){z.printStackTrace();}
    	}
    }
    class Ps extends  KeyAdapter{
    	JLabel lbl; JButton b;
    	public Ps(JLabel l1,JButton b1){
    		lbl = l1;
    		b = b1;
    	}
    	public void keyReleased(KeyEvent e){
    		Component c = (Component)e.getSource();
    		JPasswordField fld = (JPasswordField)c;
    		String s = new String(fld.getPassword());
    		TestingPass t = new TestingPass(s);
    		String p = t.strength();
    		lbl.setText(p);
    		lbl.setForeground(cl(p));
    		if(s.length()<6)
    			b.setEnabled(false);
    		else
    			b.setEnabled(true);
    	}
    	Color cl(String s){
    		if(s.equals("weak"))
    			return new Color(200,0,55);
    		if(s.equals("strong"))
    			return new Color(25,0,225);
    		if(s.equals("extra strong"))
    			return new Color(0,0,220);
    		if (s.equals("super strong"))
    			return new Color(0,0,255);
    		return Color.red;
    	}
    }
    public static void main(String[] args)throws IOException{
    	new LoginPanel(new MainFrame(),"Users.txt").show();
    }
}