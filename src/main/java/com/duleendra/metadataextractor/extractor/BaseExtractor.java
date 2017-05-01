package com.duleendra.metadataextractor.extractor;

import com.duleendra.metadataextractor.factory.ParserFactory;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Duleendra on 27/4/17.
 */
public class BaseExtractor {

    protected File file;
    protected String contentType;
    protected String bodyContent;
    protected HashMap<String,String> metaFieldsMap;

    /*
    * Parse the input file and extract the metadata
    * */
    protected Metadata parse() {
        Metadata metadata = null;
        try {
            BodyContentHandler handler = new BodyContentHandler();
            metadata = new Metadata();
            ParseContext parseContext = new ParseContext();
            Parser parser = ParserFactory.getParser(this.contentType);
            FileInputStream inputStream = new FileInputStream(this.getFile());
            parser.parse(inputStream, handler, metadata,parseContext);
            this.setBodyContent(handler.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metadata;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public HashMap getMetaFieldsMap() {
        return metaFieldsMap;
    }

    public void setMetaFieldsMap(HashMap metaFieldsMap) {
        this.metaFieldsMap = metaFieldsMap;
    }
}
