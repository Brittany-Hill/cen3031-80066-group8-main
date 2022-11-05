package com.mygdx.game;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveFile{
    Card tempCard;

    public SaveFile() throws IOException {
    }


    public void SaveToFile(Character characterToSave) throws IOException {
        LogSystem.writeToLog("Saving character ");
        int charaType = characterToSave.getType();
        int charaHP = characterToSave.getHP();
        int charaMana = characterToSave.getMana();
        Inventory tempInv = characterToSave.getInventory();
        PrintWriter toWrite = new PrintWriter(new FileWriter("SaveFile1.txt"));

        try {
            toWrite.println(charaType);
            toWrite.println(charaHP);
            toWrite.println(charaMana);

            for (int i = 0; i < tempInv.getDeckCount(); i++) {
                tempCard = tempInv.getCard(i);
                toWrite.println(tempCard.getID());
            }

        } catch (Exception e) {
            LogSystem.writeToLog("Error writing character to file");
        }
        finally {
            toWrite.close();
        }
    }

    public static Character LoadFromFile(/*potential file name*/){
        LogSystem.writeToLog("Reading character from file");
        Character charaToLoad = new Character();
        CardReader cr = new CardReader();
        Inventory tempInv = charaToLoad.getInventory();
        Scanner fileToScan = null;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            fileToScan = new Scanner(new File("SaveFile1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
                charaToLoad.setType(fileToScan.nextInt());
                charaToLoad.setHP(fileToScan.nextInt());
                charaToLoad.setMana(fileToScan.nextInt());
                 if(fileToScan.hasNextInt() && fileToScan.hasNextLine()){
                     cards.add(cr.readCard(fileToScan.nextInt()));
                 }

        } catch (Exception e) {
            LogSystem.writeToLog("Error reading character from file");
        }
        finally {
            fileToScan.close();
        }

        for (Card card : cards) {
            tempInv.addCard(card);
        }
        charaToLoad.setInventory(tempInv);
        return charaToLoad;
    }

}