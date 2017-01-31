package es.mdbdev.cvrfparser.model.cvrf.documentreferences;

import es.mdbdev.cvrfparser.model.cvrf.common.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 28/01/2017.
 */
public class DocumentReferences
{
    public DocumentReferences(){
        this.references = new ArrayList<Reference>();
    }
    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    private List<Reference> references;

    public void addReference(Reference r){
        this.references.add(r);
    }


    @Override
    public String toString()
    {
        return "ClassPojo [references = "+references+"]";
    }
}
