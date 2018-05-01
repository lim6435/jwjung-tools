/*
 * Copyright (c) 2018.03.22
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Xml Parser Class
 * 원하는 태그 값 갖고 오는 것으로 함
 */
public class XmlParser {
    private Document document;
    /**
     * xmlParser Setting(Xml Parser Constructor)
     * @param xml (xml 문자열)
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public XmlParser(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        InputSource inputSource = new InputSource(new StringReader(xml));
        document = builder.parse(inputSource);
    }

    /**
     * xml 태그중 원하는 값 가져온다.(같은 태그가 여러개 일 경우, 첫번째것으로 갖고옴)
     * @param tagName (원하는 태그 이름)
     * @return 태그 값
     */
    public String getNodeText(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        String res = "";
        if (nodeList == null || nodeList.getLength() == 0) {
            return res;
        }
        return nodeList.item(0).getTextContent();
    }

    /**
     * xml 태그 중 원하는 값들을 갖고 온다.(같은 태그 모두 긁음)
     *
     * @param tagName 원하는 태그들 이름
     * @return 태그 해당 문자열 전체
     */
    public String[] getNodeTexts(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);

        if (nodeList == null || nodeList.getLength() == 0) {
            return null;
        }

        int nodeLen = nodeList.getLength();
        String[] results = new String[nodeLen];

        for (int i = 0; i < nodeLen; i++) {
            results[i] = nodeList.item(i).getTextContent();
        }

        return results;
    }

    public static void main(String[] args) {
        String testXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Roos>" +
                "<Test1>12345</Test1>" +
                "<Test2>ci</Test2>" +
                "<test3>" +
                "<testgrand>1111</testgrand>" +
                "</test3>" +
                "</Roos>";

        try {
            XmlParser parser = new XmlParser(testXml);
            System.out.println(parser.getNodeText("Test1"));
            String[] arr = parser.getNodeTexts("test3");
            for (String s : arr) {
                System.out.println(s);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
