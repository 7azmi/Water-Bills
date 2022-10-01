/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package WaterBills;

    public class User {
       static String userName[]={"منظمة أجيال بلا قات","السكن","الشقة الخلفية"};
       //
       
       static int lastUnit, newUnit, units, value, average1, average2, average3,averageValue,tax;
       static double fValue;
       
        static void count() {           
            units=newUnit-lastUnit;
            averageValue=((average1+average2+average3)/3);
            value=(units*averageValue);
            //The '+1' is me//
            tax=(3000/(userName.length+1));
            fValue=(value+tax);
            System.out.println("Total: "+ fValue);
        }
        
    }

