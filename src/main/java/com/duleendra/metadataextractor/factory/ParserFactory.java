package com.duleendra.metadataextractor.factory;

import com.duleendra.metadataextractor.file.FileHandler;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.jpeg.JpegParser;
import org.apache.tika.parser.pdf.PDFParser;

/**
 * Created by Duleendra on 27/4/17.
 */
public class ParserFactory {

    public static Parser getParser(String contentType) {
        Parser parser = null;
        if(contentType.equalsIgnoreCase(FileHandler.FILE_TYPE_PDF)){
            parser = new PDFParser();
        }else if (contentType.equalsIgnoreCase(FileHandler.FILE_TYPE_JPEG)) {
            parser = new JpegParser();
        }else {
            parser = new AutoDetectParser();
        }
        return parser;
    }
}
