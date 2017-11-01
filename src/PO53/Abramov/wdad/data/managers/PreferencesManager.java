package PO53.Abramov.wdad.data.managers;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by amax1 on 01.11.2017.
 */
public class PreferencesManager {
    private File file;
    private Document doc;
    private String pathname;

    public PreferencesManager(String pathname) throws Exception {
        file = new File(pathname);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        doc = builder.parse(file);
        this.pathname = pathname;
    }

    public Document getDoc() {
        return doc;
    }

    public String getCreateregistry() {
        return doc.getElementsByTagName("createregistry").item(0).getNodeValue();
        }

        public void setCreateregistry(String createregistry) {
            doc.getElementsByTagName("createregistry").item(0).setNodeValue(createregistry);
        }

        public String getRegistryaddress() {
            return doc.getElementsByTagName("registryaddress").item(0).getNodeValue();
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
            return doc.getElementsByTagName("policypath").item(0).getNodeValue();
        }

        public void setPolicypath(String policypath) {
            doc.getElementsByTagName("policypath").item(0).setNodeValue(policypath);
        }

        public String getUsecodebaseonly() {
            return doc.getElementsByTagName("usecodebaseonly").item(0).getNodeValue();
        }

         public void setUsecodebaseonly(String usecodebaseonly) {
             doc.getElementsByTagName("usecodebaseonly").item(0).setNodeValue(usecodebaseonly);
        }

        public String getClassprovider() {
            return doc.getElementsByTagName("classprovider").item(0).getNodeValue();
        }

        public void setClassprovider(String classprovider) {
            doc.getElementsByTagName("classprovider").item(0).setNodeValue(classprovider);
        }
}
