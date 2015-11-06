/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import deploy.DeploymentConfiguration;
import entity.Currency;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author sabre
 */
public class ExchangeRateFacade {

    private Map<String, Currency> theDailyRates;
    private EntityManagerFactory emf;

    public ExchangeRateFacade() {
        //this(Persistence.createEntityManagerFactory("AngSeedServerPU"));
        this(Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME));

    }

    public ExchangeRateFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void fetchExchangeRates() {

        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReader());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));

            persistDailyRates();

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public void persistDailyRates() {

        EntityManager e = getEntityManager();

        try {
            e.getTransaction().begin();
            for (String key : theDailyRates.keySet()) {
                Currency value = theDailyRates.get(key);

                e.persist(value);

            }
            e.getTransaction().commit();
        } finally {

            e.close();
        }
    }

    public List<Currency> getDailyRates() {

        EntityManager em = getEntityManager();
        List<Currency> dailyRates = null;

        try {
            Query query = em.createQuery("SELECT c FROM Currency c");
            dailyRates = query.getResultList();
        } finally {
            em.close();
        }

        return dailyRates;

    }

    // fromCurrency andf toCurrency takes the code (ex.AUD) as arguments
    public float convertCurrency(float amount, String fromCurrency, String toCurrency) {

        List<Currency> ratesList;
        ratesList = getDailyRates();

        float from = 0;
        float to = 0;

        if (fromCurrency.equalsIgnoreCase("dkr")) {

            from = 100;
        }

        if (toCurrency.equalsIgnoreCase("dkr")) {

            to = 100;
        }

        for (Currency cur : ratesList) {

            String code = cur.getCode();

            if (code.equalsIgnoreCase(fromCurrency)) {

                from = Float.parseFloat(cur.getRate());
            }

            if (code.equalsIgnoreCase(toCurrency)) {

                to = Float.parseFloat(cur.getRate());
            }

        }

        return (from / to) * amount;
    }

    //------------------------ NESTED CLASS --------------------------------  
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
            theDailyRates = theRates;

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
            }
            elementCounter = elementCounter + 1;
        }

    }

}
