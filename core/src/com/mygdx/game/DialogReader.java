package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class DialogReader {

    public static ArrayList<String> dialogScanner(String fileName){
        ArrayList<String> lines = new ArrayList<>();
        Scanner fileScan = null;
        try {
            fileScan = new Scanner(new File(fileName));
            if(fileScan.hasNext()) {
                do {
                    lines.add(fileScan.nextLine());
                } while (fileScan.hasNext());
            }
        }catch (FileNotFoundException e){
            LogSystem.writeToLog("Error opening " + fileName);
        }


        fileScan.close();

        return lines;
    }
}
