package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;

import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(8, 7, 8, 23.5);
        GameProgress player2 = new GameProgress(4, 11, 3, 53.2);
        GameProgress player3 = new GameProgress(5, 4, 6, 28.3);
        GameProgress player4 = new GameProgress(6, 8, 8, 18.3);
        GameProgress player5 = new GameProgress(9, 9, 7, 78.3);

        saveGame("D://Games/savegames/save1.dat", player1);
        saveGame("D://Games/savegames/save2.dat", player2);
        saveGame("D://Games/savegames/save3.dat", player3);
        saveGame("D://Games/savegames/save4.dat", player4);
        saveGame("D://Games/savegames/save5.dat", player5);

        ArrayList<String> player = new ArrayList<>();
        player.add("D://Games/savegames/save1.dat");
        player.add("D://Games/savegames/save2.dat");
        player.add("D://Games/savegames/save3.dat");
        player.add("D://Games/savegames/save4.dat");
        player.add("D://Games/savegames/save5.dat");

        zipFiles(player);

        deleteFile(player);
    }

    public static void saveGame(String path, GameProgress player) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void zipFiles(ArrayList<String> player) {
        try (ZipOutputStream zout = new ZipOutputStream(
                new FileOutputStream( "D://Games/savegames/zip_output.zip", true))) {
            for (String allFile : player) {
                try (FileInputStream fis = new FileInputStream(allFile)) {
                    String[] filePath = allFile.split("/");
                    String fileName = filePath[filePath.length - 1];
                    ZipEntry entry = new ZipEntry(fileName);
                    zout.putNextEntry(entry);
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        zout.write(buffer);
                        zout.closeEntry();
                }
            }
            } catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
    }

    public static void deleteFile(ArrayList<String> player) {
        for (String del : player) {
            File file = new File(del);
            if (file.delete()) ;
        }
    }
}
