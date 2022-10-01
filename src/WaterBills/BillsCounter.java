
package WaterBills;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class BillsCounter{
    
    private static void setAverage() {
        Scanner in = new Scanner(System.in);  
     System.out.println("Enter Unit Value for this Month please: ");
     while (true)
            try {
     System.out.println("Enter Unit Value for First 10 Days: ");
           User.average1=Integer.parseInt(in.nextLine());
     System.out.println("Enter Unit Value for Middle 10 Days: ");      
           User.average2=Integer.parseInt(in.nextLine());
     System.out.println("Enter Unit Value for Last 10 Days: ");      
           User.average3=Integer.parseInt(in.nextLine());
           break;
                   } catch (NumberFormatException e) {
                System.out.println("Enter Numbers Only: ");
            }
    }
    
    public static void setName(int i){
        System.out.println(User.userName[i]);
        //return User.userName[i];
 }
    
    public static void setUnitNumber() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Previous Number of Unit please: ");
     while (true)
            try {
           User.lastUnit=Integer.parseInt(in.nextLine());
           break;
                   } catch (NumberFormatException e) {
                System.out.println("Enter Numbers Only: ");
            }
     
     System.out.println("Enter the New Number of Unit please: ");
     while (true)
            try {
           User.newUnit=Integer.parseInt(in.nextLine());
           if (User.lastUnit > User.newUnit) {
               System.out.println("Please check your inputs");
               continue;
               
           }
           break;
                   } catch (NumberFormatException e) {
                System.out.println("Enter Numbers Only: ");
            }
    }
     
    public static void main(String[] args) throws IOException, FileNotFoundException, DocumentException {
      Scanner in = new Scanner(System.in);
      String temp;
      setAverage();
      setName(0);
      setUnitNumber();
      User.count();        
      WaterBills.PDFWriter.genaratePDF();
        

    System.out.println("Enter any for close: ");
    temp=in.nextLine();   
            }

   

   
}
