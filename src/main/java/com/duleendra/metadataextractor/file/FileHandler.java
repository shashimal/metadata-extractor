package com.duleendra.metadataextractor.file;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Duleendra on 28/4/17.
 */
public class FileHandler {
    public static String FILE_TYPE_PDF = "application/pdf";
    public static String FILE_TYPE_JPEG= "jpeg";

    public static String getFileContentType(File file) {
        String contentType = null;
        if (file.isFile()) {

            try {
                Tika tika = new Tika();
                contentType = tika.detect(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return contentType;
    }

    public static File[] getAllFiles(String location) {
        File folder = new File(location);
        File[] listOfFiles = folder.listFiles();
        return listOfFiles;
    }

    public static String[] getPathArray(String filePath,String delimiter) {
        if(!filePath.equals("")){
            return filePath.split(delimiter);
        }
       return null;
    }

    public static void writeToXml(HashMap<String, List<HashMap<String,String>>> folderFileMap, String outputFolder){
        XmlFileHandler xmlFileHandler = new XmlFileHandler();
        xmlFileHandler.writeToXml(folderFileMap,outputFolder);





    }
}
