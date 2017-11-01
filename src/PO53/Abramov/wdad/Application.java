package PO53.Abramov.wdad;


import PO53.Abramov.wdad.learn.xml.XmlTask;

import java.util.Calendar;

/**
 * Created by amax1 on 19.09.2017.
 */
public class Application {

    public static void main(String[] args) throws Exception {

        System.out.println("I’m  Maxim Abramov, and I’m not a monkey");

        XmlTask xmlTask = new XmlTask("C:\\Users\\amax1\\starting-monkey-to-human-path\\src\\PO53\\Abramov\\wdad\\learn\\xml\\Test.xml");

        System.out.println(xmlTask.getDoc().getElementsByTagName("officiant").item(0).getAttributes().item(0).getNodeValue());

        System.out.println(xmlTask.getDoc().getElementsByTagName("date").item(0).getAttributes().item(0).getNodeValue());

        System.out.println(xmlTask.getDoc().getChildNodes().getLength());
        System.out.println(xmlTask.getDoc().getChildNodes().item(1).getChildNodes().item(0).getNodeName());

        System.out.println(xmlTask.getDoc().getElementsByTagName("date").item(0).getChildNodes().item(0).getChildNodes().item(5).getFirstChild().getNodeValue());

        //System.out.println(xmlTask.getDoc().getElementsByTagName("totalcost").item(0).getChildNodes().);

        //xmlTask.getDoc().getElementsByTagName("officiant").getLength(); кол-во эл-ов

        //System.out.print(xmlTask.earningsTotal( "Petrov")); РАБОТАЕТ!

    }
}
