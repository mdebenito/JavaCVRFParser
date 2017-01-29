package es.mdbdev;

import es.mdbdev.model.cvrf.Cvrfdoc;
import es.mdbdev.xml.CvrfParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        CvrfParser parser = new CvrfParser("data/cisco-sa-20170124-webex_cvrf.xml");
        CvrfParser parser2 = new CvrfParser("data/MS17-004.xml");
        try {
            Cvrfdoc res = parser.parseCvrfFile();
            System.out.println(res);
            System.out.println("===============================================");
            res = parser2.parseCvrfFile();
            System.out.println(res);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
