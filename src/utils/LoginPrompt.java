package src.utils;

import java.util.Scanner;

public class LoginPrompt {
    
    /** 
     * @param customer
     * @return boolean
     */
    public static boolean promptPassword(Customer customer) {
        Scanner scnr = new Scanner(System.in);
        String input = "";
        String password = formatNumber(customer.getPhoneNum());
        System.out.print("Please enter your password: ");
        input = scnr.nextLine();

        return validPassword(input, password);
    }

    /**
     * Validate input with password
     * @param password
     * @param number
     * @return
     */
    private static boolean validPassword(String password, String number) {
        if (password.equals(number))
            return true;
        return false;
    }

    /**
     * Format phone number as password
     * @param number
     * @return
     */
    private static String formatNumber(String number) {
        String s = number;
        s = s.replaceAll("\\(", "");
        s = s.replaceAll("\\)", "");
        s = s.replaceAll(" ", "");
        s = s.replaceAll("-", "");
        return s;
    }
}