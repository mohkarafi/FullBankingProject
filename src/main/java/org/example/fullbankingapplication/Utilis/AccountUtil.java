package org.example.fullbankingapplication.Utilis;

import java.time.Year;

public class AccountUtil {

     public static final String ACCOUNT_EXISTS_CODE = "001";
     public static final String ACCOUNT_EXISTS_MESSAGE = "User Already has an account";
     public static final String ACCOUNT_CREATION_CODE = "002";
     public static final String ACCOUNT_CREATION_MESSAGE = "User Saved successfully" ;

    public static String  generateAccountNumber(){
      int year = Year.now().getValue();
      int min = 10000;
      int max = 99999;
      int random = (int)(Math.random() * (max - min + 1)) + min ;
      String Year = String.valueOf(year); // 2025
      String randomNumber = String.valueOf(random); // 14678
      StringBuilder accountNumber = new StringBuilder();
      return accountNumber.append(Year).append(randomNumber).toString(); /*  sans to string le type de return ici si un objet de type StringBuilder donc on utilise toString pour converti l'objet StringBuilder To String */

     /* result attend = 202514678 */
    }
}
