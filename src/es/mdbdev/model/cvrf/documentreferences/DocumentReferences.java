package es.mdbdev.model.cvrf.documentreferences;

import es.mdbdev.model.cvrf.vulnerability.Reference;

/**
 * Created by Mario on 28/01/2017.
 */
public class DocumentReferences
{
    private es.mdbdev.model.cvrf.vulnerability.Reference Reference;

    public Reference getReference ()
    {
        return Reference;
    }

    public void setReference (Reference Reference)
    {
        this.Reference = Reference;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Reference = "+Reference+"]";
    }
}
