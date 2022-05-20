package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        GameProgress player1 = new GameProgress(8, 7, 8, 23.5);
        GameProgress player2 = new GameProgress(4, 11, 3, 53.2);
        GameProgress player3 = new GameProgress(5, 4, 6, 28.3);

        saveGame(player1, player2, player3);
        zipFiles(player1, player2, player3);
    }

    public static void saveGame(GameProgress player1, GameProgress player2, GameProgress player3) {
        try (FileOutputStream fos = new FileOutputStream("D://Games/savegames/save1.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream("D://Games/savegames/save2.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fos = new FileOutputStream("D://Games/savegames/save3.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(player3);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(GameProgress player1, GameProgress player2, GameProgress player3) {

        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("D://Games/savegames/zip_output.zip"));
             FileInputStream fis1 = new FileInputStream("D://Games/savegames/save1.dat");
             FileInputStream fis2 = new FileInputStream("D://Games/savegames/save2.dat");
             FileInputStream fis3 = new FileInputStream("D://Games/savegames/save3.dat")) {
            ZipEntry entry = new ZipEntry("packed.txt");
            zout.putNextEntry(entry);
            byte[] buffer1 = new byte[fis1.available()];
            byte[] buffer2 = new byte[fis2.available()];
            byte[] buffer3 = new byte[fis3.available()];
            fis1.read(buffer1);
            fis2.read(buffer2);
            fis3.read(buffer3);
            zout.write(buffer1);
            zout.write(buffer2);
            zout.write(buffer3);
            zout.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        File delete1 = new File("D://Games/savegames/save1.dat");
        if (delete1.delete()) ;

        File delete2 = new File("D://Games/savegames/save2.dat");
        if (delete2.delete()) ;

        File delete3 = new File("D://Games/savegames/save3.dat");
        if (delete3.delete()) ;
    }

}
