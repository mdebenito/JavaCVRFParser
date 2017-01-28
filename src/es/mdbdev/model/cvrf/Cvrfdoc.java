package es.mdbdev.model.cvrf;

import es.mdbdev.model.cvrf.documentnotes.DocumentNotes;
import es.mdbdev.model.cvrf.documentpublisher.DocumentPublisher;
import es.mdbdev.model.cvrf.documentreferences.DocumentReferences;
import es.mdbdev.model.cvrf.documenttracking.DocumentTracking;
import es.mdbdev.model.cvrf.producttree.ProductTree;
import es.mdbdev.model.cvrf.vulnerability.Vulnerability;

/**
 * Created by Mario on 28/01/2017.
 */
public class Cvrfdoc
{
    private es.mdbdev.model.cvrf.producttree.ProductTree ProductTree;

    private es.mdbdev.model.cvrf.documentpublisher.DocumentPublisher DocumentPublisher;

    private String DocumentTitle;

    private es.mdbdev.model.cvrf.documenttracking.DocumentTracking DocumentTracking;

    private es.mdbdev.model.cvrf.documentnotes.DocumentNotes DocumentNotes;

    private es.mdbdev.model.cvrf.documentreferences.DocumentReferences DocumentReferences;

    private String xmlns;

    private String DocumentType;

    private es.mdbdev.model.cvrf.vulnerability.Vulnerability Vulnerability;

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

    public Vulnerability getVulnerability ()
    {
        return Vulnerability;
    }

    public void setVulnerability (Vulnerability Vulnerability)
    {
        this.Vulnerability = Vulnerability;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [ProductTree = "+ProductTree+", DocumentPublisher = "+DocumentPublisher+", DocumentTitle = "+DocumentTitle+", DocumentTracking = "+DocumentTracking+", DocumentNotes = "+DocumentNotes+", DocumentReferences = "+DocumentReferences+", xmlns = "+xmlns+", DocumentType = "+DocumentType+", Vulnerability = "+Vulnerability+"]";
    }
}
