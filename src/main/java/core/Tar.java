package core;

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
     */
    public static void split(Scanner reader) {
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

            try (FileWriter writer = new FileWriter("src/test/resources/input1/"+ fileName.split(" ")[0])) {

                for (int i = 0; i < Integer.valueOf(fileName.split(" ")[1]); i++) {
                    if (reader.hasNext()) {
                        writer.write(reader.nextLine() + "\n");
                    } else {
                        System.out.println("Не хватает строк в главном файле");
                        throw new IllegalArgumentException();
                    }
                }

            } catch (IOException e) { System.out.println("Не возможно прочитать файл"); }

        }
    }

    /**
     * Совмещает все фалы поданные в виде(String[]) в один файл(file)
     * @param file Путь к файлу который нужно заполнить
     * @param args Файлы у которых нужно брать содержимое
     */
    public static void connect(String file, String[] args) {

        try (FileWriter writer = new FileWriter(file)) {

            for (String nameFile : args) {

                if (!new File(nameFile).exists()) {
                    System.out.println("Не существует файл");
                    throw new FileNotFoundException();
                }
                writer.write(nameFile.split("/")[nameFile.split("/").length-1] + " " + String.valueOf(getCountString(nameFile)) + "\n");

                try (Scanner input = new Scanner(new FileReader(nameFile))) {
                    while (input.hasNext()) {
                        writer.write(input.nextLine() + "\n");
                    }
                } catch (FileNotFoundException e) { System.out.println("Не существует файл"); }

            }
        } catch (IOException e) { System.out.println("Не возможно прочитать файл"); }

    }

    /**
     * Считает количество строк
     * @param file Путь к файлу
     * @return Количество строк
     */
    public static Integer getCountString(String file){
        Integer count = 0;

        try (Scanner input = new Scanner(new FileReader(file))) {
            while (input.hasNext()) {
                input.nextLine();
                count ++;
            }
        } catch (FileNotFoundException e) { System.out.println("Не существует файл"); }

        return count;
    }
}
