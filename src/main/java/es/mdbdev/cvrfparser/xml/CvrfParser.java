package es.mdbdev.cvrfparser.xml;

import es.mdbdev.cvrfparser.model.cvrf.Cvrfdoc;
import es.mdbdev.cvrfparser.model.cvrf.common.*;
import es.mdbdev.cvrfparser.model.cvrf.common.Reference;
import es.mdbdev.cvrfparser.model.cvrf.documentreferences.DocumentReferences;
import es.mdbdev.cvrfparser.model.cvrf.documenttracking.*;
import es.mdbdev.cvrfparser.model.cvrf.vulnerability.*;
import es.mdbdev.cvrfparser.model.cvrf.documentnotes.DocumentNotes;
import es.mdbdev.cvrfparser.model.cvrf.documentpublisher.DocumentPublisher;
import es.mdbdev.cvrfparser.model.cvrf.producttree.FullProductName;
import es.mdbdev.cvrfparser.model.cvrf.producttree.ProductTree;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Mario on 29/01/2017.
 */
public class CvrfParser {
    /**
     * File that will be loaded and processed
     */
    final private File xmlFile;

    /**
     * Constructor
     * @param file String with the file location
     */
    public CvrfParser(String file){
        xmlFile = new File(file);
    }

    /**
     * Constructor
     * @param file File object
     */
    public CvrfParser(File file){
        xmlFile = file;
    }

    /**
     * Method that launches the file parsing
     * @return Cvrfdoc object with the CVRF document information
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public Cvrfdoc parseCvrfFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler();
        saxParser.parse(xmlFile, xmlHandler);
        return xmlHandler.getCvrfDocument();
    }


    /**
     * Class in charge of handling the SAX parsing of the XML file into a Cvrfdoc object
     */
    class XMLHandler  extends DefaultHandler {
        private final StringBuilder chars = new StringBuilder();

        private Cvrfdoc cvrfDocument;

        private Revision crrntRev = null;
        private Note crrntDocumentNote = null;
        private FullProductName crrntFullProductName = null;
        private Vulnerability crrntVulnerability = null;
        private Note crrntVulnerabilityNote = null;
        private Status crrntVulnerabilityStatus = null;
        private Threat crrntVulnerabilityThreat = null;
        private Remediation crrntVulnerabilityRemediation = null;
        private Reference crrntDocumentReference = null;

        private boolean bDocumentNotes = false;
        private boolean bDocumentTrackingRevisionHistoryRevision = false;
        private boolean bVulnerability = false;
        private boolean bVulnerabilityNotes = false;
        private boolean bVulnerabilityProductStatuses = false;
        private boolean bVulnerabilityProductstatusesStatus = false;
        private boolean bVulnerabilityThreats = false;
        private boolean bVulnerabilityThreatsThreat = false;
        private boolean bVulnerabilityRemediations = false;
        private boolean bVulnerabilityRemediationsRemediation = false;
        private boolean bDocumentReferences = false;
        private boolean bDocumentReferencesReference = false;
        private boolean bDocumentTracking = false;
        private boolean bDocumentTrackingIdentification = false;
        private boolean bDocumentTrackingRevisionHistory = false;
        private boolean bDocumentPublisher = false;
        private boolean bProductTree = false;
        private boolean bDocumentTrackingGenerator = false;


        public XMLHandler() {

            this.cvrfDocument = new Cvrfdoc();
        }

        public Cvrfdoc getCvrfDocument() {
            return cvrfDocument;
        }

        @Override
        public void startElement(String uri,
                                 String localName, String qName, Attributes attributes)
                throws SAXException {
            chars.setLength(0);

            String[] tmp = qName.split(":");
            if(tmp.length>1)
                qName = tmp[1];
            else
                qName = tmp[0];

            if (qName.equalsIgnoreCase("cvrfdoc")) {
                this.cvrfDocument = new Cvrfdoc();
            }else if (qName.equalsIgnoreCase("DocumentPublisher")) {
                bDocumentPublisher = true;
                cvrfDocument.setDocumentPublisher(new DocumentPublisher());
            }else if (qName.equalsIgnoreCase("DocumentTracking")) {
                bDocumentTracking = true;
                cvrfDocument.setDocumentTracking(new DocumentTracking());
            }else if ((qName.equalsIgnoreCase("Identification")&&bDocumentTracking)) {
                bDocumentTrackingIdentification = true;
                cvrfDocument.getDocumentTracking().setIdentification(new Identification());
            }else if ((qName.equalsIgnoreCase("RevisionHistory")&&bDocumentTracking)) {
                bDocumentTrackingRevisionHistory = true;
                cvrfDocument.getDocumentTracking().setRevisionHistory(new RevisionHistory());
            }else if ((qName.equalsIgnoreCase("Revision")&& bDocumentTrackingRevisionHistory)) {
                bDocumentTrackingRevisionHistoryRevision = true;
                crrntRev = new Revision();
            }else if ((qName.equalsIgnoreCase("Generator")&&bDocumentTracking)) {
                bDocumentTrackingGenerator = true;
                cvrfDocument.getDocumentTracking().setGenerator(new Generator());
            }else if (qName.equalsIgnoreCase("DocumentReferences")) {
                bDocumentReferences = true;
                cvrfDocument.setDocumentReferences(new DocumentReferences());
            }else if ((qName.equalsIgnoreCase("Reference") && bDocumentReferences)) {
                bDocumentReferencesReference = true;
                crrntDocumentReference = new Reference();
                crrntDocumentReference.setType(attributes.getValue("Type"));
            }else if (qName.equalsIgnoreCase("DocumentNotes")) {
                bDocumentNotes = true;
                cvrfDocument.setDocumentNotes(new DocumentNotes());
            }else if ((qName.equalsIgnoreCase("Note") && bDocumentNotes)) {
                crrntDocumentNote = new Note();
                crrntDocumentNote.setOrdinal(attributes.getValue("Ordinal"));
                crrntDocumentNote.setType(attributes.getValue("Type"));
                crrntDocumentNote.setAudience(attributes.getValue("Audience"));
                crrntDocumentNote.setTitle(attributes.getValue("Title"));
            }else if (qName.equalsIgnoreCase("ProductTree")) {
                bProductTree = true;
                cvrfDocument.setProductTree(new ProductTree());
            }else if (qName.equalsIgnoreCase("FullProductName") && bProductTree) {//IGNORING BRANCHES FOR NOW
                crrntFullProductName = new FullProductName();
                crrntFullProductName.setProductID(attributes.getValue("ProductID"));
            }else if (qName.equalsIgnoreCase("Vulnerability")) {
                bVulnerability = true;
                crrntVulnerability = new Vulnerability();
                crrntVulnerability.setOrdinal(attributes.getValue("Ordinal"));
            }else if ((qName.equalsIgnoreCase("ID") && bVulnerability)) {
                crrntVulnerability.setID(new ID());
                crrntVulnerability.getID().setSystemName(attributes.getValue("SystemName"));
            }else if ((qName.equalsIgnoreCase("Notes") && bVulnerability)) {
                bVulnerabilityNotes = true;
                crrntVulnerability.setNotes(new Notes());
            }else if ((qName.equalsIgnoreCase("Note") && bVulnerabilityNotes)) {
                crrntVulnerabilityNote = new Note();
                crrntVulnerabilityNote.setOrdinal(attributes.getValue("Ordinal"));
                crrntVulnerabilityNote.setType(attributes.getValue("Type"));
                crrntVulnerabilityNote.setAudience(attributes.getValue("Audience"));
                crrntVulnerabilityNote.setTitle(attributes.getValue("Title"));
            }else if ((qName.equalsIgnoreCase("ProductStatuses") && bVulnerability)) {
                bVulnerabilityProductStatuses = true;
                crrntVulnerability.setProductStatuses(new ProductStatuses());
            }else if ((qName.equalsIgnoreCase("Status") && bVulnerabilityProductStatuses)) {
                bVulnerabilityProductstatusesStatus = true;
                crrntVulnerabilityStatus = new Status();
                crrntVulnerabilityStatus.setType(attributes.getValue("Type"));
            }else if ((qName.equalsIgnoreCase("Threats") && bVulnerability)) {
                bVulnerabilityThreats = true;
                crrntVulnerability.setThreats(new Threats());
            }else if ((qName.equalsIgnoreCase("Threat") && bVulnerabilityThreats)) {
                bVulnerabilityThreatsThreat = true;
                crrntVulnerabilityThreat = new Threat();
                crrntVulnerabilityThreat.setType(attributes.getValue("Type"));
                crrntVulnerabilityThreat.setDate(attributes.getValue("Date"));
            }else if ((qName.equalsIgnoreCase("Remediations") && bVulnerability)) {
                bVulnerabilityRemediations = true;
                crrntVulnerability.setRemediations(new Remediations());
            }else if ((qName.equalsIgnoreCase("Remediation") && bVulnerabilityRemediations)) {
                bVulnerabilityRemediationsRemediation = true;
                crrntVulnerabilityRemediation = new Remediation();
                crrntVulnerabilityRemediation.setType(attributes.getValue("Type"));
            }
        }

        @Override
        public void characters(char ch[],
                               int start, int length) {
            chars.append(ch, start, length);
        }
        
        @Override
        public void endElement(String uri,
                               String localName, String qName) {
            String[] tmp = qName.split(":");
            if(tmp.length>1)
                qName = tmp[1];
            else
                qName = tmp[0];

            if (qName.equalsIgnoreCase("DocumentTitle")) {
                cvrfDocument.setDocumentTitle(chars.toString());
            }else if (qName.equalsIgnoreCase("DocumentType")) {
                cvrfDocument.setDocumentType(chars.toString());
            }else if (qName.equalsIgnoreCase("DocumentPublisher")) {
                bDocumentPublisher = false;
            }else if ((qName.equalsIgnoreCase("ContactDetails") && bDocumentPublisher)) {
                cvrfDocument.getDocumentPublisher().setContactDetails(chars.toString());
            }else if ((qName.equalsIgnoreCase("IssuingAuthority")) && bDocumentPublisher) {
                cvrfDocument.getDocumentPublisher().setIssuingAuthority(chars.toString());
            }else if (qName.equalsIgnoreCase("DocumentTracking")) {
                bDocumentTracking = false;
            }else if ((qName.equalsIgnoreCase("Identification")&&bDocumentTracking)) {
                bDocumentTrackingIdentification = false;
            }else if ((qName.equalsIgnoreCase("ID")&&bDocumentTrackingIdentification)) {
                cvrfDocument.getDocumentTracking().getIdentification().setID(chars.toString());
            }else if ((qName.equalsIgnoreCase("Alias")&&bDocumentTracking)) {
                cvrfDocument.getDocumentTracking().getIdentification().setAlias(chars.toString());
            }else if ((qName.equalsIgnoreCase("Status")&&bDocumentTracking)) {
                cvrfDocument.getDocumentTracking().setStatus(chars.toString());
            }else if ((qName.equalsIgnoreCase("Version")&&bDocumentTracking)) {
                cvrfDocument.getDocumentTracking().setVersion(chars.toString());
            }else if ((qName.equalsIgnoreCase("InitialReleaseDate")&&bDocumentTracking)) {
                cvrfDocument.getDocumentTracking().setInitialReleaseDate(chars.toString());
            }else if ((qName.equalsIgnoreCase("CurrentReleaseDate")&&bDocumentTracking)) {
                cvrfDocument.getDocumentTracking().setCurrentReleaseDate(chars.toString());
            }else if ((qName.equalsIgnoreCase("Generator")&&bDocumentTracking)) {
                bDocumentTrackingGenerator = false;
            }else if (qName.equalsIgnoreCase("Engine") && bDocumentTrackingGenerator) {
                cvrfDocument.getDocumentTracking().getGenerator().setEngine(chars.toString());
            }else if ((qName.equalsIgnoreCase("RevisionHistory")&&bDocumentTracking)) {
                bDocumentTrackingRevisionHistory = false;
            }else if ((qName.equalsIgnoreCase("Revision")&& bDocumentTrackingRevisionHistory)) {
                cvrfDocument.getDocumentTracking().getRevisionHistory().addRevision(crrntRev);
                crrntRev = null;
                bDocumentTrackingRevisionHistoryRevision = false;
            }else if ((qName.equalsIgnoreCase("Number") && bDocumentTrackingRevisionHistoryRevision)) {
                crrntRev.setNumber(chars.toString());
            }else if ((qName.equalsIgnoreCase("Date") && bDocumentTrackingRevisionHistoryRevision)) {
                crrntRev.setDate(chars.toString());
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentTrackingRevisionHistoryRevision)) {
                crrntRev.setDescription(chars.toString());
            }else if (qName.equalsIgnoreCase("DocumentReferences")) {
                bDocumentReferences = false;
            }else if ((qName.equalsIgnoreCase("Reference") && bDocumentReferences)) {
                bDocumentReferencesReference = false;
                cvrfDocument.getDocumentReferences().addReference(crrntDocumentReference);
                crrntDocumentReference = null;
            }else if ((qName.equalsIgnoreCase("URL") && bDocumentReferencesReference)) {
                crrntDocumentReference.setURL(chars.toString());
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentReferencesReference)) {
                crrntDocumentReference.setDescription(chars.toString());
            }else if (qName.equalsIgnoreCase("DocumentNotes")) {
                bDocumentNotes = false;
            }else if ((qName.equalsIgnoreCase("Note") && bDocumentNotes)){
                crrntDocumentNote.setContent(chars.toString());
                cvrfDocument.getDocumentNotes().addNote(crrntDocumentNote);
                crrntDocumentNote = null;
            }else if (qName.equalsIgnoreCase("AggregateSeverity")) {
                cvrfDocument.setAggregateSeverity(chars.toString());
            }else if (qName.equalsIgnoreCase("ProductTree")) {
                bProductTree = false;
            }else if (qName.equalsIgnoreCase("FullProductName") && bProductTree) {
                crrntFullProductName.setContent(chars.toString());
                cvrfDocument.getProductTree().addFullProductName(crrntFullProductName);
                crrntFullProductName = null;
            }else if (qName.equalsIgnoreCase("Vulnerability")) {
                bVulnerability = false;
                cvrfDocument.addVulnerability(crrntVulnerability);
                crrntVulnerability = null;
            }else if ((qName.equalsIgnoreCase("Title") && bVulnerability)) {
                crrntVulnerability.setTitle(chars.toString());
            }else if ((qName.equalsIgnoreCase("ID") && bVulnerability)) {
                crrntVulnerability.getID().setContent(chars.toString());
            }else if ((qName.equalsIgnoreCase("Notes") && bVulnerability)) {
                bVulnerabilityNotes = false;
            }else if ((qName.equalsIgnoreCase("Note") && bVulnerabilityNotes)) {
                crrntVulnerabilityNote.setContent(chars.toString());
                crrntVulnerability.getNotes().addNote(crrntVulnerabilityNote);
                crrntVulnerabilityNote = null;
            }else if ((qName.equalsIgnoreCase("CVE") && bVulnerability)) {
                crrntVulnerability.setCVE(chars.toString());
            }else if ((qName.equalsIgnoreCase("ProductStatuses") && bVulnerability)) {
                bVulnerabilityProductStatuses = false;
            }else if ((qName.equalsIgnoreCase("Status") && bVulnerabilityProductStatuses)) {
                bVulnerabilityProductstatusesStatus = false;
                crrntVulnerability.getProductStatuses().addStatus(crrntVulnerabilityStatus);
                crrntVulnerabilityStatus = null;
            }else if ((qName.equalsIgnoreCase("ProductID") && bVulnerabilityProductstatusesStatus)) {
                crrntVulnerabilityStatus.addProductID(chars.toString());
            }else if ((qName.equalsIgnoreCase("Threats") && bVulnerability)) {
                bVulnerabilityThreats = false;
            }else if ((qName.equalsIgnoreCase("Threat") && bVulnerabilityThreats)) {
                bVulnerabilityThreatsThreat = false;
                crrntVulnerability.getThreats().addThreat(crrntVulnerabilityThreat);
                crrntVulnerabilityThreat = null;
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerabilityThreatsThreat)) {
                crrntVulnerabilityThreat.setDescription(chars.toString());
            }else if ((qName.equalsIgnoreCase("Remediations") && bVulnerability)) {
                bVulnerabilityRemediations = false;
            }else if ((qName.equalsIgnoreCase("Remediation") && bVulnerabilityRemediations)) {
                bVulnerabilityRemediationsRemediation = false;
                crrntVulnerability.getRemediations().addRemediation(crrntVulnerabilityRemediation);
                crrntVulnerabilityRemediation = null;
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerabilityRemediationsRemediation)) {
                crrntVulnerabilityRemediation.setDescription(chars.toString());
            }else if ((qName.equalsIgnoreCase("URL") && bVulnerabilityRemediationsRemediation)) {
                crrntVulnerabilityRemediation.setURL(chars.toString());
            }
        }


    }

}
