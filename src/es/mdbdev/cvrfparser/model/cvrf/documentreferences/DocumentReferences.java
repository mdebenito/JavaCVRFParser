package es.mdbdev.cvrfparser.model.cvrf.documentreferences;

/**
 * Created by Mario on 28/01/2017.
 */
public class DocumentReferences
{
    private es.mdbdev.cvrfparser.model.cvrf.vulnerability.Reference Reference;

    public es.mdbdev.cvrfparser.model.cvrf.vulnerability.Reference getReference ()
    {
        return Reference;
    }

    public void setReference (es.mdbdev.cvrfparser.model.cvrf.vulnerability.Reference Reference)
    {
        this.Reference = Reference;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Reference = "+Reference+"]";
    }
}
