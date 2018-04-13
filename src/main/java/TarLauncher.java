import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.Scanner;

public class TarLauncher {

    @Option(name = "-u", metaVar = "split", required = true, usage = "Input file to split")
    private String inputSplit;

    @Option(name = "-out", metaVar = "connect", required = true, usage = "Input file to connect")
    private String outputConnect;

 //   @Argument(required = true, metaVar = "Output", index = 1, usage = "")
 //   private String a;

    public static void main(String[] args) throws IOException {

        new TarLauncher().launch(args);

    }

    private void launch(String[] args) throws IOException {

        CmdLineParser parser = new CmdLineParser(this);

        try{

            parser.parseArgument(args);

        } catch (CmdLineException e) {

            System.err.println(e.getMessage());
            System.err.println("java -jar tar.jar -u filename.txt");
            System.err.println("java -jar tar.jar file1.txt file2.txt … -out output.txt");
            parser.printUsage(System.err);

        }

        if (!inputSplit.isEmpty() && !outputConnect.isEmpty()){

            System.err.println("String entered incorrectly");
            parser.printUsage(System.err);

        }

        if (inputSplit.matches("[^/:*?\"<>|]*.txt")) {

            Scanner reader = new Scanner(new FileReader(inputSplit));
            Tar.split(reader);

        } else{

            System.err.println("Incorrect file name");
            parser.printUsage(System.err);

        }

        if (outputConnect.matches("[^/:*?\"<>|]*.txt")) {

            FileWriter writer = new FileWriter(outputConnect);
             Tar.connect(writer, args);

        } else{

            System.err.println("Incorrect file name");
            parser.printUsage(System.err);

        }
    }
}