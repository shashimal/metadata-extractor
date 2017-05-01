package com.duleendra.metadataextractor.service;

/**
 * Created by Duleendra on 29/4/17.
 */
public interface MetadataExtractorService {

    /*
   * Generate the output of metadata based on the output format provided by the client.
   * inputFolder: is the folder which contains the input files Eg: PDF, JPEG etc
   * outputFolder: is the folder which contains the generated metadata files
   * outputFormat: is the format of output file which contains the metadata
   * */
    public void extractMetadataInXmlFormat(String inputFolder, String outputFolder, String outputFormat);

}
