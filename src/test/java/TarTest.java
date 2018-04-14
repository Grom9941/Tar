import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class TarTest {

    private static String connect2(String file) throws IOException {

        Scanner fileTemp = new Scanner(new FileReader(file));
        StringBuilder result = new StringBuilder("");

        while (fileTemp.hasNext()) {

            result.append(fileTemp.nextLine());
            result.append("\n");

        }

        fileTemp.close();
        return result.toString();
    }

    private static String connect1 (String[] str) throws IOException {

        StringBuilder result = new StringBuilder();

        for (String aStr : str) {

            Scanner fileTemp1 = new Scanner(new FileReader(aStr));

            while (fileTemp1.hasNext()) {

                result.append(fileTemp1.nextLine());
                result.append("\n");

            }

            fileTemp1.close();
        }

        return result.toString();
    }

    @Test
    void connect() throws IOException {

        Tar.connect("output/output1.txt", new String[]{"input/input1.txt", "input/input2.txt", "input/input3.txt", "input/input4.txt", "input/input5.txt"});
        assertEquals(connect2("output/output1.txt"), connect1(new String[]{"input/input1.txt", "input/input2.txt", "input/input3.txt", "input/input4.txt", "input/input5.txt"}));

        Tar.connect("output/output1.txt", new String[]{"input/input3.txt", "input/input5.txt"});
        assertEquals(connect2("output/output1.txt"), connect1(new String[]{"input/input3.txt", "input/input5.txt"}));

        Tar.connect("output/output1.txt", new String[]{"input/input3.txt", "input/input5.txt", "input/input6.txt"});
        assertEquals(connect2("output/output1.txt"), connect1(new String[]{"input/input3.txt", "input/input5.txt", "input/input6.txt"}));
    }

    @Test
    void split() throws IOException {

        Tar.split(new Scanner(new FileReader("output/output2.txt")));

        for (String file: new String[]{"input6.txt", "input1.txt", "input2.txt", "input3.txt"}){
            assertEquals(connect2("input/" + file), connect2("input1/" + file));
        }

        Tar.split(new Scanner(new FileReader("output/output4.txt")));
        for (String file: new String[]{"input1.txt", "input4.txt", "input5.txt"}){
            assertEquals(connect2("input/" + file), connect2("input1/" + file));
        }
    }

}
