package facades;


import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import entity.Currency;

public class XmlReader extends DefaultHandler {

    private Map<String, Currency> theRates = new HashMap<>();

    DateFormat dateFormat;
    Date date;
    int elementCounter;
    
    String dateToday;

    @Override
    public void startDocument() throws SAXException {
        elementCounter = 0;
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date = new Date();

    }

    @Override
    public void endDocument() throws SAXException {

        // call facade with hashmap
        
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (elementCounter >= 2) {
            Currency value = new Currency();
            for (int i = 0; i < attributes.getLength(); i++) {

                switch (i) {

                    case (0):
                        value.setCode(attributes.getValue(i));
                        break;

                    case (1):
                        value.setDescribtion(attributes.getValue(i));
                        break;

                    case (2):
                        value.setRate(attributes.getValue(i));
                        break;
                }

            }
            value.setDate(dateFormat.format(date));
            theRates.put(value.getCode(), value);
            System.out.println(value);
        }//end of loop
        System.out.println("");

        elementCounter = elementCounter + 1;
    }

}
