package com.praveen.docker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Others {
    public static void main(String[] args) throws IOException {
        System.out.println("Praveen");

        try(FileReader file = new FileReader(new File("file.txt"))){
            System.out.println(file.read());
        }

    }
}
