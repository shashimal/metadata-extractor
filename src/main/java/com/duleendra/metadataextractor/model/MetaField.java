package com.duleendra.metadataextractor.model;

import org.apache.tika.metadata.Metadata;

import java.util.HashMap;

/**
 * Created by duleenw on 4/26/2017.
 */
public class MetaField {

    public static HashMap<String,String> getMetaFieldMap(Metadata metadata) {
        HashMap<String,String> fieldMap = null;
        String[] metadataNames = metadata.names();
        if(metadataNames.length >0){
            fieldMap = new HashMap<String, String>();
            for(String name : metadataNames) {
                fieldMap.put(name,metadata.get(name));
                //System.out.println(name + ": " + metadata.get(name));
            }
        }
        return fieldMap;
    }
}
