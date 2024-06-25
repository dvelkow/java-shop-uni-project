package utils;

import entities.Receipt;
import java.io.*;

public class ReceiptUtils {

    // Метод за записване на касова бележка във файл
    public static void writeReceiptToFile(Receipt receipt, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(receipt.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Възникна грешка при записване на касовата бележка във файл: " + e.getMessage());
        }
    }

    // Метод за четене на касова бележка от файл
    public static String readReceiptFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Възникна грешка при четене на касовата бележка от файл: " + e.getMessage());
        }
        return content.toString();
    }
}
