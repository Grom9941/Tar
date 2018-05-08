package core;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class TarLauncher {

    @Option(name = "-u", metaVar = "split", usage = "Input file to split")
    private boolean inputSplit;

    @Option(name = "-out", metaVar = "connect", usage = "Input file to connect" , forbids = "-u")
    private boolean outputConnect;

    @Argument(required = true, metaVar = "Output", usage = "Name files")
    private String[] files;

    public static void main(String[] args) {

        try {
            new TarLauncher().launch(args);
        }catch (IOException e){
            System.out.println("Ошибка в формате или пути к файлу");
        }

    }

    private void launch(String[] args) throws IOException {
        final String format = "[^/:*?\"<>|]*.txt";
        CmdLineParser parser = new CmdLineParser(this);

        try{

            parser.parseArgument(args);

        } catch (CmdLineException e) {

            System.err.println(e.getMessage());
            System.err.println("java -jar tar.jar -u filename.txt");
            System.err.println("java -jar tar.jar file1.txt file2.txt … -out output.txt");
            parser.printUsage(System.err);

        }

        if (!inputSplit && !outputConnect){

            System.err.println("String entered incorrectly");
            parser.printUsage(System.err);
            return;

        }

        if (inputSplit && files[0].split("/")[files[0].split("/").length-1].matches(format)) {

            Scanner reader = new Scanner(new FileReader(files[0]));
            Tar.split(reader);
            return;

        }

        if (outputConnect) {

                for (String nameFile : files) {
                    if (!nameFile.split("/")[nameFile.split("/").length-1].matches(format)) {
                        System.out.println("Неверный формат файла");
                        throw new IOException();
                    }
                }
                Tar.connect(files[files.length - 1], Arrays.copyOfRange(files, 0, files.length - 2));

            } else {

                System.err.println("Incorrect file name");
                parser.printUsage(System.err);

            }

    }
}