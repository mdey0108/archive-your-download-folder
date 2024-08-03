import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MoveFileFromDownloadToArchive {

    public static void main(String[] args) {
        // Replace these paths with the actual source and destination paths

        // String sourcePath = "C:\\Users\\INDIAN\\Downloads\\";
        String sourcePath = "C:\\Users\\mdey0\\Downloads\\";

        // String destinationPath_old = "C:\\Users\\INDIAN\\DownloadsArchive\\";

        // String destinationPath_old = "M:\\DownloadsArchive\\";

        String destinationPath_old = "C:\\Users\\mdey0\\DownloadsArchive\\";
        int move_flag = 1;
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strDate = dateFormat.format(date);

        String destinationPath = destinationPath_old + strDate;

        System.out.println("printing timestamp " + strDate);
        try {
            moveFile(sourcePath, destinationPath,move_flag);
            
            
        } catch (IOException e) {
            System.err.println("Error moving file from " + e.getMessage());
            
        }
       

    }

    public static void moveFile(String sourcePath, String destinationPath, int move_flag) throws IOException {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);
        int mf=move_flag;
        // System.out.println("beformoving");

        // StandardCopyOption.REPLACE_EXISTING is used to overwrite the destination file
        // if it already exists
        try {
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved successfully to " + destinationPath);
            mf= 1;
            if (mf == 1) {
                createTextFile(destinationPath, sourcePath);
            }
        } catch (Exception e) {
            System.err.println("Error moving file from " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static void createTextFile(String destinationPath, String sourcePath) {
        // Specify the file path

        String folderPath = sourcePath;

        // Create a File object representing the folder
        File folder = new File(folderPath);

        // Check if the folder doesn't exist, then create it
        if (!folder.exists()) {
            boolean success = folder.mkdir(); // Creates the folder
            if (success) {
                System.out.println("Folder created successfully.");
            } else {
                System.err.println("Failed to create folder.");
            }
        } else {
            System.out.println("Folder already exists.");
        }

        String filePath = sourcePath + "\\moved.txt";

        // Content to be written into the file
        String content = " this file is created after moving downloaded contents to archive location "
                + destinationPath;

        try {
            // Create a FileWriter object with the specified file path
            FileWriter fileWriter = new FileWriter(filePath);

            // Wrap the FileWriter with BufferedWriter for efficient writing
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the content into the file
            bufferedWriter.write(content);

            // Close the BufferedWriter to ensure data is flushed and the file is saved
            bufferedWriter.close();
            System.out.println("Text file created successfully.");

        } catch (IOException e) {
            // Handle exceptions if any error occurs
            e.printStackTrace();
        }
    }

}