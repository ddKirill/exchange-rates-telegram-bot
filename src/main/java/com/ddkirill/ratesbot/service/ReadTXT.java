package com.ddkirill.ratesbot.service;

import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
public class ReadTXT {

    public String readTextFile(String fileName) {

        String readText = "";

        try {
            FileReader fileReader = new FileReader(fileName);
            char[] buffer = new char[8096];
            int chars = fileReader.read(buffer);

            while (chars != -1) {
                readText = String.valueOf(buffer, 0, chars);
                chars = fileReader.read(buffer);
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("File not found or not reading");
        }

        return readText;
    }

}
