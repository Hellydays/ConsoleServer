package com.example.ewgen.consoleserver.server.run.run;

import com.example.ewgen.consoleserver.server.run.logic.ServerLogic;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) {

        int id = 0;

        String separator = File.separator;
        String pathEwgen = separator + "home" + separator + "ewgen" + separator + "Документы"+ separator + "booklist.txt";
        File file = new File(pathEwgen);

        try {

            ServerLogic serverLogic = new ServerLogic();
            Socket client = serverLogic.connectToClient(9595);

            Scanner scanner = new Scanner(client.getInputStream());
            id = scanner.nextInt(); //id can be used to compare

            PrintStream printStream = new PrintStream(client.getOutputStream());

            Map<Integer,String> hashMap = serverLogic.readingFromFile(file);
            String matchingBookResult = serverLogic.searchingById(hashMap,id);

//
            printStream.println(matchingBookResult);
//

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}