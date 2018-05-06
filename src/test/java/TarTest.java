import core.Tar;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class TarTest {

    private final static String OUTPUT1 = "src/test/resources/output/output1.txt";
    private final static String OUTPUT2 = "src/test/resources/output/output2.txt";
    private final static String OUTPUT4 = "src/test/resources/output/output4.txt";
    private final static String OUTPUT5 = "src/test/resources/output/output5.txt";
    private final static String OUTPUT6 = "src/test/resources/output/output6.txt";
    private final static String INPUT1 = "src/test/resources/input/input1.txt";
    private final static String INPUT2 = "src/test/resources/input/input2.txt";
    private final static String INPUT3 = "src/test/resources/input/input3.txt";
    private final static String INPUT4 = "src/test/resources/input/input4.txt";
    private final static String INPUT5 = "src/test/resources/input/input5.txt";
    private final static String INPUT6 = "src/test/resources/input/input6.txt";
    private final static String WAY_INPUT = "src/test/resources/input/";
    private final static String WAY_INPUT1 = "src/test/resources/input1/";

    /**
     * Метод превращает содержимое всех файлов которые подаются в строку
     * @param str Пути всех файлов
     * @return Строку собранную из содержимого файлов
     * @throws IOException Если будет не возможно прочитать файл
     */
    private static String connectToString (String[] str) {
        StringBuilder result = new StringBuilder();

        for (String aStr : str) {

           if ( !new File(aStr).exists()) {
                System.out.println("Невозмодный формат");
                throw new IllegalArgumentException();
            }

            try (Scanner fileTemp1 = new Scanner(new FileReader(aStr))) {
                while (fileTemp1.hasNext()) {
                    result.append(fileTemp1.nextLine());
                    result.append("\n");
                }
            } catch (IOException e) { System.out.println("Не возможно прочитать файл"); }

        }
        return result.toString();
    }

    @Test
    void connect() throws IOException {

        Tar.connect(OUTPUT1, new String[]{INPUT1, INPUT2, INPUT3, INPUT4, INPUT5});
        assertEquals(connectToString(new String[]{OUTPUT1}), connectToString(new String[]{INPUT1, INPUT2, INPUT3, INPUT4, INPUT5}));

        Tar.connect(OUTPUT1, new String[]{INPUT3, INPUT5});
        assertEquals(connectToString(new String[]{OUTPUT1}), connectToString(new String[]{INPUT3, INPUT5}));

        Tar.connect(OUTPUT1, new String[]{INPUT3, INPUT5, INPUT6});
        assertEquals(connectToString(new String[]{OUTPUT1}), connectToString(new String[]{INPUT3, INPUT5, INPUT6}));

        Tar.connect(OUTPUT1, new String[]{INPUT3, INPUT5, INPUT1, INPUT6});
        assertEquals(connectToString(new String[]{OUTPUT1}), connectToString(new String[]{INPUT3, INPUT5, INPUT1, INPUT6}));
    }

    @Test
    void split() throws IOException {

        Tar.split(new Scanner(new FileReader(OUTPUT2)));
        for (String file: new String[]{"input6.txt", "input1.txt", "input2.txt", "input3.txt"}){
                assertEquals(connectToString(new String[]{WAY_INPUT + file}), connectToString(new String[]{WAY_INPUT1 + file}));
        }

        Tar.split(new Scanner(new FileReader(OUTPUT4)));
        for (String file: new String[]{"input1.txt", "input4.txt", "input5.txt"}){
                assertEquals(connectToString(new String[]{WAY_INPUT + file}), connectToString(new String[]{WAY_INPUT1 + file}));
        }

        try {
            Tar.split(new Scanner(new FileReader(OUTPUT6)));
            for (String file: new String[]{"input6.txt", "input1.txt", "input2.txt", "input3.txt"}){
                    assertEquals(connectToString(new String[]{WAY_INPUT + file}), connectToString(new String[]{WAY_INPUT1 + file}));
            }
        } catch(IllegalArgumentException e){}


        Tar.split(new Scanner(new FileReader(OUTPUT5)));
        for (String file: new String[]{"input6.txt", "input1.txt", "input2.txt", "input3.txt"}){
                assertEquals(connectToString(new String[]{WAY_INPUT + file}), connectToString(new String[]{WAY_INPUT1 + file}));
        }

    }
}
