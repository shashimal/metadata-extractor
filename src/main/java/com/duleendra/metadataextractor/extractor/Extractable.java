package com.duleendra.metadataextractor.extractor;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Duleendra on 27/4/17.
 */
public interface Extractable {

    HashMap<String,String> extract(File file);
}
