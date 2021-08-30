package myProjects.library;
import java.io.*; import java.util.*; import javax.swing.*;import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Library {

 static Scanner In; 
 static StringTokenizer tk;
 private static Book2[] books = new Book2[20];
 private static Member[] mmbs = new Member[20];
 private static int no_bk =0, no_mb =0;
 private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
 public Library()throws IOException{
   initBooks();
   initMmbs();
 }
 public Book getBook(int i){
 	if(i==-1)
 		return null;
   return books[i];
 }
 public Member getMember(int i){
 	if(i==-1)
 		return null;
   return mmbs[i];
 }
 public Member getMember(String mmbrID){
 	int i = findMember(mmbrID);
 	return (i==-1)?null: mmbs[i];
 }
 public boolean isBorrowed(int i){
   return books[i].isBorrowed();
 }
 public boolean isAvailable(int i){
 	return books[i].isAvailable();
 }
 public void addBook(Book2 b)throws IOException{
   if(no_bk<20){
   books[no_bk] = b;
   no_bk++;
   saveBooks();
   }
   else{
   	JOptionPane.showMessageDialog(null,"Service unavailable !\nContact Administrator");
   	System.out.println("\nLibrary is full! ");
  }
 }
 public void addMember(Member m)throws IOException{
 
   if(no_mb<20){
   mmbs[no_mb] = m;
   no_mb++;
   saveMembers();
   }
   else{
   	JOptionPane.showMessageDialog(null,"Service unavailable !\nContact Administrator");
   	System.out.println("\nClub is full! ");
   }
 }
 public static int findBook(String id){
   int i;
   for(i=0;i<no_bk;i++)
   if(books[i].getBookNumber().equals(id))
       return i;
   return -1;
 }
 public static  int findBook(Book k){
   for(int i=0;i<no_bk;i++)
   if(books[i].getBookNumber().equals(k.getBookNumber()))
   return i;
  return -1;
 }
  public static int findMember(String id){
   int i;
   for(i=0;i<no_mb;i++)
   if(mmbs[i].getIDNumber().equals(id))
       return i;
   return -1;
 }
 public static int findMember(Member k){
   for(int i=0;i<no_mb;i++)
   if(mmbs[i].getIDNumber().equals(k.getIDNumber()))
   return i;
   return -1;
 }
 public void borrowBook(String bkID, String mbID)throws IOException{
   int i = findBook(bkID);
   int j = findMember(mbID);
   books[i].borrowBook(getMember(j));
   saveBooks();
   saveMembers();
 }
 public void returnBook(Book b,Member m)throws IOException{
   int i = findBook(b);
   books[i].returnBook(m);
   saveBooks();
   saveMembers();
 }
 public void listBooks( JTextArea a){
 	String s = "";
    for(int i =0; i<no_bk;i++)
   		s += books[i].myPrint();
    a.setText(s);
 }
 public void listMembers(JTextArea a){
   String s = "";
    for(int i =0; i<no_mb;i++)
   		s += mmbs[i].myPrint();
    a.setText(s);
    //System.out.println(s);
 }
 public void searchByFld(JTextArea a,String field){
   String s = "";
    for(int i =0; i<no_bk;i++)
    	if(books[i].getField().compareToIgnoreCase(field) == 0)
   			s += books[i].myPrint();
    if(s.equals(""))
    	a.setText("No Available Books in this Field");
    else
    	a.setText(s);
    //System.out.println(s);
 }
 public void searchByAuth(JTextArea a,String field){
   String s = "";
    for(int i =0; i<no_bk;i++)
    	if(books[i].getAuth().compareToIgnoreCase(field) == 0)
   			s += books[i].myPrint();
    if(s.equals(""))
    	a.setText("No Available Books by this Author");
    else
    	a.setText(s);
    //System.out.println(s);
 }
 public void searchByTit(JTextArea a,String tit){
   String s = "";
    for(int i =0; i<no_bk;i++)
    	if(books[i].getTit().toLowerCase().contains(tit.toLowerCase()))
   			s += books[i].myPrint();
    if(s.equals(""))
    	a.setText("No Available Books by this title");
    else
    	a.setText(s);
    //System.out.println(s);
 }
 public void searchByDate(JTextArea a,LocalDate date){
   String s = "";
   String sdate = date.getMonth() + ", " + date.getYear();
    for(int i =0; i<no_bk;i++)
    	if(books[i].getPubDate().equals(sdate))
   			s += books[i].myPrint();
    if(s.equals(""))
    	a.setText("No Available Books publish on " + sdate);
    else
    	a.setText(s);
    //System.out.println(s);
 }
 public static void saveBooks()throws IOException{
  PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\MIQDAD\\Desktop\\New folder (3)\\My Project\\books.txt"));
  for(int i =0;i<no_bk;i++)
  out.println(books[i]);
  out.close();
   
 }
 public static void saveMembers()throws IOException{
  PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\MIQDAD\\Desktop\\New folder (3)\\My Project\\members.txt"));
  for(int i =0;i<no_mb;i++)
  out.println(mmbs[i]);
  out.close();
   
 }
 
 static void initBooks()throws IOException{
   In = new Scanner(new FileReader("books.txt"));
   String inp, id, ath, til,ed,fld;
   int n,m;
   while (In.hasNext()){
     inp = In.nextLine();
     tk = new StringTokenizer(inp,"#,\t");
     id = tk.nextToken();
     ath = tk. nextToken();
     til = tk. nextToken();
     ed = tk.nextToken();
     fld = tk.nextToken();
     LocalDate date = LocalDate.parse(tk.nextToken(),df);
     n = Integer.parseInt(tk.nextToken());
     m = Integer.parseInt(tk.nextToken());
    books[no_bk] = new Book2(id,ath,til,ed,fld,date,n,m);
    if(m != 0){
  		for(int i =0;i<m;i++) 
  			books[no_bk].setBorrower(tk.nextToken(),i);
    }
    no_bk++;
   }
   In.close();
   Book2.sort(books,no_bk);
 } 
 static void initMmbs()throws IOException{
   In = new Scanner(new FileReader("members.txt"));
   String inp, id, nm;
   int n;
   double fn = 0;
   while (In.hasNext()){
     inp = In.nextLine();
     tk = new StringTokenizer(inp,"#,\t");
     id = tk.nextToken();
     nm = tk. nextToken();
     n = Integer.parseInt(tk. nextToken());
     fn = Double.parseDouble(tk.nextToken());
    mmbs[no_mb] = new Member(id,nm,n,fn);
    no_mb++;
   }
   In.close();
 }
 public void removeMember(String i)throws IOException{
   int k = findMember(i);
   if(k != -1){
   	String id = mmbs[k].getIDNumber();
   		for(int j=0; j<no_bk;j++)
   			if(books[j].isBorrowedBy(id))
   				books[j].returnBook(mmbs[k]);
   	Member m = mmbs[no_mb-1];
   	mmbs[k] = m;
   	mmbs[no_mb-1] = null; 
   	no_mb--;
   	saveMembers();
   	JOptionPane.showMessageDialog(null,"Member removed successfully!");
   }
 }
 public int getNoMmb(){
   return no_mb;
 }
 public int getNoBk(){
   return no_bk;
 }
 public ArrayList<Book2> getBooks(){
 	ArrayList<Book2> bks = new ArrayList<Book2>();
 	for(int i=0;i<no_bk;i++)
 		bks.add(books[i]);
 	return bks;
 }
 public static void updateBook(Book2 b)throws IOException{
 	int x = findBook(b);
 	books[x].setTit(b.getTit());
	books[x].setEdition(b.getEdit());
	books[x].setField(b.getField());
 	books[x].setPublishDate(LocalDate.parse(b.getDate(),df));
 	saveBooks();
 }
 public void setInitials(){
 	no_mb = 0;
 	no_bk = 0;
 }
 /*public void setFines(Book2 b){
 	String[] brs = b.getBorrowers();
 	int n = LocalDate.now().getDayOfYear();
 	Member m; int dif;
 	for(int i=0; i< b.getNoBrd();i++){
 		
 	}
 }*/
}