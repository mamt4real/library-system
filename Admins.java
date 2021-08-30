package myProjects.loginform;

public class Admins {
	
	private String username,password,questn,answer;

    public Admins(String us,String ps,String qs,String as ) {
    	username = us;
    	password = ps;
    	questn = qs;
    	answer = as;
    }
    public String getUser(){
    	return username;
    }
    public String getPass(){
    	return password;
    }
    public String getQues(){
    	return questn;
    }
    public String getAns(){
    	return answer;
    }
    public void setPass(String pass){
    	password = pass;
    }
    public String toString(){
    	return String.format("%s#%s#%s#%s",username,password,questn,answer);
    }
    
    
}