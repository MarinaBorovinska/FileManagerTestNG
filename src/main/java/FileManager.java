import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    boolean interactive;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    FileManager(Boolean mode) {
        interactive = mode;
    }

    public void createFile(String filePath){

        if (interactive) {
                try {
                    filePath = pathInput();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        List<String> lines = Arrays.asList("first", "second", "third");
        Path file = Paths.get(filePath);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File " + filePath + " is created");
    }

    public void deleteFile(String filePath) throws IOException{
  /*    Scanner scanner = new Scanner(System.in);
        System.out.println("Enter File name: ");
        String inputPath = scanner.nextLine();
  */
        if (interactive) {
            try {
                filePath = pathInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean result;

        File file = new File(filePath);

        result = file.delete();

            if(result){
                System.out.println("File has been deleted");
            }else{
                System.out.println("Delete operation is failed");
            }
    }

    public void renameFile(String renameFilePath, String pathNew) throws IOException{
        boolean result;
        File oldFile = new File(renameFilePath);
        File newFile = new File(pathNew);
        result = oldFile.renameTo(newFile);
        if(result){
            System.out.println("Rename succesful");
        }else{
            System.out.println("Rename failed");
        }
    }

    public void findWord(){

        System.out.print("Enter file name: ");
        Scanner scannerFile = new Scanner(System.in);
        String inputPath = scannerFile.nextLine();

        System.out.print("Enter word: ");
        Scanner scannerWord = new Scanner(System.in);
        String inputWord = scannerWord.nextLine();

        File file = new File(inputPath + ".txt");

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scanner != null) {
            while (scanner.hasNextLine()) {
                String nextWord = scanner.next();
                if (nextWord.equalsIgnoreCase(inputWord)) {
                    System.out.println("Found word is " + nextWord);
                }
                else System.out.println("Word is not found");
                break;
            }
        }
        //      scannerFile.close();
        //       scannerWord.close();
    }

    public void replaceWord(){

        System.out.print("Enter file name: ");
        Scanner scannerFile = new Scanner(System.in);
        String inputPath = scannerFile.nextLine();

        System.out.print("Enter word: ");
        Scanner scannerWord = new Scanner(System.in);
        String inputWord = scannerWord.nextLine();

        System.out.print("Enter new word: ");
        Scanner scannerNewWord = new Scanner(System.in);
        String inputNewWord = scannerNewWord.nextLine();

        try {
            File file = new File(inputPath + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            while ((line = reader.readLine()) != null) {
                oldtext += line + "\r\n";
            }
            reader.close();

            String newWord = oldtext.replaceAll(inputWord, "" + inputNewWord);

            FileWriter writer = new FileWriter(inputPath + ".txt");
            writer.write(newWord);
            System.out.println("Word replaced");
            writer.close();

            //          scannerFile.close();
            //          scannerWord.close();
            //          scannerNewWord.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public String pathInput() throws IOException {
        String os;
        String path;

        os = System.getProperty("os.name");
        printLogMessage("Your operation system is " + os);

        if (os.contains("Windows")) {
            printLogMessage("Please, input a path and file name in format (example): C:\\Users\\user\\test.txt");
            path = reader.readLine();
        } else if (os.contains("Mac")) {
            printLogMessage("Please, input a path and file name in format (example) /Users/user/projects/test.txt");
            path = reader.readLine();
        } else if (os.contains("Linux")) {
            printLogMessage("Please, input a path and file name in format (example) /home/user/projects/test.txt");
            path = reader.readLine();
        } else {
            printLogMessage("Please, input a path and file name");
            path = reader.readLine();
        }

        return path;
    }

    private void printLogMessage(String message) {
        if (interactive) {
            System.out.println(message);
        }
    }
}