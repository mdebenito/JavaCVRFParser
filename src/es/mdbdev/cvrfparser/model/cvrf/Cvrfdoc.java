package es.mdbdev.cvrfparser.model.cvrf;

import es.mdbdev.cvrfparser.model.cvrf.documentnotes.DocumentNotes;
import es.mdbdev.cvrfparser.model.cvrf.documenttracking.DocumentTracking;
import es.mdbdev.cvrfparser.model.cvrf.documentpublisher.DocumentPublisher;
import es.mdbdev.cvrfparser.model.cvrf.documentreferences.DocumentReferences;
import es.mdbdev.cvrfparser.model.cvrf.producttree.ProductTree;
import es.mdbdev.cvrfparser.model.cvrf.vulnerability.Vulnerability;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 28/01/2017.
 */
public class Cvrfdoc
{
    public Cvrfdoc(){
        this.vulnerabilities = new ArrayList<Vulnerability>();
    }

    private String aggregateSeverity;

    private es.mdbdev.cvrfparser.model.cvrf.producttree.ProductTree ProductTree;

    private es.mdbdev.cvrfparser.model.cvrf.documentpublisher.DocumentPublisher DocumentPublisher;

    private String DocumentTitle;

    private es.mdbdev.cvrfparser.model.cvrf.documenttracking.DocumentTracking DocumentTracking;

    private es.mdbdev.cvrfparser.model.cvrf.documentnotes.DocumentNotes DocumentNotes;

    private es.mdbdev.cvrfparser.model.cvrf.documentreferences.DocumentReferences DocumentReferences;

    private String xmlns;

    private String DocumentType;

    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    private List<Vulnerability> vulnerabilities;

    public ProductTree getProductTree ()
    {
        return ProductTree;
    }

    public void setProductTree (ProductTree ProductTree)
    {
        this.ProductTree = ProductTree;
    }

    public DocumentPublisher getDocumentPublisher ()
    {
        return DocumentPublisher;
    }

    public void setDocumentPublisher (DocumentPublisher DocumentPublisher)
    {
        this.DocumentPublisher = DocumentPublisher;
    }

    public String getDocumentTitle ()
    {
        return DocumentTitle;
    }

    public void setDocumentTitle (String DocumentTitle)
    {
        this.DocumentTitle = DocumentTitle;
    }

    public DocumentTracking getDocumentTracking ()
    {
        return DocumentTracking;
    }

    public void setDocumentTracking (DocumentTracking DocumentTracking)
    {
        this.DocumentTracking = DocumentTracking;
    }

    public DocumentNotes getDocumentNotes ()
    {
        return DocumentNotes;
    }

    public void setDocumentNotes (DocumentNotes DocumentNotes)
    {
        this.DocumentNotes = DocumentNotes;
    }

    public DocumentReferences getDocumentReferences ()
    {
        return DocumentReferences;
    }

    public void setDocumentReferences (DocumentReferences DocumentReferences)
    {
        this.DocumentReferences = DocumentReferences;
    }

    public String getXmlns ()
    {
        return xmlns;
    }

    public void setXmlns (String xmlns)
    {
        this.xmlns = xmlns;
    }

    public String getDocumentType ()
    {
        return DocumentType;
    }

    public void setDocumentType (String DocumentType)
    {
        this.DocumentType = DocumentType;
    }

    public String getAggregateSeverity() {
        return aggregateSeverity;
    }

    public void addVulnerability(Vulnerability v){
        this.vulnerabilities.add(v);
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ProductTree = "+ProductTree+", DocumentPublisher = "+DocumentPublisher+", DocumentTitle = "+DocumentTitle+", DocumentTracking = "+DocumentTracking+", DocumentNotes = "+DocumentNotes+", DocumentReferences = "+DocumentReferences+", xmlns = "+xmlns+", DocumentType = "+DocumentType+", vulnerabilities = "+vulnerabilities+"]";
    }

    public void setAggregateSeverity(String aggregateSeverity) {
        this.aggregateSeverity = aggregateSeverity;
    }
}
