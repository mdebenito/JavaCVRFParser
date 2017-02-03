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
    private File xmlFile;

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
        private StringBuilder chars = new StringBuilder();

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

        private boolean bDocType = false;
        private boolean bDocTitle = false;
        private boolean bContactDetails = false;
        private boolean bIssuingAuthority = false;
        private boolean bID = false;
        private boolean bDocumentTrackingAlias = false;
        private boolean bDocumentTrackingStatus = false;
        private boolean bDocumentTrackingVersion = false;
        private boolean bDocumentTrackingRevisionNumber = false;
        private boolean bDocumentTrackingRevisionDate = false;
        private boolean bDocumentTrackingRevisionDescription = false;
        private boolean bInitialReleaseDate = false;
        private boolean bCurrentReleaseDate = false;
        private boolean bDocumentNotes = false;
        private boolean bNote = false;
        private boolean bAggregateSeverity = false;
        private boolean bEngine = false;
        private boolean bFullProductName = false;
        private boolean bDocumentTrackingRevision = false;
        private boolean bVulnerability = false;
        private boolean bVulnerabilityTitle = false;
        private boolean bVulnerabilityNotes = false;
        private boolean bVulnerabilityNote = false;
        private boolean bVulnerabilityCVE = false;
        private boolean bVulnerabilityProductStatuses = false;
        private boolean bVulnerabilityStatus = false;
        private boolean bVulnerabilityProductID = false;
        private boolean bVulnerabilityThreats = false;
        private boolean bVulnerabilityThreat = false;
        private boolean bVulnerabilityThreatDescription = false;
        private boolean bVulnerabilityRemediations = false;
        private boolean bVulnerabilityRemediation = false;
        private boolean bVulnerabilityRemediationDescription = false;
        private boolean bVulnerabilityRemediationURL = false;
        private boolean bDocumentReferences = false;
        private boolean bDocumentReference = false;
        private boolean bDocumentReferenceURL = false;
        private boolean bDocumentReferenceDescription = false;
        private boolean bDocumentTracking = false;
        private boolean bDocumentTrackingIdentification = false;
        private boolean bDocumentTrackingID = false;
        private boolean bVulnerabilityID = false;
        private boolean bDocumentTrackingRevisionHistory = false;


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
            if (qName.equalsIgnoreCase("cvrfdoc")
                    || qName.equalsIgnoreCase("cvrf:cvrfdoc")) {
                this.cvrfDocument = new Cvrfdoc();
            } else if (qName.equalsIgnoreCase("DocumentTitle")
                    || qName.equalsIgnoreCase("cvrf:DocumentTitle")) {
                bDocTitle = true;
            }else if (qName.equalsIgnoreCase("DocumentType")
                    || qName.equalsIgnoreCase("cvrf:DocumentType")) {
                bDocType = true;
            }  else if (qName.equalsIgnoreCase("DocumentPublisher")
                    || qName.equalsIgnoreCase("cvrf:DocumentPublisher")) {
                cvrfDocument.setDocumentPublisher(new DocumentPublisher());
            }else if (qName.equalsIgnoreCase("ContactDetails")
                    || qName.equalsIgnoreCase("cvrf:ContactDetails")) {
                bContactDetails = true;
            }else if (qName.equalsIgnoreCase("IssuingAuthority")
                    || qName.equalsIgnoreCase("cvrf:IssuingAuthority")) {
                bIssuingAuthority = true;
            }else if (qName.equalsIgnoreCase("DocumentTracking")
                    || qName.equalsIgnoreCase("cvrf:DocumentTracking")) {
                bDocumentTracking = true;
                cvrfDocument.setDocumentTracking(new DocumentTracking());
            }else if ((qName.equalsIgnoreCase("Identification")&&bDocumentTracking)
                    || (qName.equalsIgnoreCase("cvrf:Identification")&&bDocumentTracking)) {
                cvrfDocument.getDocumentTracking().setIdentification(new Identification());
                bDocumentTrackingIdentification = true;
            }else if ((qName.equalsIgnoreCase("ID")&&bDocumentTrackingIdentification)
                    || (qName.equalsIgnoreCase("cvrf:ID") && bDocumentTrackingIdentification)) {
                bDocumentTrackingID = true;
            }else if ((qName.equalsIgnoreCase("Alias")&&bDocumentTracking)
                    || (qName.equalsIgnoreCase("cvrf:Alias")&&bDocumentTracking)) {
                bDocumentTrackingAlias = true;
            }else if ((qName.equalsIgnoreCase("Status")&&bDocumentTracking)
                    || (qName.equalsIgnoreCase("cvrf:Status")&&bDocumentTracking)) {
                bDocumentTrackingStatus = true;
            }else if ((qName.equalsIgnoreCase("Version")&&bDocumentTracking)
                    || (qName.equalsIgnoreCase("cvrf:Version")&&bDocumentTracking)) {
                bDocumentTrackingVersion = true;
            }else if ((qName.equalsIgnoreCase("RevisionHistory")&&bDocumentTracking)
                    || (qName.equalsIgnoreCase("cvrf:RevisionHistory")&&bDocumentTracking)) {
                cvrfDocument.getDocumentTracking().setRevisionHistory(new RevisionHistory());
                bDocumentTrackingRevisionHistory = true;
            }else if ((qName.equalsIgnoreCase("Revision")&& bDocumentTrackingRevisionHistory)
                    || (qName.equalsIgnoreCase("cvrf:Revision")&& bDocumentTrackingRevisionHistory)) {
                crrntRev = new Revision();
                bDocumentTrackingRevision = true;
            }else if ((qName.equalsIgnoreCase("Number") && bDocumentTrackingRevision)
                    || (qName.equalsIgnoreCase("cvrf:Number") && bDocumentTrackingRevision)) {
                bDocumentTrackingRevisionNumber = true;
            }else if ((qName.equalsIgnoreCase("Date") && bDocumentTrackingRevision)
                    || (qName.equalsIgnoreCase("cvrf:Date") && bDocumentTrackingRevision)) {
                bDocumentTrackingRevisionDate = true;
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentTrackingRevision)
                    || (qName.equalsIgnoreCase("cvrf:Description") && bDocumentTrackingRevision)) {
                bDocumentTrackingRevisionDescription = true;
            }else if (qName.equalsIgnoreCase("InitialReleaseDate")
                    || qName.equalsIgnoreCase("cvrf:InitialReleaseDate")) {
                bInitialReleaseDate = true;
            }else if (qName.equalsIgnoreCase("CurrentReleaseDate")
                    || qName.equalsIgnoreCase("cvrf:CurrentReleaseDate")) {
                bCurrentReleaseDate = true;
            }else if (qName.equalsIgnoreCase("Generator")
                    || qName.equalsIgnoreCase("cvrf:Generator")) {
                cvrfDocument.getDocumentTracking().setGenerator(new Generator());
            }else if (qName.equalsIgnoreCase("Engine")
                    || qName.equalsIgnoreCase("cvrf:Engine")) {
                bEngine = true;
            }else if (qName.equalsIgnoreCase("DocumentNotes")
                    || qName.equalsIgnoreCase("cvrf:DocumentNotes")) {
                bDocumentNotes = true;
                cvrfDocument.setDocumentNotes(new DocumentNotes());
            }else if ((qName.equalsIgnoreCase("Note") && bDocumentNotes)
                    || (qName.equalsIgnoreCase("cvrf:Note")  && bDocumentNotes)) {
                bNote = true;
                crrntDocumentNote = new Note();
                crrntDocumentNote.setOrdinal(attributes.getValue("Ordinal"));
                crrntDocumentNote.setType(attributes.getValue("Type"));
                crrntDocumentNote.setAudience(attributes.getValue("Audience"));
                crrntDocumentNote.setTitle(attributes.getValue("Title"));
            }else if (qName.equalsIgnoreCase("DocumentReferences")
                    || qName.equalsIgnoreCase("cvrf:DocumentReferences")) {
                bDocumentReferences = true;
                cvrfDocument.setDocumentReferences(new DocumentReferences());
            }else if ((qName.equalsIgnoreCase("Reference") && bDocumentReferences)
                    || (qName.equalsIgnoreCase("cvrf:Reference") && bDocumentReferences)) {
                bDocumentReference = true;
                crrntDocumentReference = new Reference();
                crrntDocumentReference.setType(attributes.getValue("Type"));
            }else if ((qName.equalsIgnoreCase("URL") && bDocumentReference)
                    || (qName.equalsIgnoreCase("cvrf:URL") && bDocumentReference)) {
                bDocumentReferenceURL = true;
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentReference)
                    || (qName.equalsIgnoreCase("cvrf:Description") && bDocumentReference)) {
                bDocumentReferenceDescription = true;
            }else if (qName.equalsIgnoreCase("AggregateSeverity")
                    || qName.equalsIgnoreCase("cvrf:AggregateSeverity")) {
                bAggregateSeverity = true;
            }else if (qName.equalsIgnoreCase("ProductTree")
                    || qName.equalsIgnoreCase("prod:ProductTree")) {
                cvrfDocument.setProductTree(new ProductTree());
            }else if (qName.equalsIgnoreCase("FullProductName") //IGNORING BRANCHES FOR NOW
                    || qName.equalsIgnoreCase("prod:FullProductName")) {
                bFullProductName = true;
                crrntFullProductName = new FullProductName();
                crrntFullProductName.setProductID(attributes.getValue("ProductID"));
            }else if (qName.equalsIgnoreCase("Vulnerability")
                    || qName.equalsIgnoreCase("vuln:Vulnerability")) {
                bVulnerability = true;
                crrntVulnerability = new Vulnerability();
                crrntVulnerability.setOrdinal(attributes.getValue("Ordinal"));
            }else if ((qName.equalsIgnoreCase("Title") && bVulnerability)
                    || (qName.equalsIgnoreCase("vuln:Title") && bVulnerability)) {
                bVulnerabilityTitle = true;
            }else if ((qName.equalsIgnoreCase("ID") && bVulnerability)
                    || (qName.equalsIgnoreCase("vuln:ID") && bVulnerability)) {
                bVulnerabilityID = true;
                crrntVulnerability.setID(new ID());
                crrntVulnerability.getID().setSystemName(attributes.getValue("SystemName"));
            }else if ((qName.equalsIgnoreCase("Notes") && bVulnerability)
                    || (qName.equalsIgnoreCase("vuln:Notes") && bVulnerability)) {
                bVulnerabilityNotes = true;
                crrntVulnerability.setNotes(new Notes());
            }else if ((qName.equalsIgnoreCase("Note") && bVulnerability && bVulnerabilityNotes)
                    || (qName.equalsIgnoreCase("vuln:Note") && bVulnerability && bVulnerabilityNotes)) {
                bVulnerabilityNote = true;
                crrntVulnerabilityNote = new Note();
                crrntVulnerabilityNote.setOrdinal(attributes.getValue("Ordinal"));
                crrntVulnerabilityNote.setType(attributes.getValue("Type"));
                crrntVulnerabilityNote.setAudience(attributes.getValue("Audience"));
                crrntVulnerabilityNote.setTitle(attributes.getValue("Title"));

            }else if ((qName.equalsIgnoreCase("CVE") && bVulnerability)
                    || qName.equalsIgnoreCase("vuln:CVE")) {
                bVulnerabilityCVE = true;
            }else if ((qName.equalsIgnoreCase("ProductStatuses") && bVulnerability)
                    || qName.equalsIgnoreCase("vuln:ProductStatuses")) {
                bVulnerabilityProductStatuses = true;
                crrntVulnerability.setProductStatuses(new ProductStatuses());
            }else if ((qName.equalsIgnoreCase("Status") && bVulnerability && bVulnerabilityProductStatuses)
                    || (qName.equalsIgnoreCase("vuln:Status") && bVulnerability && bVulnerabilityProductStatuses)) {
                bVulnerabilityStatus = true;
                crrntVulnerabilityStatus = new Status();
                crrntVulnerabilityStatus.setType(attributes.getValue("Type"));
            }else if ((qName.equalsIgnoreCase("ProductID") && bVulnerability && bVulnerabilityProductStatuses && bVulnerabilityStatus)
                    || (qName.equalsIgnoreCase("vuln:ProductID") && bVulnerability && bVulnerabilityProductStatuses && bVulnerabilityStatus)) {
                bVulnerabilityProductID = true;
            }else if ((qName.equalsIgnoreCase("Threats") && bVulnerability)
                    || (qName.equalsIgnoreCase("vuln:Threats") && bVulnerability)) {
                bVulnerabilityThreats = true;
                crrntVulnerability.setThreats(new Threats());
            }else if ((qName.equalsIgnoreCase("Threat") && bVulnerability && bVulnerabilityThreats)
                    || (qName.equalsIgnoreCase("vuln:Threat") && bVulnerability && bVulnerabilityThreats)) {
                bVulnerabilityThreat = true;
                crrntVulnerabilityThreat = new Threat();
                crrntVulnerabilityThreat.setType(attributes.getValue("Type"));
                crrntVulnerabilityThreat.setDate(attributes.getValue("Date"));
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerability && bVulnerabilityThreat)
                    || (qName.equalsIgnoreCase("vuln:Description") && bVulnerability && bVulnerabilityThreat)) {
                bVulnerabilityThreatDescription = true;
            }else if ((qName.equalsIgnoreCase("Remediations") && bVulnerability)
                    || qName.equalsIgnoreCase("vuln:Remediations")) {
                bVulnerabilityRemediations = true;
                crrntVulnerability.setRemediations(new Remediations());
            }else if ((qName.equalsIgnoreCase("Remediation") && bVulnerability && bVulnerabilityRemediations)
                    || (qName.equalsIgnoreCase("vuln:Remediation") && bVulnerability && bVulnerabilityRemediations)) {
                bVulnerabilityRemediation = true;
                crrntVulnerabilityRemediation = new Remediation();
                crrntVulnerabilityRemediation.setType(attributes.getValue("Type"));
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerability && bVulnerabilityRemediations && bVulnerabilityRemediation)
                    || (qName.equalsIgnoreCase("vuln:Description") && bVulnerability && bVulnerabilityRemediations && bVulnerabilityRemediation)) {
                bVulnerabilityRemediationDescription = true;
            }else if ((qName.equalsIgnoreCase("URL") && bVulnerability && bVulnerabilityRemediations && bVulnerabilityRemediation)
                    || (qName.equalsIgnoreCase("vuln:URL") && bVulnerability && bVulnerabilityRemediations && bVulnerabilityRemediation)) {
                bVulnerabilityRemediationURL = true;
            }
        }

        @Override
        public void characters(char ch[],
                               int start, int length) {
            String value = new String(ch, start, length);
            if (bDocTitle) {
                cvrfDocument.setDocumentTitle(value);
                bDocTitle = false;
            }if (bDocType) {
                cvrfDocument.setDocumentType(value);
                bDocType = false;
            }else if (bContactDetails) {
                cvrfDocument.getDocumentPublisher().setContactDetails(value);
                bContactDetails = false;
            }else if (bIssuingAuthority) {
                cvrfDocument.getDocumentPublisher().setIssuingAuthority(value);
                bIssuingAuthority = false;
            }else if (bDocumentTrackingID) {
                cvrfDocument.getDocumentTracking().getIdentification().setID(value);
                bDocumentTrackingID = false;
            }else if (bDocumentTrackingAlias) {
                cvrfDocument.getDocumentTracking().getIdentification().setAlias(value);
                bDocumentTrackingAlias = false;
            }else if (bDocumentTrackingStatus) {
                cvrfDocument.getDocumentTracking().setStatus(value);
                bDocumentTrackingStatus = false;
            }else if (bDocumentTrackingVersion) {
                cvrfDocument.getDocumentTracking().setVersion(value);
                bDocumentTrackingVersion = false;
            }else if (bDocumentTrackingRevisionNumber) {
                crrntRev.setNumber(value);
                bDocumentTrackingRevisionNumber = false;
            }else if (bDocumentTrackingRevisionDate) {
                crrntRev.setDate(value);
                bDocumentTrackingRevisionDate = false;
            }else if (bDocumentTrackingRevisionDescription) {
                crrntRev.setDescription(value);
                bDocumentTrackingRevisionDescription = false;
            }else if (bInitialReleaseDate) {
                cvrfDocument.getDocumentTracking().setInitialReleaseDate(value);
                bInitialReleaseDate = false;
            }else if (bCurrentReleaseDate) {
                cvrfDocument.getDocumentTracking().setCurrentReleaseDate(value);
                bCurrentReleaseDate = false;
            }else if (bEngine) {
                cvrfDocument.getDocumentTracking().getGenerator().setEngine(value);
                bEngine = false;
            }else if (bNote && bDocumentNotes) {
                crrntDocumentNote.setContent(value);
                bNote = false;
            }else if (bDocumentReferenceURL) {
                crrntDocumentReference.setURL(value);
                bDocumentReferenceURL = false;
            }else if (bDocumentReferenceDescription) {
                crrntDocumentReference.setDescription(value);
                bDocumentReferenceDescription = false;
            }else if (bAggregateSeverity) {
                cvrfDocument.setAggregateSeverity(value);
                bAggregateSeverity = false;
            }else if (bFullProductName) {
                crrntFullProductName.setContent(value);
                bFullProductName = false;
            }else if (bVulnerabilityTitle) {
                crrntVulnerability.setTitle(value);
                bVulnerabilityTitle = false;
            }else if (bVulnerabilityNote) {
                crrntVulnerabilityNote.setContent(value);
                bVulnerabilityNote = false;
            }else if (bVulnerabilityCVE) {
                crrntVulnerability.setCVE(value);
                bVulnerabilityCVE = false;
            }else if (bVulnerabilityID) {
                crrntVulnerability.getID().setContent(value);
                bVulnerabilityID = false;
            }else if (bVulnerabilityProductID) {
                crrntVulnerabilityStatus.addProductID(value);
                bVulnerabilityProductID = false;
            }else if (bVulnerabilityThreatDescription) {
                crrntVulnerabilityThreat.setDescription(value);
                bVulnerabilityThreatDescription = false;
            }else if (bVulnerabilityRemediationDescription) {
                crrntVulnerabilityRemediation.setDescription(value);
                bVulnerabilityRemediationDescription = false;
            }else if (bVulnerabilityRemediationURL) {
                crrntVulnerabilityRemediation.setURL(value);
                bVulnerabilityRemediationURL = false;
            }

        }
        @Override
        public void endElement(String uri,
                               String localName, String qName) {
            if (qName.equalsIgnoreCase("cvrfdoc")
                    || qName.equalsIgnoreCase("cvrf:cvrfdoc")) {

            }else if (qName.equalsIgnoreCase("DocumentNotes")
                    || qName.equalsIgnoreCase("cvrf:DocumentNotes")) {
                bDocumentNotes = false;
            }else if ((qName.equalsIgnoreCase("Revision")&& bDocumentTrackingRevisionHistory)
                    || (qName.equalsIgnoreCase("cvrf:Revision")&& bDocumentTrackingRevisionHistory)) {
                cvrfDocument.getDocumentTracking().getRevisionHistory().addRevision(crrntRev);
                crrntRev = null;
                bDocumentTrackingRevision = false;
            }else if (qName.equalsIgnoreCase("DocumentTracking")
                    || qName.equalsIgnoreCase("cvrf:DocumentTracking")) {
                bDocumentTracking = false;
            }else if ((qName.equalsIgnoreCase("Identification")&&bDocumentTracking)
                    || (qName.equalsIgnoreCase("cvrf:Identification")&&bDocumentTracking)) {
                bDocumentTrackingIdentification = false;
            }else if ((qName.equalsIgnoreCase("RevisionHistory")&&bDocumentTracking)
                    || (qName.equalsIgnoreCase("cvrf:RevisionHistory")&&bDocumentTracking)) {
                bDocumentTrackingRevisionHistory = false;
            }else if ((qName.equalsIgnoreCase("ID")&&bDocumentTrackingIdentification)
                    || (qName.equalsIgnoreCase("cvrf:ID") && bDocumentTrackingIdentification)) {
                bDocumentTrackingID = false;
            }else if (qName.equalsIgnoreCase("Alias")
                    || qName.equalsIgnoreCase("cvrf:Alias")) {
                bDocumentTrackingAlias = false;
            }else if (qName.equalsIgnoreCase("Status")
                    || qName.equalsIgnoreCase("cvrf:Status")) {
                bDocumentTrackingStatus = false;
            }else if (qName.equalsIgnoreCase("Version")
                    || qName.equalsIgnoreCase("cvrf:Version")) {
                bDocumentTrackingVersion = false;
            }else if (qName.equalsIgnoreCase("DocumentReferences")
                    || qName.equalsIgnoreCase("cvrf:DocumentReferences")) {
                bDocumentReferences = false;
            }else if ((qName.equalsIgnoreCase("Reference") && bDocumentReferences)
                    || (qName.equalsIgnoreCase("cvrf:Reference") && bDocumentReferences)) {
                bDocumentReference = false;
                cvrfDocument.getDocumentReferences().addReference(crrntDocumentReference);
                crrntDocumentReference = null;
            }else if ((qName.equalsIgnoreCase("Note") && bDocumentNotes)
                    || (qName.equalsIgnoreCase("cvrf:Note")  && bDocumentNotes)){
                cvrfDocument.getDocumentNotes().addNote(crrntDocumentNote);
                crrntDocumentNote = null;
                bNote = false;
            }else if (qName.equalsIgnoreCase("FullProductName")
                    || qName.equalsIgnoreCase("prod:FullProductName")) {
                bFullProductName = false;
                cvrfDocument.getProductTree().addFullProductName(crrntFullProductName);
                crrntFullProductName = null;
            }else if (qName.equalsIgnoreCase("Vulnerability")
                    || qName.equalsIgnoreCase("vuln:Vulnerability")) {
                bVulnerability = false;
                cvrfDocument.addVulnerability(crrntVulnerability);
                crrntVulnerability = null;
            }else if ((qName.equalsIgnoreCase("Notes") && bVulnerability)
                    || qName.equalsIgnoreCase("vuln:Notes")) {
                bVulnerabilityNotes = false;
            }else if ((qName.equalsIgnoreCase("Note") && bVulnerability && bVulnerabilityNotes)
                    || qName.equalsIgnoreCase("vuln:Note")) {
                bVulnerabilityNote = false;
                crrntVulnerability.getNotes().addNote(crrntVulnerabilityNote);
                crrntVulnerabilityNote = null;
            }else if ((qName.equalsIgnoreCase("Status") && bVulnerability && bVulnerabilityProductStatuses)
                    || qName.equalsIgnoreCase("vuln:Status")) {
                bVulnerabilityStatus = false;
                crrntVulnerability.getProductStatuses().addStatus(crrntVulnerabilityStatus);
                crrntVulnerabilityStatus = null;
            }else if ((qName.equalsIgnoreCase("Threats") && bVulnerability)
                    || qName.equalsIgnoreCase("vuln:Threats")) {
                bVulnerabilityThreats = false;
            }else if ((qName.equalsIgnoreCase("Threat") && bVulnerability && bVulnerabilityThreats)
                    || qName.equalsIgnoreCase("vuln:Threat")) {
                bVulnerabilityThreat = false;
                crrntVulnerability.getThreats().addThreat(crrntVulnerabilityThreat);
                crrntVulnerabilityThreat = null;
            }else if ((qName.equalsIgnoreCase("Remediations") && bVulnerability)
                    || qName.equalsIgnoreCase("vuln:Remediations")) {
                bVulnerabilityRemediations = false;
            }else if ((qName.equalsIgnoreCase("Remediation") && bVulnerability && bVulnerabilityRemediations)
                    || qName.equalsIgnoreCase("vuln:Remediation")) {
                bVulnerabilityRemediation = false;
                crrntVulnerability.getRemediations().addRemediation(crrntVulnerabilityRemediation);
                crrntVulnerabilityRemediation = null;

            }
        }


    }

}
