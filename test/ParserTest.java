import es.mdbdev.cvrfparser.model.cvrf.Cvrfdoc;
import es.mdbdev.cvrfparser.xml.CvrfParser;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Mario de Benito on 30/01/2017.
 */
public class ParserTest {
    @Test
    public void testParserOutputCisco(){
        try {
            parseFile("data/cisco-sa-20170124-webex_cvrf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testParserOutputMicrosoft(){
        try {
            parseFile("data/MS16-120.xml");
            parseFile("data/MS17-004.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testParserOutputOracle(){
        try {
            parseFile("data/oracle.xml");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void parseFile(String file) throws IOException, SAXException, ParserConfigurationException {
        CvrfParser parser = new CvrfParser(file);
        Cvrfdoc res = parser.parseCvrfFile();
        System.out.println(res);
        System.out.println("===============================================");
    }
}
