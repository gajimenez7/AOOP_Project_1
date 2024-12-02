package src.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public interface History {
    public static void createFile(String pathToFile) {
        try {
            File f = new File(pathToFile);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String transaction, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(transaction);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}