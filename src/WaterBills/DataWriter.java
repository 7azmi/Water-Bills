
package WaterBills;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


public class DataWriter extends User {
    static LocalDate toDate = LocalDate.now();
    
    public static void dataWriter() {
    //Create txt file for bills     
        try {
      File bills = new File(("C:\\Users\\Ahmed\\Desktop\\Bills\\Bills "+toDate+".txt"));
      if (bills.createNewFile()) {
        System.out.println("File created: " + bills.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    //modify bills' info
     try {
      FileWriter myWriter = new FileWriter(("C:\\Users\\Ahmed\\Desktop\\Bills\\Bills "+toDate+".txt"));
      myWriter.write(toDate+"\n");
      myWriter.write("اسم المستهلك: "+User.userName +"\n");
       myWriter.write("القراءة السابقة: "+User.lastUnit+"\n");
        myWriter.write("القراءة الحالية: "+User.newUnit+"\n");
         myWriter.write("الفارق: "+User.units+"\n");
         myWriter.write("القيمة: "+User.value+"\n");
         myWriter.write("الضريبة: "+User.tax+"\n");
         myWriter.write("الكل إجمالا: "+User.fValue+"\n");
         myWriter.write("------------------------------------------------------");
 
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
        
    }    
}
}
