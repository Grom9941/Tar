import java.io.*;
import java.util.*;

public class Tar {
    public static void split(Scanner reader) throws IOException {
        List fileName = new ArrayList();
        List<Integer> fileNumber = new ArrayList<Integer>();
        String fileString = reader.nextLine();
        while (!fileString.equals("")){
            fileName.add(fileString.split(" ")[0]);
            fileNumber.add(Integer.valueOf(fileString.split(" ")[1]));
            fileString = reader.nextLine();
        }
        Integer number = 0;
        while (reader.hasNext()){
            FileWriter writer = new FileWriter("input1/"+ fileName.get(number));
            for (int i = 0; i < fileNumber.get(number); i++){
                writer.write(reader.nextLine());
                writer.write("\n");
            }
            number++;
            writer.close();
        }
    }

    public static void connect(FileWriter writer, String[] args) throws IOException {
        for (String nameFile : args){
            Scanner input = new Scanner(new FileReader(nameFile));
            while(input.hasNext()){
                writer.write(input.nextLine());
                writer.write("\n");
            }
            input.close();
        }
        writer.close();
    }

}
