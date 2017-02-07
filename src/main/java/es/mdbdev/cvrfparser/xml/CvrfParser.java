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

        private boolean bDocumentType = false;
        private boolean bDocumentTitle = false;
        private boolean bDocumentPublisherContactDetails = false;
        private boolean bDocumentPublisherIssuingAuthority = false;
        private boolean bID = false;
        private boolean bDocumentTrackingAlias = false;
        private boolean bDocumentTrackingStatus = false;
        private boolean bDocumentTrackingVersion = false;
        private boolean bDocumentTrackingRevisionHistoryRevisionNumber = false;
        private boolean bDocumentTrackingRevisionHistoryRevisionDate = false;
        private boolean bDocumentTrackingRevisionHistoryRevisionDescription = false;
        private boolean bDocumentTrackingInitialReleaseDate = false;
        private boolean bDocumentTrackingCurrentReleaseDate = false;
        private boolean bDocumentNotes = false;
        private boolean bDocumentNotesNote = false;
        private boolean bAggregateSeverity = false;
        private boolean bDocumentTrackingGeneratorEngine = false;
        private boolean bProductTreeFullProductName = false;
        private boolean bDocumentTrackingRevisionHistoryRevision = false;
        private boolean bVulnerability = false;
        private boolean bVulnerabilityTitle = false;
        private boolean bVulnerabilityNotes = false;
        private boolean bVulnerabilityNotesNote = false;
        private boolean bVulnerabilityCVE = false;
        private boolean bVulnerabilityProductStatuses = false;
        private boolean bVulnerabilityProductstatusesStatus = false;
        private boolean bVulnerabilityProductID = false;
        private boolean bVulnerabilityThreats = false;
        private boolean bVulnerabilityThreatsThreat = false;
        private boolean bVulnerabilityThreatDescription = false;
        private boolean bVulnerabilityRemediations = false;
        private boolean bVulnerabilityRemediationsRemediation = false;
        private boolean bVulnerabilityRemediationsRemediationDescription = false;
        private boolean bVulnerabilityRemediationsRemediationURL = false;
        private boolean bDocumentReferences = false;
        private boolean bDocumentReferencesReference = false;
        private boolean bDocumentReferenceURL = false;
        private boolean bDocumentReferenceDescription = false;
        private boolean bDocumentTracking = false;
        private boolean bDocumentTrackingIdentification = false;
        private boolean bDocumentTrackingIdentificationID = false;
        private boolean bVulnerabilityID = false;
        private boolean bDocumentTrackingRevisionHistory = false;
        private boolean bDocumentPublisher = false;
        private boolean bCvrfDoc = false;
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
                bCvrfDoc = true;
                this.cvrfDocument = new Cvrfdoc();
            } else if (qName.equalsIgnoreCase("DocumentTitle")) {
                bDocumentTitle = true;
            }else if (qName.equalsIgnoreCase("DocumentType")) {
                bDocumentType = true;
            }else if (qName.equalsIgnoreCase("DocumentPublisher")) {
                bDocumentPublisher = true;
                cvrfDocument.setDocumentPublisher(new DocumentPublisher());
            }else if ((qName.equalsIgnoreCase("ContactDetails") && bDocumentPublisher)){
                bDocumentPublisherContactDetails = true;
            }else if ((qName.equalsIgnoreCase("IssuingAuthority")) && bDocumentPublisher) {
                bDocumentPublisherIssuingAuthority = true;
            }else if (qName.equalsIgnoreCase("DocumentTracking")) {
                bDocumentTracking = true;
                cvrfDocument.setDocumentTracking(new DocumentTracking());
            }else if ((qName.equalsIgnoreCase("Identification")&&bDocumentTracking)) {
                bDocumentTrackingIdentification = true;
                cvrfDocument.getDocumentTracking().setIdentification(new Identification());
            }else if ((qName.equalsIgnoreCase("ID")&&bDocumentTrackingIdentification)) {
                bDocumentTrackingIdentificationID = true;
            }else if ((qName.equalsIgnoreCase("Alias")&&bDocumentTracking)) {
                bDocumentTrackingAlias = true;
            }else if ((qName.equalsIgnoreCase("Status")&&bDocumentTracking)) {
                bDocumentTrackingStatus = true;
            }else if ((qName.equalsIgnoreCase("Version")&&bDocumentTracking)) {
                bDocumentTrackingVersion = true;
            }else if ((qName.equalsIgnoreCase("RevisionHistory")&&bDocumentTracking)) {
                bDocumentTrackingRevisionHistory = true;
                cvrfDocument.getDocumentTracking().setRevisionHistory(new RevisionHistory());
            }else if ((qName.equalsIgnoreCase("Revision")&& bDocumentTrackingRevisionHistory)) {
                bDocumentTrackingRevisionHistoryRevision = true;
                crrntRev = new Revision();
            }else if ((qName.equalsIgnoreCase("Number") && bDocumentTrackingRevisionHistoryRevision)) {
                bDocumentTrackingRevisionHistoryRevisionNumber = true;
            }else if ((qName.equalsIgnoreCase("Date") && bDocumentTrackingRevisionHistoryRevision)) {
                bDocumentTrackingRevisionHistoryRevisionDate = true;
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentTrackingRevisionHistoryRevision)) {
                bDocumentTrackingRevisionHistoryRevisionDescription = true;
            }else if ((qName.equalsIgnoreCase("InitialReleaseDate")&&bDocumentTracking)) {
                bDocumentTrackingInitialReleaseDate = true;
            }else if ((qName.equalsIgnoreCase("CurrentReleaseDate")&&bDocumentTracking)) {
                bDocumentTrackingCurrentReleaseDate = true;
            }else if ((qName.equalsIgnoreCase("Generator")&&bDocumentTracking)) {
                bDocumentTrackingGenerator = true;
                cvrfDocument.getDocumentTracking().setGenerator(new Generator());
            }else if (qName.equalsIgnoreCase("Engine") && bDocumentTrackingGenerator) {
                bDocumentTrackingGeneratorEngine = true;
            }else if (qName.equalsIgnoreCase("DocumentReferences")) {
                bDocumentReferences = true;
                cvrfDocument.setDocumentReferences(new DocumentReferences());
            }else if ((qName.equalsIgnoreCase("Reference") && bDocumentReferences)) {
                bDocumentReferencesReference = true;
                crrntDocumentReference = new Reference();
                crrntDocumentReference.setType(attributes.getValue("Type"));
            }else if ((qName.equalsIgnoreCase("URL") && bDocumentReferencesReference)) {
                bDocumentReferenceURL = true;
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentReferencesReference)) {
                bDocumentReferenceDescription = true;
            }else if (qName.equalsIgnoreCase("DocumentNotes")) {
                bDocumentNotes = true;
                cvrfDocument.setDocumentNotes(new DocumentNotes());
            }else if ((qName.equalsIgnoreCase("Note") && bDocumentNotes)) {
                bDocumentNotesNote = true;
                crrntDocumentNote = new Note();
                crrntDocumentNote.setOrdinal(attributes.getValue("Ordinal"));
                crrntDocumentNote.setType(attributes.getValue("Type"));
                crrntDocumentNote.setAudience(attributes.getValue("Audience"));
                crrntDocumentNote.setTitle(attributes.getValue("Title"));
            }else if (qName.equalsIgnoreCase("AggregateSeverity")) {
                bAggregateSeverity = true;
            }else if (qName.equalsIgnoreCase("ProductTree")) {
                bProductTree = true;
                cvrfDocument.setProductTree(new ProductTree());
            }else if (qName.equalsIgnoreCase("FullProductName") && bProductTree) {//IGNORING BRANCHES FOR NOW
                bProductTreeFullProductName = true;
                crrntFullProductName = new FullProductName();
                crrntFullProductName.setProductID(attributes.getValue("ProductID"));
            }else if (qName.equalsIgnoreCase("Vulnerability")) {
                bVulnerability = true;
                crrntVulnerability = new Vulnerability();
                crrntVulnerability.setOrdinal(attributes.getValue("Ordinal"));
            }else if ((qName.equalsIgnoreCase("Title") && bVulnerability)) {
                bVulnerabilityTitle = true;
            }else if ((qName.equalsIgnoreCase("ID") && bVulnerability)) {
                bVulnerabilityID = true;
                crrntVulnerability.setID(new ID());
                crrntVulnerability.getID().setSystemName(attributes.getValue("SystemName"));
            }else if ((qName.equalsIgnoreCase("Notes") && bVulnerability)) {
                bVulnerabilityNotes = true;
                crrntVulnerability.setNotes(new Notes());
            }else if ((qName.equalsIgnoreCase("Note") && bVulnerabilityNotes)) {
                bVulnerabilityNotesNote = true;
                crrntVulnerabilityNote = new Note();
                crrntVulnerabilityNote.setOrdinal(attributes.getValue("Ordinal"));
                crrntVulnerabilityNote.setType(attributes.getValue("Type"));
                crrntVulnerabilityNote.setAudience(attributes.getValue("Audience"));
                crrntVulnerabilityNote.setTitle(attributes.getValue("Title"));
            }else if ((qName.equalsIgnoreCase("CVE") && bVulnerability)) {
                bVulnerabilityCVE = true;
            }else if ((qName.equalsIgnoreCase("ProductStatuses") && bVulnerability)) {
                bVulnerabilityProductStatuses = true;
                crrntVulnerability.setProductStatuses(new ProductStatuses());
            }else if ((qName.equalsIgnoreCase("Status") && bVulnerabilityProductStatuses)) {
                bVulnerabilityProductstatusesStatus = true;
                crrntVulnerabilityStatus = new Status();
                crrntVulnerabilityStatus.setType(attributes.getValue("Type"));
            }else if ((qName.equalsIgnoreCase("ProductID") && bVulnerabilityProductstatusesStatus)) {
                bVulnerabilityProductID = true;
            }else if ((qName.equalsIgnoreCase("Threats") && bVulnerability)) {
                bVulnerabilityThreats = true;
                crrntVulnerability.setThreats(new Threats());
            }else if ((qName.equalsIgnoreCase("Threat") && bVulnerabilityThreats)) {
                bVulnerabilityThreatsThreat = true;
                crrntVulnerabilityThreat = new Threat();
                crrntVulnerabilityThreat.setType(attributes.getValue("Type"));
                crrntVulnerabilityThreat.setDate(attributes.getValue("Date"));
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerabilityThreatsThreat)) {
                bVulnerabilityThreatDescription = true;
            }else if ((qName.equalsIgnoreCase("Remediations") && bVulnerability)) {
                bVulnerabilityRemediations = true;
                crrntVulnerability.setRemediations(new Remediations());
            }else if ((qName.equalsIgnoreCase("Remediation") && bVulnerabilityRemediations)) {
                bVulnerabilityRemediationsRemediation = true;
                crrntVulnerabilityRemediation = new Remediation();
                crrntVulnerabilityRemediation.setType(attributes.getValue("Type"));
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerabilityRemediationsRemediation)) {
                bVulnerabilityRemediationsRemediationDescription = true;
            }else if ((qName.equalsIgnoreCase("URL") && bVulnerabilityRemediationsRemediation)) {
                bVulnerabilityRemediationsRemediationURL = true;
            }
        }

        @Override
        public void characters(char ch[],
                               int start, int length) {
            String value = new String(ch, start, length);
            if (bDocumentTitle) {
                cvrfDocument.setDocumentTitle(value);
            }if (bDocumentType) {
                cvrfDocument.setDocumentType(value);
            }else if (bDocumentPublisherContactDetails) {
                cvrfDocument.getDocumentPublisher().setContactDetails(value);
            }else if (bDocumentPublisherIssuingAuthority) {
                cvrfDocument.getDocumentPublisher().setIssuingAuthority(value);
            }else if (bDocumentTrackingIdentificationID) {
                cvrfDocument.getDocumentTracking().getIdentification().setID(value);
            }else if (bDocumentTrackingAlias) {
                cvrfDocument.getDocumentTracking().getIdentification().setAlias(value);
            }else if (bDocumentTrackingStatus) {
                cvrfDocument.getDocumentTracking().setStatus(value);
            }else if (bDocumentTrackingVersion) {
                cvrfDocument.getDocumentTracking().setVersion(value);
            }else if (bDocumentTrackingRevisionHistoryRevisionNumber) {
                crrntRev.setNumber(value);
            }else if (bDocumentTrackingRevisionHistoryRevisionDate) {
                crrntRev.setDate(value);
            }else if (bDocumentTrackingRevisionHistoryRevisionDescription) {
                crrntRev.setDescription(value);
            }else if (bDocumentTrackingInitialReleaseDate) {
                cvrfDocument.getDocumentTracking().setInitialReleaseDate(value);
            }else if (bDocumentTrackingCurrentReleaseDate) {
                cvrfDocument.getDocumentTracking().setCurrentReleaseDate(value);
            }else if (bDocumentTrackingGeneratorEngine) {
                cvrfDocument.getDocumentTracking().getGenerator().setEngine(value);
            }else if (bDocumentNotesNote && bDocumentNotes) {
                crrntDocumentNote.setContent(value);
            }else if (bDocumentReferenceURL) {
                crrntDocumentReference.setURL(value);
            }else if (bDocumentReferenceDescription) {
                crrntDocumentReference.setDescription(value);
            }else if (bAggregateSeverity) {
                cvrfDocument.setAggregateSeverity(value);
            }else if (bProductTreeFullProductName) {
                crrntFullProductName.setContent(value);
            }else if (bVulnerabilityTitle) {
                crrntVulnerability.setTitle(value);
            }else if (bVulnerabilityNotesNote) {
                crrntVulnerabilityNote.setContent(value);
            }else if (bVulnerabilityCVE) {
                crrntVulnerability.setCVE(value);
            }else if (bVulnerabilityID) {
                crrntVulnerability.getID().setContent(value);
            }else if (bVulnerabilityProductID) {
                crrntVulnerabilityStatus.addProductID(value);
            }else if (bVulnerabilityThreatDescription) {
                crrntVulnerabilityThreat.setDescription(value);
            }else if (bVulnerabilityRemediationsRemediationDescription) {
                crrntVulnerabilityRemediation.setDescription(value);
            }else if (bVulnerabilityRemediationsRemediationURL) {
                crrntVulnerabilityRemediation.setURL(value);
            }

        }
        @Override
        public void endElement(String uri,
                               String localName, String qName) {
            String[] tmp = qName.split(":");
            if(tmp.length>1)
                qName = tmp[1];
            else
                qName = tmp[0];


            if (qName.equalsIgnoreCase("cvrfdoc")) {
                bCvrfDoc = false;
            }else if (qName.equalsIgnoreCase("DocumentTitle")) {
                bDocumentTitle = false;
            }else if (qName.equalsIgnoreCase("DocumentType")) {
                bDocumentType = false;
            }else if (qName.equalsIgnoreCase("DocumentPublisher")) {
                bDocumentPublisher = false;
            }else if ((qName.equalsIgnoreCase("ContactDetails") && bDocumentPublisher)) {
                bDocumentPublisherContactDetails = false;
            }else if ((qName.equalsIgnoreCase("IssuingAuthority")) && bDocumentPublisher) {
                bDocumentPublisherIssuingAuthority = false;
            }else if (qName.equalsIgnoreCase("DocumentTracking")) {
                bDocumentTracking = false;
            }else if ((qName.equalsIgnoreCase("Identification")&&bDocumentTracking)) {
                bDocumentTrackingIdentification = false;
            }else if ((qName.equalsIgnoreCase("ID")&&bDocumentTrackingIdentification)) {
                bDocumentTrackingIdentificationID = false;
            }else if ((qName.equalsIgnoreCase("Alias")&&bDocumentTracking)) {
                bDocumentTrackingAlias = false;
            }else if ((qName.equalsIgnoreCase("Status")&&bDocumentTracking)) {
                bDocumentTrackingStatus = false;
            }else if ((qName.equalsIgnoreCase("Version")&&bDocumentTracking)) {
                bDocumentTrackingVersion = false;
            }else if ((qName.equalsIgnoreCase("InitialReleaseDate")&&bDocumentTracking)) {
                bDocumentTrackingInitialReleaseDate = false;
            }else if ((qName.equalsIgnoreCase("CurrentReleaseDate")&&bDocumentTracking)) {
                bDocumentTrackingCurrentReleaseDate = false;
            }else if ((qName.equalsIgnoreCase("Generator")&&bDocumentTracking)) {
                bDocumentTrackingGenerator = false;
            }else if (qName.equalsIgnoreCase("Engine") && bDocumentTrackingGenerator) {
                bDocumentTrackingGeneratorEngine = false;
            }else if ((qName.equalsIgnoreCase("RevisionHistory")&&bDocumentTracking)) {
                bDocumentTrackingRevisionHistory = false;
            }else if ((qName.equalsIgnoreCase("Revision")&& bDocumentTrackingRevisionHistory)) {
                cvrfDocument.getDocumentTracking().getRevisionHistory().addRevision(crrntRev);
                crrntRev = null;
                bDocumentTrackingRevisionHistoryRevision = false;
            }else if ((qName.equalsIgnoreCase("Number") && bDocumentTrackingRevisionHistoryRevision)) {
                bDocumentTrackingRevisionHistoryRevisionNumber = false;
            }else if ((qName.equalsIgnoreCase("Date") && bDocumentTrackingRevisionHistoryRevision)) {
                bDocumentTrackingRevisionHistoryRevisionDate = false;
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentTrackingRevisionHistoryRevision)) {
                bDocumentTrackingRevisionHistoryRevisionDescription = false;
            }else if (qName.equalsIgnoreCase("DocumentReferences")) {
                bDocumentReferences = false;
            }else if ((qName.equalsIgnoreCase("Reference") && bDocumentReferences)) {
                bDocumentReferencesReference = false;
                cvrfDocument.getDocumentReferences().addReference(crrntDocumentReference);
                crrntDocumentReference = null;
            }else if ((qName.equalsIgnoreCase("URL") && bDocumentReferencesReference)) {
                bDocumentReferenceURL = false;
            }else if ((qName.equalsIgnoreCase("Description") && bDocumentReferencesReference)) {
                bDocumentReferenceDescription = false;
            }else if (qName.equalsIgnoreCase("DocumentNotes")) {
                bDocumentNotes = false;
            }else if ((qName.equalsIgnoreCase("Note") && bDocumentNotes)){
                cvrfDocument.getDocumentNotes().addNote(crrntDocumentNote);
                crrntDocumentNote = null;
                bDocumentNotesNote = false;
            }else if (qName.equalsIgnoreCase("AggregateSeverity")) {
                bAggregateSeverity = false;
            }else if (qName.equalsIgnoreCase("ProductTree")) {
                bProductTree = false;
            }else if (qName.equalsIgnoreCase("FullProductName") && bProductTree) {
                bProductTreeFullProductName = false;
                cvrfDocument.getProductTree().addFullProductName(crrntFullProductName);
                crrntFullProductName = null;
            }else if (qName.equalsIgnoreCase("Vulnerability")) {
                bVulnerability = false;
                cvrfDocument.addVulnerability(crrntVulnerability);
                crrntVulnerability = null;
            }else if ((qName.equalsIgnoreCase("Title") && bVulnerability)) {
                bVulnerabilityTitle = false;
            }else if ((qName.equalsIgnoreCase("ID") && bVulnerability)) {
                bVulnerabilityID = false;
            }else if ((qName.equalsIgnoreCase("Notes") && bVulnerability)) {
                bVulnerabilityNotes = false;
            }else if ((qName.equalsIgnoreCase("Note") && bVulnerabilityNotes)) {
                bVulnerabilityNotesNote = false;
                crrntVulnerability.getNotes().addNote(crrntVulnerabilityNote);
                crrntVulnerabilityNote = null;
            }else if ((qName.equalsIgnoreCase("CVE") && bVulnerability)) {
                bVulnerabilityCVE = false;
            }else if ((qName.equalsIgnoreCase("ProductStatuses") && bVulnerability)) {
                bVulnerabilityProductStatuses = false;
            }else if ((qName.equalsIgnoreCase("Status") && bVulnerabilityProductStatuses)) {
                bVulnerabilityProductstatusesStatus = false;
                crrntVulnerability.getProductStatuses().addStatus(crrntVulnerabilityStatus);
                crrntVulnerabilityStatus = null;
            }else if ((qName.equalsIgnoreCase("ProductID") && bVulnerabilityProductstatusesStatus)) {
                bVulnerabilityProductID = false;
            }else if ((qName.equalsIgnoreCase("Threats") && bVulnerability)) {
                bVulnerabilityThreats = false;
            }else if ((qName.equalsIgnoreCase("Threat") && bVulnerabilityThreats)) {
                bVulnerabilityThreatsThreat = false;
                crrntVulnerability.getThreats().addThreat(crrntVulnerabilityThreat);
                crrntVulnerabilityThreat = null;
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerabilityThreatsThreat)) {
                bVulnerabilityThreatDescription = false;
            }else if ((qName.equalsIgnoreCase("Remediations") && bVulnerability)) {
                bVulnerabilityRemediations = false;
            }else if ((qName.equalsIgnoreCase("Remediation") && bVulnerabilityRemediations)) {
                bVulnerabilityRemediationsRemediation = false;
                crrntVulnerability.getRemediations().addRemediation(crrntVulnerabilityRemediation);
                crrntVulnerabilityRemediation = null;
            }else if ((qName.equalsIgnoreCase("Description") && bVulnerabilityRemediationsRemediation)) {
                bVulnerabilityRemediationsRemediationDescription = false;
            }else if ((qName.equalsIgnoreCase("URL") && bVulnerabilityRemediationsRemediation)) {
                bVulnerabilityRemediationsRemediationURL = false;
            }
        }


    }

}
