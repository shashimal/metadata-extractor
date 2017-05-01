package com.duleendra.metadataextractor.extractor;

import com.duleendra.metadataextractor.file.FileHandler;
import org.apache.tika.metadata.Metadata;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Duleendra on 27/4/17.
 */
public class Extractor extends BaseExtractor implements Extractable {

    public HashMap<String, String> extract(File file) {
        String contentType = FileHandler.getFileContentType(file);
        this.setFile(file);
        this.setContentType(contentType);
        Metadata metadata = this.parse();

        String[] metadataNames = metadata.names();
        if(metadataNames.length > 0) {
            this.metaFieldsMap = new HashMap<String, String>();
            for (String key: metadataNames){
                String value = metadata.get(key);
                this.metaFieldsMap.put(key,value);
            }

        }
        return this.metaFieldsMap;
    }


}
