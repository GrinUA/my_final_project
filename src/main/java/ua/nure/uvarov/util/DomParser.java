package ua.nure.uvarov.util;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ua.nure.uvarov.exceptions.AppException;

public class DomParser {
    private Map<String,Set<String>> accessMap = new HashMap<>();
    private Set<String> allRestricUrl = new HashSet<>();


    public void parse(String fileName) {
        File inputFile = new File(fileName);
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse("D:/EPAM/courses/FinalProject/access.xml");
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("constraint");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList roles = eElement.getElementsByTagName("role");
                    NodeList urls = eElement.getElementsByTagName("url");
                    for (int i = 0; i < roles.getLength(); i++) {
                        Element rElement = (Element) roles.item(i);
                        String role = rElement.getTextContent();
                        if(!accessMap.containsKey(role)){
                            accessMap.put(role,new HashSet<>());
                        }
                        Set<String> accessSet = accessMap.get(role);
                        for (int j= 0;j<urls.getLength();j++){
                            Element uElement = (Element) urls.item(j);
                            accessSet.add(uElement.getTextContent());
                            allRestricUrl.add(uElement.getTextContent());
                        }
                    }

                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new AppException(e);
        }
    }

    public Map<String, Set<String>> getAccessMap() {
        return accessMap;
    }

    public Set<String> getAllRestricUrl() {
        return allRestricUrl;
    }
}

