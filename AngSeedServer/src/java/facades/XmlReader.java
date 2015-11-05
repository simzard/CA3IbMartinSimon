package facades;

import entity.ExchangeRate;
import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class XmlReader extends DefaultHandler {

    List<String> dailyRates = new ArrayList<String>();
    int elementCounter = 0;
    ExchangeRate theDailyRate = new ExchangeRate();

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {
        
        theDailyRate.setAUD(dailyRates.get(2));
        
        System.out.println(theDailyRate.getAUD());
        
        

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        int elementNO = attributes.getLength() - 1;

        System.out.print("Element: " + localName + ": ");
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");

            if (i == elementNO) {

                dailyRates.add(attributes.getValue(i));

                System.out.println(attributes.getValue(i));

            }

        }
        System.out.println("");
    }

}
