package src.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * History interface for Log and UserTransaction
 */
public interface History {
    String parseTransaction(); // Unique transaction parsing
    
    /**
     * Create file
     * @param pathToFile
     */
    static void createFile(String pathToFile) {
        try {
            File file = new File(pathToFile);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    /**
     * Write to file
     * @param transaction
     * @param fileName
     */
    static void writeToFile(String data, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}