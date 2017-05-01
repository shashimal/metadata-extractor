package com.duleendra.metadataextractor.file;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Duleendra on 29/4/17.
 */
public class XmlFileHandler {

    public void writeToXml(HashMap<String, List<HashMap<String, String>>> folderFileMaps, String outputFolder) {

        try {
            for (Map.Entry<String, List<HashMap<String, String>>> listMapEntry : folderFileMaps.entrySet()) {
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                Document doc = docBuilder.newDocument();

                Element rootElement = doc.createElement("documents");
                doc.appendChild(rootElement);

                String outputFile = null;
                String categoryFolder = listMapEntry.getKey();

                outputFile = categoryFolder + ".xml";
                List<HashMap<String, String>> lst = listMapEntry.getValue();

                for (HashMap<String, String> listKeyValueMaps : lst) {
                    Element eleDocument = doc.createElement("document");
                    rootElement.appendChild(eleDocument);
                    for (Map.Entry<String, String> entry : listKeyValueMaps.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        //System.out.println(key + ":" + value);
                        Element element = doc.createElement("element");

                        Attr attr = doc.createAttribute("name");
                        attr.setValue(key);
                        element.setAttributeNode(attr);

                        Element eleValue = doc.createElement("value");
                        eleValue.appendChild(doc.createTextNode(value));
                        element.appendChild(eleValue);
                        eleDocument.appendChild(element);

                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(outputFolder + "/" + outputFile));
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
