package PO53.Abramov.wdad.learn.xml;

import java.util.Calendar;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * Created by amax1 on 21.09.2017.
 */



public class XmlTask  implements xml  {

    private File file;
    private Document doc;
    private String pathname;

            public XmlTask(String pathname) throws Exception {
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
    private NodeList date = doc.getElementsByTagName("date");
    private NodeList officiant = doc.getElementsByTagName("officiant");


    @Override
    public int earningsTotal(String officiantSecondName, Calendar calendar) {
                int day = calendar.DAY_OF_MONTH;
                int month = calendar.MONTH;
                int year = calendar.YEAR;
                int cost=0;
                int numberOfDate = 0;
                for (int i = 0; i < date.getLength(); i++){
                    NamedNodeMap dateNode = date.item(i).getAttributes();
                    if(Integer.parseInt(dateNode.item(0).getNodeValue()) == day &&
                            Integer.parseInt(dateNode.item(1).getNodeValue()) == month &&
                            Integer.parseInt(dateNode.item(2).getNodeValue()) == year)
                    {
                        numberOfDate = i;
                        break;
                    }
                }

                for (int i = 0; i < date.item(numberOfDate).getChildNodes().getLength(); i++) {// для пробега по ордерам
                    for (int j = 0; j < date.item(numberOfDate).getChildNodes().item(i).getChildNodes().getLength(); j++) { //для пробега по оффициантам
                        if (officiant.item(j).getAttributes().item(1).equals(officiantSecondName)){
                            cost +=  Integer.parseInt(date.item(numberOfDate).getChildNodes().item(j).getChildNodes().item(5).getFirstChild().getNodeValue());
                        }
                    }
                }
                return cost;
    }

    @Override
    public void removeDay(Calendar calendar) {
        int day = calendar.DAY_OF_MONTH;
        int month = calendar.MONTH;
        int year = calendar.YEAR;
        int numberOfDate = 0;

        for (int i = 0; i < date.getLength(); i++){
            NamedNodeMap dateNode = date.item(i).getAttributes();
            if(Integer.parseInt(dateNode.item(0).getNodeValue()) == day &&
                    Integer.parseInt(dateNode.item(1).getNodeValue()) == month &&
                    Integer.parseInt(dateNode.item(2).getNodeValue()) == year)
            {
                numberOfDate = i;
                break;
            }
        }
        for (int i = 0; i < date.item(numberOfDate).getChildNodes().getLength(); i++){
            date.item(numberOfDate).removeChild( date.item(numberOfDate).getChildNodes().item(i));
        }
    }

    @Override
    public void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName) {
    for(int i = 0; i < officiant.getLength(); i++){
        if(officiant.item(i).getAttributes().item(0).equals(oldFirstName)
                && officiant.item(i).getAttributes().item(1).equals(oldSecondName)){
            officiant.item(i).getAttributes().item(0).setNodeValue(newFirstName);
            officiant.item(i).getAttributes().item(1).setNodeValue(newSecondName);
        }
    }
    }
}
