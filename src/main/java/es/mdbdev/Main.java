package es.mdbdev;

import es.mdbdev.cvrfparser.model.cvrf.Cvrfdoc;
import es.mdbdev.cvrfparser.xml.CvrfParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by tf05046 on 07/02/2017.
 */
public class Main {
    public static void main(String [] args){
        CvrfParser parser = new CvrfParser("data/MS16-120.xml");
        try {
            Cvrfdoc result = parser.parseCvrfFile();
            System.out.println(result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
