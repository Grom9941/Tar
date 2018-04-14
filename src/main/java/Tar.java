import java.io.*;
import java.util.*;

public class Tar {

    public static void split(Scanner reader) throws IOException {

        Integer number = 0;
        List<String> fileName = new ArrayList<>();
        List<Integer> fileNumber = new ArrayList<>();
        String fileString = reader.nextLine();

        while (!fileString.equals("")){

            fileName.add(fileString.split(" ")[0]);
            fileNumber.add(Integer.valueOf(fileString.split(" ")[1]));
            fileString = reader.nextLine();

        }

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

    public static void connect(String file, String[] args) throws IOException {

        FileWriter writer = new FileWriter(file);
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
