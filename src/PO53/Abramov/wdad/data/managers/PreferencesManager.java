package PO53.Abramov.wdad.data.managers;

import org.w3c.dom.Document;
import PO53.Abramov.wdad.utils.PreferencesConstantManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Enumeration;
import java.util.Properties;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * Created by amax1 on 01.11.2017.
 */
public class PreferencesManager {
    private static volatile PreferencesManager instance;
    private Document doc;
    private static final String FILE_PATH="C:\\Users\\amax1\\starting-monkey-to-human-path\\src\\PO53\\Abramov\\wdad\\resources\\configuration\\appconfig.xml";

    private PreferencesManager() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(new File(FILE_PATH));
    }

    public static PreferencesManager getInstance() throws Exception {
        if (instance == null)
            synchronized (PreferencesManager.class) {
                if (instance == null)
                    instance = new PreferencesManager();
            }

        return instance;
    }

    public void setProperty(String key, String value) {
        getElement(key).setTextContent(value);
    }

    public String getProperty(String key) {
        return getElement(key).getTextContent();
    }

    public void setProperties(Properties prop) {
        Enumeration elements = prop.elements();
        Enumeration keys = prop.keys();
        while (elements.hasMoreElements()) {
            getElement((String) elements.nextElement()).setTextContent((String) keys.nextElement());
        }
    }

    public Properties getProperties() {
        Properties prop = new Properties();
        String[] keys = new String[]{
                PreferencesConstantManager.CREATEREGISTRY,
                PreferencesConstantManager.REGISTRYADDRESS,
                PreferencesConstantManager.REGISTRYPORT,
                PreferencesConstantManager.POLICYPATH,
                PreferencesConstantManager.USECODEBASEONLY,
                PreferencesConstantManager.CLASSPROVIDER
        };

        for (String key : keys) {
            setProperty(key, getElement(key).getTextContent());
        }

        return prop;
    }

    public void addBindedObject(String name, String className) {
        Element element=(Element) doc.createElement("bindedobject");
        element.setAttribute("class",className);
        element.setAttribute("name",name);
        getElement("rmi").appendChild(element);
    }

    public void removeBindedObject(String name) {
        NodeList nodeList = doc.getElementsByTagName("bindedobject");
        Element element;
        for (int i = 0; i < nodeList.getLength(); i++  ) {
            element = (Element) nodeList.item(i);
            if (element.getAttribute("name").equals(name))
            {
                getElement("rmi").removeChild(element);
            }
        }
    }

    private Element getElement(String nameField) {
        NodeList nodeList = doc.getElementsByTagName(nameField);
        String[] field = nameField.split("\\.");
        nodeList = doc.getElementsByTagName(field[field.length - 1]);
        Node node = nodeList.item(0);
        return (Element) node;
    }

    public Document getDoc() {
        return doc;
    }

        public String getCreateregistry() {
            return doc.getElementsByTagName("createregistry").item(0).getTextContent();
        }



        public void setCreateregistry(String createregistry) {
            doc.getElementsByTagName("createregistry").item(0).setNodeValue(createregistry);
        }

        public String getRegistryaddress() {
            return doc.getElementsByTagName("registryaddress").item(0).getTextContent();
        }

        public void setRegistryaddress(String registryaddress) {
            doc.getElementsByTagName("registryaddress").item(0).setNodeValue(registryaddress);
        }

        public int getRegistryport() {
        return Integer.parseInt(doc.getElementsByTagName("registryport").item(0).getNodeValue());
        }

        public void setRegistryport(int registryport) {
            doc.getElementsByTagName("registryport").item(0).setNodeValue(String.valueOf(registryport));
        }

        public String getPolicypath() {
            return doc.getElementsByTagName("policypath").item(0).getTextContent();
        }

        public void setPolicypath(String policypath) {
            doc.getElementsByTagName("policypath").item(0).setNodeValue(policypath);
        }

        public String getUsecodebaseonly() {
            return doc.getElementsByTagName("usecodebaseonly").item(0).getTextContent();
        }

         public void setUsecodebaseonly(String usecodebaseonly) {
             doc.getElementsByTagName("usecodebaseonly").item(0).setNodeValue(usecodebaseonly);
        }

        public String getClassprovider() {
            return doc.getElementsByTagName("classprovider").item(0).getTextContent();
        }

        public void setClassprovider(String classprovider) {
            doc.getElementsByTagName("classprovider").item(0).setNodeValue(classprovider);
        }


}
