package com.example.ewgen.consoleserver.server.run.logic;

import android.support.annotation.IntRange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ServerLogic {

    private ServerSocket serverSocket;
    private Socket client;

    public Socket connectToClient(int port) {

        try { serverSocket = new ServerSocket(port);
            System.out.println("Server started...");

            client = serverSocket.accept();
            System.out.println("Client connected");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                serverSocket.close();
                System.out.println("Server stopped...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return client;
    }

    public Map readingFromFile(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);
        Map<Integer,String> hashMap = new HashMap<>();

        do {

            String line = scanner.nextLine();
            String[] bookData = line.split(",");
            int id = Integer.parseInt(bookData[0]);
            hashMap.put(id,bookData[1]);

        } while (scanner.hasNextLine());

        return hashMap;
    }

    public String searchingById(Map<Integer,String> hashMap, int id) throws IOException {

        String matchingBookData = null;

        for (Map.Entry<Integer, String> entry: hashMap.entrySet()) {
            if (entry.getKey() == id) {
                matchingBookData = (entry.getKey().toString() + "," + entry.getValue());
            }

            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        return matchingBookData;
    }
}
