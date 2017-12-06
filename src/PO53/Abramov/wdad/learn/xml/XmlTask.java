package PO53.Abramov.wdad.learn.xml;

import java.util.Calendar;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
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

    //возвращающий кол- во заданных блюд, которые были заказаны в заданныхй день
    public int numberOfItemsPerDay (Calendar calendar, String nameOfItem) {
        int day = calendar.get(calendar.DAY_OF_MONTH);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int numOfItems = 0;
        NodeList dates = doc.getElementsByTagName("date");
        for (int z = 0; z < dates.getLength(); z++) {
            Node dateNode = dates.item(z);
            NamedNodeMap dateNodeAttributes = dateNode.getAttributes();
            if (Integer.parseInt(dateNodeAttributes.item(0).getNodeValue()) == day &&
                    Integer.parseInt(dateNodeAttributes.item(1).getNodeValue()) == month &&
                    Integer.parseInt(dateNodeAttributes.item(2).getNodeValue()) == year) {
                NodeList orders = dateNode.getChildNodes();
                for (int j = 0; j < orders.getLength(); j++) {
                    NodeList items = ((Element) orders.item(j)).getElementsByTagName("item");
                    for (int k = 0; k < items.getLength(); k++) {
                        if(items.item(k).getAttributes().getNamedItem("name").getTextContent().equals(nameOfItem)) numOfItems += 1;
                    }
                }
            }
        }
        return numOfItems;
    }

    @Override
    public int earningsTotal(String officiantSecondName, Calendar calendar) {
        int day = calendar.get(calendar.DAY_OF_MONTH);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int cost = 0;
        NodeList dates = doc.getElementsByTagName("date");
        for (int z = 0; z < dates.getLength(); z++) {
            Node dateNode = dates.item(z);
            NamedNodeMap dateNodeAttributes = dateNode.getAttributes();
            if (Integer.parseInt(dateNodeAttributes.item(0).getNodeValue()) == day &&
                    Integer.parseInt(dateNodeAttributes.item(1).getNodeValue()) == month &&
                    Integer.parseInt(dateNodeAttributes.item(2).getNodeValue()) == year) {
                //todo перенести счёт cost'a сюда
                //todo переписать, брать cost не из тоталкоста, а из атрибутов айтемов
                NodeList orders = dateNode.getChildNodes();
                for (int j = 0; j < orders.getLength(); j++) {
                    boolean foundOfficiant = false;
                    NodeList items = orders.item(j).getChildNodes();
                    for (int k = 0; k < items.getLength(); k++) {
                        if(items.item(k).getNodeName().equals("officiant") &&
                           items.item(k).getAttributes().getNamedItem("secondname").getTextContent().equals(officiantSecondName)) foundOfficiant=true;
                        if(foundOfficiant &&  items.item(k).getNodeName().equals("totalcost")) cost+=Integer.parseInt(items.item(k).getTextContent());
                    }
                }
            }
        }
        return cost;
    }


    @Override
    public void removeDay(Calendar calendar) {
        int day = calendar.get(calendar.DAY_OF_MONTH);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        NodeList date = doc.getElementsByTagName("date");

        for (int i = 0; i < date.getLength(); i++){
            NamedNodeMap datesNode = date.item(i).getAttributes();
            if(Integer.parseInt(datesNode.item(0).getNodeValue()) == day &&
                    Integer.parseInt(datesNode.item(1).getNodeValue()) == month &&
                    Integer.parseInt(datesNode.item(2).getNodeValue()) == year)
            {
                //todo производить удаление здесь
                for (int j = 0; j < date.item(i).getChildNodes().getLength(); j++){
                    date.item(i).removeChild( date.item(i).getChildNodes().item(j));
                }

            }
        }
    }

    @Override
    public void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName) {
        NodeList officiant = doc.getElementsByTagName("officiant");
        for(int i = 0; i < officiant.getLength(); i++){
        if(officiant.item(i).getAttributes().item(0).equals(oldFirstName)
                && officiant.item(i).getAttributes().item(1).equals(oldSecondName)){
            officiant.item(i).getAttributes().item(0).setNodeValue(newFirstName);
            officiant.item(i).getAttributes().item(1).setNodeValue(newSecondName);
        }
    }
    }

    public int returnNumOfDate(Calendar calendar){
        int day = calendar.get(calendar.DAY_OF_MONTH);
        int month = calendar.get(calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int numberOfDate = 0;
        NodeList date = doc.getElementsByTagName("date");

        for (int i = 0; i < date.getLength(); i++){
            NamedNodeMap dateNode = date.item(i).getAttributes();
            if(Integer.parseInt(dateNode.item(0).getNodeValue()) == day &&
                    Integer.parseInt(dateNode.item(1).getNodeValue()) == month &&
                    Integer.parseInt(dateNode.item(2).getNodeValue()) == year)
            {
                numberOfDate = i;
            }
        }
        return numberOfDate;
    }
}
