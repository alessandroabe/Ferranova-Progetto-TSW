package org.unisa.abeilleamorellifontana_pj.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoManager {

    /**
     * Ottiene la lista dei nomi delle foto nella directory specificata.
     *
     * @param photoDirectory Il percorso della directory delle foto
     * @return Una lista di nomi delle foto nella directory
     */
    public static List<String> getPhotoList(String photoDirectory) {
        List<String> photoList = new ArrayList<>();
        File directory = new File(photoDirectory);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        photoList.add(file.getName());
                    }
                }
            }
        }
        return photoList;
    }

    /**
     * Conta il numero di foto nella directory specificata.
     *
     * @param id_product Il percorso della directory delle foto/ id del prodotto
     * @return Il numero di foto nella directory
     */
    public static int countPhotosInDirectory(int id_product, String path) {
        int photoCount = 0;
        String directoryPath = path + "/product_images/" + id_product;
        File directory = new File(directoryPath);

        try {
            if (directory.exists() && directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            photoCount++;
                        }
                    }
                }
            } else {
                System.out.println("La directory non esiste o non è una directory valida.");
            }
        } catch (SecurityException e) {
            System.out.println("Errore di sicurezza: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Errore durante il conteggio delle foto: " + e.getMessage());
        }

        return photoCount;
    }
}
