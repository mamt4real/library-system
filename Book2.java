package myProjects.library;
import java. util.*; import java.util.*; import javax.swing.*; import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Book2 extends Book implements Comparable{
	static int x = -1;
	private int noCopiesAv,noCopiesBrd;
	private String edition, field;
	private String[] borwrs;
	private LocalDate publishDate;
	private final double finePerDay = 50.0;
	private final int daysAllowed = 5;
	DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MMM/yyyy");
	
	public Book2(String bk, String ath, String ttl,String ed,String fld,LocalDate dt,int n,int n1){
	  super(bk,ath,ttl);
	  edition = ed;
	  field = fld;
	  publishDate = (dt == null)? LocalDate.now():dt;
	  noCopiesAv = n;
	  noCopiesBrd = n1;
	  borwrs = new String[n + n1];
	}
	public Book2(String bk, String ath, String ttl,String ed,String fld,LocalDate dt,int n){
	  this(bk, ath, ttl, ed, fld,dt, n, 0);
	}
	public Book2(String bk, String ath, String ttl){
	  this(bk,ath,ttl,"","",LocalDate.now(),1,0);
	}
	public void setNoBrd(int n){
	  noCopiesBrd = n;
	}
	public String getField(){
		return field;
	}
	public void setCopies(int n){
	  noCopiesAv = n;
	  String temp[] = new String[n];
	  System.arraycopy(borwrs, 0,temp,0,borwrs.length);
	  borwrs = temp;
	}
	public void setField(String s){
	  field = s;
	}
	public void setEdition(String s){
	  edition = s;
	}
	public String getEdit(){
		return edition;
	}
	public String getPubDate(){
		return publishDate.getMonth() + ", " + publishDate.getYear();
	}
	public String getDate(){
		return publishDate.format(df);
	}
	public void setPublishDate(LocalDate dt){
		publishDate = dt;
	}
	public String getCopies(){
		return (noCopiesAv + noCopiesBrd) + "";
	}
	public boolean isAvailable(){
	  return noCopiesAv > 0;
	}
	public boolean isBorrowedBy(String mmbrID){
	  for(int i =0; i<noCopiesBrd;i++)
	  	if(borwrs[i].startsWith(mmbrID)){
	  		x = i;
	  		return true;
	  	}
	  return false ;
	}
	public int getBordNo(){
		return noCopiesBrd;
	}
	public int findBrwr(String mmbrID){
		return isBorrowedBy(mmbrID)? x:x;
	}
	public void borrowBook(Member m){
		int n = LocalDate.now().getDayOfYear();
		String date = LocalDate.now().plusDays(5).format(df);
		int choice = 0; double amt = 0.0;
		if(!m.isActive()){
			JOptionPane.showMessageDialog(null,"Your account is suspended, you have a fine of $" + m.getFine() + " on you!!");
			choice = JOptionPane.showConfirmDialog(null,"Do you wish to pay now?");
				if(choice == JOptionPane.YES_OPTION){
					amt = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter amount"));
					m.payFines(amt);
				}
					return;
		}
		if(m.getNumberOfBooks() == 5){
			JOptionPane.showMessageDialog(null,"You have reach you borrowing capacity, Return some books first");
			return;
		}
		if(isBorrowedBy(m.getIDNumber())){
			JOptionPane.showMessageDialog(null,"You have a copy of this book with you");
			return;
		}
		if(isAvailable()){
			noCopiesAv--;
			borwrs[noCopiesBrd] = m.getIDNumber() + "-" + n;
			noCopiesBrd++;
			m.addBook(1);
			JOptionPane.showMessageDialog(null,super.getTit() + " is borrowed to " + m.getName() + "Successfully\nIt should be returned on or before: " + date );
			return;
		}
		else
		JOptionPane.showMessageDialog(null,"Sorry, there is no available copies");
	}
	public String[] getBorrowers(){
		return borwrs;
	}
	public void returnBook(Member m){
		int n = LocalDate.now().getDayOfYear(), choice = 0;
		double fines = 0.0, amt = 0.0; 
		if(isBorrowedBy(m.getIDNumber())){
			n = n - Integer.parseInt(borwrs[x].substring(borwrs[x].indexOf('-') + 1));
			fines = (n - daysAllowed) * finePerDay;
			if(n <= daysAllowed){
				JOptionPane.showMessageDialog(null,super.getTit() + " is returned by\n\t\t" + m.getName() + " on " + LocalDate.now().format(df));
			}
			else{
				m.addFine(fines);
				JOptionPane.showMessageDialog(null,"You are " + (n- daysAllowed) + " days ahead of the due date, you will be charged $" + fines + " as penalty!");
				choice = JOptionPane.showConfirmDialog(null,"Do you wish to pay now?");
				if(choice == JOptionPane.YES_OPTION){
					amt = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter amount"));
					m.payFines(amt);
				}
			}
			borwrs[x] = borwrs[noCopiesBrd-1];
			borwrs[noCopiesBrd-1] = null;
			noCopiesBrd--;
			noCopiesAv++;
			m.removeBook(1);
		}
		else
			JOptionPane.showMessageDialog(null,"Sorry, this book is not borrowed by you");
	}
	 public void setBorrower(String B,int i){
	 	if( i< noCopiesBrd)
	   		borwrs[i] = B;
	 }
	public void setBorrowers(String[] br){
	  System.arraycopy(br,0,borwrs,0,br.length);
	}
	public String toString(){
	  String s1 = super.toString();
	  s1 = s1 + "#" + edition + "#" + field + "#" + publishDate.format(df) + "#" + noCopiesAv + "#" + noCopiesBrd;
	  if(noCopiesBrd !=0)
	  for(String s : borwrs)
	  	if(!(s == null))
	  		s1 = s1 + "#" + s;
	  return s1;
	}
	public String myPrint(){
	  String stat = (noCopiesAv==0)? "No available Copies" : noCopiesAv + " copies available";
	  String date = publishDate.getMonth() + ", " + publishDate.getYear();
	  if(noCopiesAv == 1)
	  	stat = "1 copy available";
	  String str = String.format("Book ID: %s%nTitle: %s %s%nField: %s%nAuthor: %s%nDate Published: %s%nStatus: %s%n%n",super.getBookNumber(),super.getTit(),edition,field, super.getAuth(),date,stat);
	  return str;
	}
	public int compareTo(Object o){
		Book b = (Book)o;
		String s = super.getBookNumber();
		String s2 = b.getBookNumber();
		return s.compareTo(s2);
	}
	public boolean equals(Object o){
		Book b = (Book)o;
		return (this.compareTo(b)==0);
	}
	public static void sort(Book2[] a, int h){
		boolean dn;
		Book2 t;
		int p = 1;
		do{
		  dn = true;
		  for(int i =1;i<= h-p; i++){
			if(a[i].compareTo(a[i-1])<0){
			  t = a[i-1];
			  a[i-1] = a[i];
			  a[i] = t;
			  dn = false;
			}
		  }
		  p++;
		}while(!dn);
	}
}
