import java.io.*;

public class FileHandlingUtility {

    public static void writeToFile(String fileName, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
            System.out.println( "File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    public static void readFromFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println("File Content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    public static void modifyFile(String fileName, String newContent) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.newLine();
            writer.write(newContent);
            writer.close();
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file");
        }
    }

    public static void main(String[] args) {

        String fileName = "sample.txt";

        writeToFile(fileName,
                "DHAANISH AHMED COLLEGE OF ENGINEERING OF CODETECH INTERNSHIP\nThis file demonstrates basic Java file handling READ FIRST CONTENT");
       
         readFromFile(fileName);

        modifyFile(fileName,
                "WELCOME TO DHAANISH AHMED COLLEGE OF ENGINEERING OF CODETECH INTERNSHIP\nThis file demonstrates basic Java file handling.\nAdditional content added successfully.");

 
        readFromFile(fileName);
    }
}