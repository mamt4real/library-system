package myProjects.library;

public class Member {

  private String iDnumber, name,gender;
  private int numberOfBooks;
  private AccountStatus status;
  private double fine;
  
  public Member(String id,String nm,int bn,double fn){
    this(id,nm,"Male",bn,fn);
  }
  public Member(String id,String nm,String g,int bn,double fn){
    iDnumber = id;
    name = nm;
    gender = g; 
    numberOfBooks = bn;
    fine = fn;
    status = (fine == 0.0)? AccountStatus.ACTIVE:AccountStatus.SUSPENDED;
  }
  public Member(String in, String m){
    this(in,m,"Male",0,0.0);
  }
  public String getIDNumber(){
    return iDnumber;
  }
  public String getName(){
    return name;
  }
  public int getNumberOfBooks(){
    return numberOfBooks;
  }
  public double getFine(){
  	return fine;
  }
  public AccountStatus getStatus(){
  	return status;
  }
  public boolean isActive(){
  	return getStatus() == AccountStatus.ACTIVE;
  }
  public void addFine(double fn){
  	fine += fn;
  	status = AccountStatus.SUSPENDED;
  }
  public void payFines(double amt){
  	fine = (amt <= fine)?fine - amt:0;
  	if (fine == 0)
  		status = AccountStatus.ACTIVE;
  }
  public void setStatus(AccountStatus st){
  	status = st;
  }
  public void addBook(int n){
    numberOfBooks += n;
  }
  public void removeBook(int r){
    numberOfBooks -= r;
  }
  public String toString(){
    return iDnumber + "#" + name + "#" +  numberOfBooks + "#" + fine;
  }
  public String myPrint(){
  	return String.format("ID No: %s%nName: %s%nAccount Status: %s%nN0 of Books Borrowed:_%d%n%n",iDnumber,name,status,numberOfBooks);
  }

}
