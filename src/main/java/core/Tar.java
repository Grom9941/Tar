package core;

import org.junit.Assert;

import java.io.*;
import java.util.*;

public class Tar {

    private final static String format = "[^/:*?\"<>|]*.txt";

    /**
     * Файл в формате:
     * nameFile.txt countString
     * input2.txt 3
     * text..
     * ......
     * input3.txt 2
     * text..
     * input4.txt 0
     * text...
     *
     * Записывает в нужные файлы
     * @param reader Начальный файл который нужно разобрать
     * @throws IOException Если не возможно будет вписать в файл
     */
    public static void split(Scanner reader) throws IOException {
        Integer number = 0;
        String fileName;

        while (reader.hasNext()) {
            fileName = reader.nextLine();
            if (fileName.split(" ").length != 2) {
                System.out.println("Не хватает строк в главном файле");
                throw new IllegalArgumentException();
            }

            if (!fileName.split(" ")[0].matches(format)) {
                System.out.println("Невозмодный формат");
                throw new IllegalArgumentException();
            }

            FileWriter writer = new FileWriter("src/test/resources/input1/"+ fileName.split(" ")[0]);
            writer.write(fileName+ "\n");

            for (int i = 0; i < Integer.valueOf(fileName.split(" ")[1]); i++){

                if (reader.hasNext()) {
                    writer.write(reader.nextLine() + "\n");
                } else {
                    System.out.println("Не хватает строк в главном файле");
                    throw new IllegalArgumentException();

                }

            }

            number++;
            writer.close();

        }
    }

    /**
     * Совмещает все фалы поданные в виде(String[]) в один файл(file)
     * @param file Путь к файлу который нужно заполнить
     * @param args Файлы у которых нужно брать содержимое
     * @throws IOException Если не возможно будет вписать в файл или прочитать файл
     */
    public static void connect(String file, String[] args) throws IOException {

        if (!new File(file).exists()) {
            System.out.println("Несуществует файл");
            throw new IllegalArgumentException();
        }
        FileWriter writer = new FileWriter(file);

        for (String nameFile : args){
            if (!new File(nameFile).exists()) {
                System.out.println("Несуществует файл");
                throw new IllegalArgumentException();
            }

            Scanner input = new Scanner(new FileReader(nameFile));

            while(input.hasNext()){
                writer.write(input.nextLine() + "\n");
            }

            input.close();
        }

        writer.close();
    }

}
