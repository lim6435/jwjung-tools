/*
 * Copyright (c) 2018.03.22
 * Made by jjwonyop
 */

package kr.co.jwjung.tools.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Properties;

/**
 * XML Maker(Xml Maker Don't Use JDOM.jar)
 */
public class XmlMaker {
    private Document document;
    private Element rootElement;
    private DocumentBuilder builder;
    private DocumentBuilderFactory builderFactory;

    public XmlMaker() throws ParserConfigurationException {
        this.builderFactory = DocumentBuilderFactory.newInstance();
        this.builder = builderFactory.newDocumentBuilder();
        this.document = builder.newDocument();
        this.document.setXmlStandalone(true);
    }

    /**
     * XML RootName Setting
     * @param XMLRootName root Element 이름
     */
    public void setXMLRoot(String XMLRootName) {
        rootElement = document.createElement(XMLRootName);
        document.appendChild(rootElement);
    }

    /**
     * XML Root하위 단계 세팅(1단계)
     * @param elementName 태그 이름
     * @param value 태그에 값
     */
    public void addElements(String elementName, String value) {
        Element childElements = document.createElement(elementName);
        childElements.setTextContent(value);
        rootElement.appendChild(childElements);
    }

    /**
     * xml 손자 element로 Node 세팅
     * @param parentName 부모 이름
     * @param grandElementName 손자 이름
     * @param value 손자 값
     */
    public void addGrandElementSet(String parentName, String grandElementName, String value){
        Element parentElements = document.createElement(parentName);
        Element grandElements = document.createElement(grandElementName);
        grandElements.setTextContent(value);

        parentElements.appendChild(grandElements);
        rootElement.appendChild(parentElements);
    }

    /**
     * 만들어진 XML 문자열을 받는다.
     * @return Result of XNL String
     * @throws TransformerException
     */
    public String getMakedXmlDocuments() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        String xmlStr = "";

        StringWriter sb = new StringWriter();
        Properties output = new Properties();
        output.setProperty(OutputKeys.INDENT, "yes");
        output.setProperty(OutputKeys.ENCODING, "UTF-8");

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperties(output);

        transformer.transform(new DOMSource(document), new StreamResult(sb));

        xmlStr = sb.getBuffer().toString();
        return xmlStr;
    }

    public static void main(String[] args) {
        try {
            XmlMaker maker = new XmlMaker();
            maker.setXMLRoot("Roos");
            maker.addElements("Test1", "12345");
            maker.addElements("Test2", "ci");
            maker.addGrandElementSet("test3", "testgrand", "1111");
            System.out.println(maker.getMakedXmlDocuments());
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
