package core;

import org.junit.Assert;

import java.io.*;
import java.util.*;

public class Tar {

    /**
     * Файл в формате:
     * nameFile.txt countString
     * input2.txt 3
     * ......
     * ......
     * input3.txt 2
     *
     * text...
     *
     * Записывает в нужные файлы
     * @param reader Начальный файл который нужно разобрать
     * @throws IOException Если не возможно будет вписать в файл
     */
    public static void split(Scanner reader) throws IOException {

        Integer number = 0;
        List<String> fileName = new LinkedList<>();
        String fileString = reader.nextLine();

        while (!fileString.equals("")){

            fileName.add (fileString);
            if (reader.hasNext()) {
                fileString = reader.nextLine();
            } else {
                System.out.println("Неверный формат файла");
                Assert.assertEquals(1,0);
            }

        }

        while (reader.hasNext()){

            FileWriter writer = new FileWriter("src/test/resources/input1/"+ fileName.get(number).split(" ")[0]);

            for (int i = 0; i < Integer.valueOf(fileName.get(number).split(" ")[1]); i++){

                writer.write(reader.nextLine());
                writer.write("\n");

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
