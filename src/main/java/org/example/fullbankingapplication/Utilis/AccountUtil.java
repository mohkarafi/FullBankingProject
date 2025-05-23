package org.example.fullbankingapplication.Utilis;

import java.time.Year;

public class AccountUtil {

    public static final String ACCOUNT_EXISTS_CODE = "001";
    public static final String ACCOUNT_EXISTS_MESSAGE = "User Already has an account";
    public static final String ACCOUNT_CREATION_CODE = "002";
    public static final String ACCOUNT_CREATION_MESSAGE = "User Saved successfully";
    public static final String ACCOUNT_NOT_EXISTS_CODE = "003";
    public static final String ACCOUNT_NOT_EXISTS_MESSAGE = "Account with this provided Number Not Exist";
    public static final String ACCOUNT_FOUND_CODE = "004";
    public static final String ACCOUNT_FOUND_MESSAGE = "Succes Account Number Found";
    public static final String BALANCE_ADDED_CODE = "005";
    public static final String BALANCE_ADDED__MESSAGE = "Amount added successfully";
    public static final String BALANCE_INSUFISSANT_CODE = "005";
    public static final String BALANCE_INSUFISSANT_MESSAGE = "Insufissant  Balance ";
    public static final String TRANSFER_OPERATION_CODE = "006";
    public static final String TRANSFEROPERATION_MESSAGE = "he transfer operation has been completed successfully.";

    public static String generateAccountNumber() {
        int year = Year.now().getValue();
        int min = 10000;
        int max = 99999;
        int random = (int) (Math.random() * (max - min + 1)) + min;
        String Year = String.valueOf(year); // 2025
        String randomNumber = String.valueOf(random); // 14678
        return Year + randomNumber;

        /*sans to string le type de return ici si un objet de type StringBuilder
        donc on utilise toString pour converti l'objet StringBuilder To String */
        /* result attend = 202514678 */
    }
}
