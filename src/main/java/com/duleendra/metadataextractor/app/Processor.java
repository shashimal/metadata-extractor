package com.duleendra.metadataextractor.app;

import com.duleendra.metadataextractor.service.MetadataExtractorService;
import com.duleendra.metadataextractor.service.MetadataExtractorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by duleenw on 4/26/2017.
 */
@Component
public class Processor {

    @Autowired
     MetadataExtractorService metadataExtractorService;

    public static void main(String[] args) {

       /*
       * MetadataExtractorService is used to extract metadata from any file format.
       * inputFolder: is the folder which contains the input files Eg: PDF, JPEG etc
       * outputFolder: is the folder which contains the generated metadata files
       * outputFormat: is the format of output file which contains the metadata.
       */
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        Processor processor = context.getBean(Processor.class);
        processor.extractMetadataInXmlFormat("data_in","data_out","xml");

    }

    private void extractMetadataInXmlFormat(String inputFolder,String outputFolder,String outputFormat) {
        metadataExtractorService.extractMetadataInXmlFormat(inputFolder,outputFolder,outputFormat);
    }
}
