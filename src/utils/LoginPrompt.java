package src.utils;

import java.util.Scanner;

public class LoginPrompt {
    public static boolean promptPassword(Customer customer) {
        Scanner scnr = new Scanner(System.in);
        String input = "";
        String password = formatNumber(customer.getPhoneNum());
        System.out.print("Please enter your password: ");
        input = scnr.nextLine();

        return validPassword(input, password);
    }

    private static boolean validPassword(String password, String number) {
        if (password.equals(number))
            return true;
        return false;
    }

    private static String formatNumber(String number) {
        String s = number;
        s = s.replaceAll("\\(", "");
        s = s.replaceAll("\\)", "");
        s = s.replaceAll(" ", "");
        s = s.replaceAll("-", "");
        return s;
    }
}