package src.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

interface History {
    public static void createFile(String fileName) {
        try {
            File f = new File(fileName);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String transaction) {
        try {
            FileWriter fw = new FileWriter("log.txt", true);
            fw.write(transaction);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}