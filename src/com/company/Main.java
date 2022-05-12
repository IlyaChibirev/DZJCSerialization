package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(8, 7, 8, 23.5);
        GameProgress player2 = new GameProgress(4, 11, 3, 53.2);
        GameProgress player3 = new GameProgress(5, 4, 6, 28.3);

        try (FileOutputStream fos = new FileOutputStream("D://Games/savegames/save.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player1);
            oos.writeObject(player2);
            oos.writeObject(player3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D://Games/savegames/zip_output.zip"));
             FileInputStream fis = new FileInputStream("D://Games/savegames/save.txt")) {
            ZipEntry entry = new ZipEntry("packed.txt");
            zout.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()]; fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        File delete = new File("D://Games/savegames/save.txt");
        if (delete.delete());
    }
}
