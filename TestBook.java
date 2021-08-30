package LabProject;
import java.util.*;
import java. io. *;
import LabProject.Library;
public class TestBook {

  public static void main(String[] args)throws IOException {
     Library l = new Library();
     Book b[] = l.books;
     Member m[] = l.mmbs;
     Book2 b2[] = new Book2[l.no_bk];
     String[] brw = new String[5];
     Random r = new Random();
     for(int i=0;i<b2.length;i++)
       b2[i] = new Book2(b[i].getBookNumber(),b[i].getAuth(),b[i].getTit());
     for(int i=0;i<5;i++){
       brw[i] = m[r.nextInt(l.no_mb)].getIDNumber();
     }
     /*for(int i=0;i<b2.length;i++){
       b2[i].setNoAv(r.nextInt(10) +1);
       //System.out.println(b2[i].myPrint());
     }*/
     /*for(int i=0;i<b2.length;i++){
       for(int j=0;j<3;j++)
         b2[i].borrowBook(brw[r.nextInt(5)]);
       System.out.println(b2[i].myPrint());
     }*/
     
     b2[3].setNoAv(5);
     b2[3].setField("Science");
     b2[3].setEdition("3rd edition");
     for(int i=0;i<5;i++)
     b2[3].borrowBook(brw[i]);
     System.out.println(b2[3].myPrint());
     System.out.println(b2[3]);
     System.out.println(b2[3].findBrwr(brw[1]));
     b2[3].returnBook(brw[1]);
     System.out.println(b2[3]);  
  }
  /*static void initBooks()throws IOException{
   In = new Scanner(new File("/sdcard/LabProject/books.txt"));
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
     n = Integer.parseInt(tk.nextToken());
     m = Integer.parseInt(tk.nextToken());
    books[no_bk] = new Book2(id,ath,til,ed,fld,n,m);
    if(m != 0){
    books[no_bk].setBorrowed(true);
   for(int i =0;i<m;i++) books[no_bk].setBorrower(tk.nextToken());
    }
    no_bk++;
   }
   In.close();
 }*/ 

}
