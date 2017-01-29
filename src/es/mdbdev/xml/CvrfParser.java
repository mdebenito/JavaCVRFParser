package es.mdbdev.xml;

import es.mdbdev.model.cvrf.CVRFDocument;
import es.mdbdev.model.cvrf.Cvrfdoc;
import es.mdbdev.model.cvrf.documentnotes.DocumentNotes;
import es.mdbdev.model.cvrf.documentnotes.Note;
import es.mdbdev.model.cvrf.documentpublisher.DocumentPublisher;
import es.mdbdev.model.cvrf.documenttracking.*;
import es.mdbdev.model.cvrf.producttree.FullProductName;
import es.mdbdev.model.cvrf.producttree.ProductTree;
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
    private File xmlFile;
    private CVRFDocument result;

    public CvrfParser(String file){
        xmlFile = new File(file);
    }
    public CvrfParser(File file){
        xmlFile = file;
    }

    public Cvrfdoc parseCvrfFile() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        XMLHandler xmlHandler = new XMLHandler();
        saxParser.parse(xmlFile, xmlHandler);
        return xmlHandler.getCvrfDocument();
    }


    /**
     * Class in charge of handling the SAX parsing of the XML file into a NistUpdate object
     */
    class XMLHandler  extends DefaultHandler {
        private StringBuilder chars = new StringBuilder();

        private Cvrfdoc cvrfDocument;

        private Revision crrntRev = null;
        private Note crrntDocumentNote = null;
        private FullProductName crrntFullProductName = null;


        private boolean bDocType = false;
        private boolean bDocTitle = false;
        private boolean bContactDetails = false;
        private boolean bIssuingAuthority = false;
        private boolean bID = false;
        private boolean bAlias = false;
        private boolean bDocumentTrackingStatus = false;
        private boolean bDocumentTrackingVersion = false;
        private boolean bRevisionNumber = false;
        private boolean bRevisionDate = false;
        private boolean bRevisionDescription = false;
        private boolean bInitialReleaseDate = false;
        private boolean bCurrentReleaseDate = false;
        private boolean bDocumentNotes = false;
        private boolean bNote = false;
        private boolean bAggregateSeverity = false;
        private boolean bEngine = false;
        private boolean bFullProductName = true;

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
                cvrfDocument.setDocumentTracking(new DocumentTracking());
            }else if (qName.equalsIgnoreCase("Identification")
                    || qName.equalsIgnoreCase("cvrf:Identification")) {
                cvrfDocument.getDocumentTracking().setIdentification(new Identification());
            }else if (qName.equalsIgnoreCase("ID")
                    || qName.equalsIgnoreCase("cvrf:ID")) {
                bID = true;
            }else if (qName.equalsIgnoreCase("Alias")
                    || qName.equalsIgnoreCase("cvrf:Alias")) {
                bAlias = true;
            }else if (qName.equalsIgnoreCase("Status")
                    || qName.equalsIgnoreCase("cvrf:Status")) {
                bDocumentTrackingStatus = true;
            }else if (qName.equalsIgnoreCase("Version")
                    || qName.equalsIgnoreCase("cvrf:Version")) {
                bDocumentTrackingVersion = true;
            }else if (qName.equalsIgnoreCase("RevisionHistory")
                    || qName.equalsIgnoreCase("cvrf:RevisionHistory")) {
                cvrfDocument.getDocumentTracking().setRevisionHistory(new RevisionHistory());
            }else if (qName.equalsIgnoreCase("Revision")
                    || qName.equalsIgnoreCase("cvrf:Revision")) {
                crrntRev = new Revision();
            }else if (qName.equalsIgnoreCase("Number")
                    || qName.equalsIgnoreCase("cvrf:Number")) {
                bRevisionNumber = true;
            }else if (qName.equalsIgnoreCase("Date")
                    || qName.equalsIgnoreCase("cvrf:Date")) {
                bRevisionDate = true;
            }else if (qName.equalsIgnoreCase("Description")
                    || qName.equalsIgnoreCase("cvrf:Description")) {
                bRevisionDescription = true;
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
            }else if (bID) {
                cvrfDocument.getDocumentTracking().getIdentification().setID(value);
                bID = false;
            }else if (bAlias) {
                cvrfDocument.getDocumentTracking().getIdentification().setAlias(value);
                bAlias = false;
            }else if (bDocumentTrackingStatus) {
                cvrfDocument.getDocumentTracking().setStatus(value);
                bDocumentTrackingStatus = false;
            }else if (bDocumentTrackingVersion) {
                cvrfDocument.getDocumentTracking().setVersion(value);
                bDocumentTrackingVersion = false;
            }else if (bRevisionNumber) {
                crrntRev.setNumber(value);
                bRevisionNumber = false;
            }else if (bRevisionDate) {
                crrntRev.setDate(value);
                bRevisionDate = false;
            }else if (bRevisionDescription) {
                crrntRev.setDescription(value);
                bRevisionDescription = false;
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
            }else if (bAggregateSeverity) {
                cvrfDocument.setAggregateSeverity(value);
                bAggregateSeverity = false;
            }else if (bFullProductName) {
                crrntFullProductName.setContent(value);
            }

        }
        @Override
        public void endElement(String uri,
                               String localName, String qName) {
            if (qName.equalsIgnoreCase("cvrfdoc")
                    || qName.equalsIgnoreCase("cvrf:cvrfdoc")) {

            }else if (qName.equalsIgnoreCase("Revision")
                    || qName.equalsIgnoreCase("cvrf:Revision")) {
                cvrfDocument.getDocumentTracking().getRevisionHistory().addRevision(crrntRev);
                crrntRev = null;
            }else if (qName.equalsIgnoreCase("DocumentNotes")
                    || qName.equalsIgnoreCase("cvrf:DocumentNotes")) {
                bDocumentNotes = false;
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
            }
        }


    }

}
