package core;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class TarLauncher {

    @Option(name = "-u", metaVar = "split", required = true, usage = "Input file to split", forbids = "-out")
    private boolean inputSplit;

    @Option(name = "-out", metaVar = "connect", required = true, usage = "Input file to connect" ,forbids = "-u")
    private boolean outputConnect;

    @Argument(required = true, metaVar = "Output", usage = "")
    private String files;

    public static void main(String[] args) {

        try {
            new TarLauncher().launch(args);
        }catch (IOException e){
            System.out.println("Ошибка в формате или пути к файлу");
        }

    }

    public void launch(String[] args) throws IOException {

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

        }

        if (inputSplit && files.matches(format)) {

            Scanner reader = new Scanner(new FileReader(files));
            Tar.split(reader);

        } else{

            System.err.println("Incorrect file name");
            parser.printUsage(System.err);

        }

        if (outputConnect && files.matches(format)) {
            for (String nameFile : args) {
                if (!nameFile.matches(format)) {
                    System.out.println("Неверный формат файла");
                    throw new IOException();
                }
            }
             Tar.connect(files, args);

        } else{

            System.err.println("Incorrect file name");
            parser.printUsage(System.err);

        }
    }
}