package myProjects.loginform;
import java.util.*;

public class TestingPass {
   String pass;
   String[] a;
   int l;
   
   public TestingPass(String s){
     pass = s;
     l = s.length();
     a = new String[l];
     for(int i=0;i<l;i++)
        a[i] = pass.charAt(i) + "";
   }
   
   boolean C(){
     for(int i=0;i<l;i++){
        int x = a[i].hashCode();
        if((32<=x && x<=47) || (58<=x && x<=64) ||(91<=x&&x<=96) || x>122){
          return true;
        }
     }
     return false;
   }
   boolean D(){
     for(int i=0;i<l;i++){
        int x = a[i].hashCode();
        if(48<=x && x<=57){
          return true;
        }
     }
     return false;
   }
   
   boolean U(){
     for(int i=0;i<l;i++){
        int x = a[i].hashCode();
        if(65<=x && x<=90){
          return true;
        }
     }
     return false;
   }
   
   boolean L(){
     for(int i=0;i<l;i++){
        int x = a[i].hashCode();
        if(97<=x && x<=122){
          return true;
        }
     }
     return false;
   }
   
   String strength(){
     if(l<6)
       return "too short";
     if(L()&&C()&&D()&&U())
       return "super strong";
     if((L()&&U()&&D()) || (L()&&U()&&C()) || (C()&&U()&&D()) ||(L()&&C()&&D()))
       return "extra strong";
     if((L()&&U())|| (L()&&D())|| (L()&&C())|| (D()&&U())|| (C()&&U())|| (D()&&C()))
       return "strong";
     return "weak";
     
   }

  public static void main(String[] args) {
   TestingPass p = new TestingPass("alphaNUmE.,+@69ric");
   String b = p.strength();
   System.out.println(p);
  }
  public String toString(){
    return String.format("\'%s\' is a %s password",pass,strength());
  }

}
