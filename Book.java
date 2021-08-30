package myProjects.library;

public class Book {
  
 private String booknumber, author,title,borrower;
 private boolean borrowed;
 
 public Book(String bknm,String auth,String tit,String borw, boolean br){
   booknumber = bknm;
   author = auth;
   title = tit;
   borrower = borw;
   borrowed = br;
   
 }
 public Book(String bk, String ath, String ttl){
   this(bk,ath,ttl,"none",false);
 }
 
 public String getBookNumber(){
   return booknumber;
 }
 public String getAuth(){
   return author;
 }
 public String getTit(){
   return title;
 }
 public String getBorrower(){
   return borrower;
 }
 public boolean isBorrowed(){
   return borrowed;
 }
 public void setBorrowed(boolean b){
   borrowed = b;
 }
 public void setBorrower(String B){
   borrower = B;
 }
 public void setTit(String t){
 	title = t;
 }
 /*public void borrowBook(String id){
   borrower = id;
   borrowed = true;
 }*/
 public void returnBook(){
   borrower = "none";
   borrowed = false;
 }
 public String toString(){
   String av;
   if(borrowed == false)
   av = "Available";
   else av = "Borrowed#" + borrower;
   return booknumber + "#" + author + "#" + title; // + "#" + av;
 }
 public String myPrint(){
 	String av;
   if(borrowed == false)
   av = "Status: Available";
   else av = "Status: Borrowed by " + borrower;
  	return String.format("Book ID: %s%nAuthor: %s%nTitle: %s%n%s%n%n",booknumber,author,title,av);
  }

}
