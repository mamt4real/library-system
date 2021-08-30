/**
 * @(#)StudyDate.java
 *
 *
 * @author 
 * @version 1.00 2019/12/21
 */
import java.time.LocalDate;
import java.text.DateFormat;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import myProjects.library.*;

public class StudyDate {

    public StudyDate() {
    }
    public static void main(String[] args){
    	/*DateTimeFormatter df = DateTimeFormatter.ofPattern("DD-MM-YYYY");
    	String day = "2013-12-20";
    	LocalDate date = LocalDate.parse(day);
    	System.out.println(date.getDayOfYear());*/
    	
    	AccountStatus st = AccountStatus.ACTIVE;
    	System.out.println(st);
    }
    
}