package com.duleendra.metadataextractor.service;

import com.duleendra.metadataextractor.extractor.Extractable;
import com.duleendra.metadataextractor.extractor.Extractor;
import com.duleendra.metadataextractor.file.FileHandler;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Duleendra on 29/4/17.
 */

@Service
public class MetadataExtractorServiceImpl implements MetadataExtractorService {

    Extractable extractable = new Extractor();
    List<HashMap<String, String>> extractedFileList = null;
    HashMap<String, List<HashMap<String, String>>> folderFileMap;


    public void extractMetadataInXmlFormat(String inputFolder, String outputFolder,String outputFormat) {
        folderFileMap = new HashMap<String, List<HashMap<String, String>>>();

        readFolder(inputFolder);

        if (extractedFileList != null) {
            //Write extracted metadata into a XML file.
            if (outputFormat.equalsIgnoreCase("xml")){
                FileHandler.writeToXml(folderFileMap, outputFolder);
            }
        }
    }

    /*
    * Read the input folder recursively
    * Keep all the extracted metadata in a HashMap
    * */
    private void readFolder(String inputFolder) {
        File[] listOfFiles = FileHandler.getAllFiles(inputFolder);
        if (listOfFiles != null && listOfFiles.length > 0) {
            for (File file : listOfFiles) {

                if (file.isFile() && !file.getName().startsWith(".") && !file.isHidden()) {
                    HashMap<String, String> extractedMetadata = extractable.extract(file);
                    if (extractedMetadata != null) {
                        String[] pathElements = FileHandler.getPathArray(file.getAbsolutePath(), "/");
                        String pathKey = null;

                        if (pathElements.length > 0) {
                            String firstLevel = pathElements[pathElements.length - 3];
                            String secondLevel = pathElements[pathElements.length - 2];

                            pathKey =firstLevel + "_" + secondLevel;
                            if(pathKey.contains("data_in")) {
                                pathKey = pathKey.replace("data_in",secondLevel);
                            }
                            pathKey = pathKey.toLowerCase();
                        }

                        if (folderFileMap.containsKey(pathKey)) {
                            folderFileMap.get(pathKey).add(extractedMetadata);
                        } else {
                            extractedFileList = new ArrayList<HashMap<String, String>>();
                            extractedFileList.add(extractedMetadata);
                            folderFileMap.put(pathKey, extractedFileList);
                        }
                    }
                } else {
                    readFolder(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println(inputFolder+ ": Input folder is empty");
        }
    }
}
