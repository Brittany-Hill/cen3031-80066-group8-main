package com.mygdx.game;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;

public class LogSystem {
    private static final Date currentDate = new Date();
    private static PrintWriter logWriter;

    public static void writeToLog(String infoToWrite) {
        try{
            logWriter = new PrintWriter(new FileWriter("RoVLog.txt", true));
            logWriter.print(currentDate + ": ");
            logWriter.println(infoToWrite);
        }catch(Exception e){
            System.out.println("Error writing to log \n");
        }finally {
            logWriter.close();
        }
    }
}
